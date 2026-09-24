# Reserva Flow (Reserva + DetalleReserva)

|             |                                     |
| ----------- | ----------------------------------- |
| **Status**  | Implemented                          |
| **Date**    | 2026-09-21                          |
| **Scope**   | `com.biblioteca` (backend) |
| **Affects** | `Reserva`, `DetalleReserva`         |

> Specification for registering a book reservation for a client across one or more books.

---

# Part 1 - Specification

*Written before implementation.*

## Problem

A client can reserve one or more books in a single operation. Registering the header
(client, date) and each reserved book (detail line) as separate REST calls would allow
a reservation to exist with zero books, or a client-facing screen to have to orchestrate
multiple requests and handle partial failure.

## Goal

Provide a single REST endpoint that registers a `Reserva` together with all of its
`DetalleReserva` lines (one per reserved `Libro`) in one transactional call, plus
endpoints to list all reservations and to query the reservations of a given client.

## Scope

In scope:

* `Reserva` (idReserva, fechaReserva, cliente) and `DetalleReserva` (idDetalleReserva,
  reserva, libro) entities, mapped with `@OneToMany(cascade = ALL)` on the `Reserva`
  side and `@ManyToOne` on the `DetalleReserva` side.
- Single `POST /v1/reservas` that accepts a `Reserva` header with a non-empty list of
  `DetalleReserva` lines and persists all of it in one transaction.
* `GET /v1/reservas` listing every reservation with the client and reserved books
  nested in the response.
* `GET /v1/reservas/{id}`.
* `GET /v1/reservas/cliente/{idCliente}` to query a client's reservations.
* `DELETE /v1/reservas/{id}`.
* `@JsonManagedReference` / `@JsonBackReference` between `ReservaDTO` and
  `DetalleReservaDTO` to avoid infinite recursion on serialization.

Out of scope:

* `PUT /v1/reservas/{id}` (editing an existing reservation) - not needed for this
  scope, and it would require re-synchronizing the back-reference of existing detail
  lines on every update, adding complexity with no real use case here.
* Automatically toggling `Libro.disponibleLibro` when a book is reserved - this keeps
  the scope simple; availability stays a manually managed field on the `Libro` CRUD.
* Quantity per detail line, due dates, or any loan/return concept - a reservation is
  just a client, a date, and the books, nothing more.

## Decisions

**`DetalleReservaDTO` list requires `@NotEmpty`, not just `@NotNull`.**
A reservation with zero books isn't a valid reservation; an empty list must be
rejected with `400`, not silently accepted.

**The back-reference is wired at the entity level inside the service, not relied upon
from DTO deserialization alone.**
`ReservaServiceImpl.saveTransactional` explicitly sets `detalle.setReserva(reserva)`
for every line before calling `repo.save(reserva)`. This does not depend on
`ModelMapper`/Jackson producing a fully wired back-reference on the entity graph.

**`fechaReserva` is a `LocalDate`, not a `LocalDateTime`.**
A book reservation does not need a time component.

**`DetalleReserva` has no standalone repo, service, or controller.**
It is only ever persisted through the cascade from `Reserva`, never created or queried
on its own.

**No separate many-to-many join table.**
Each `DetalleReserva` already references exactly one `Libro`, so "one or more books" is
satisfied purely by the list of detail lines - no extra join table or repository is
needed.

## Contract

| Method | Path | Body | Success | Notes |
|---|---|---|---|---|
| POST | `/v1/reservas` | `ReservaDTO` (cliente + non-empty detalleReserva) | `201 Created` + `Location` | Uses `saveTransactional`, not the generic `ICRUD.save` |
| GET | `/v1/reservas` | - | `200 OK`, `List<ReservaDTO>` | Each item nests `cliente` and `detalleReserva[].libro` |
| GET | `/v1/reservas/{id}` | - | `200 OK`, `ReservaDTO` / `404` if missing |
| GET | `/v1/reservas/cliente/{idCliente}` | - | `200 OK`, `List<ReservaDTO>` |
| DELETE | `/v1/reservas/{id}` | - | `204 No Content` / `404` if missing |

## Acceptance Criteria

* [x] `POST /v1/reservas` with an empty `detalleReserva` list returns `400`.
* [x] `POST /v1/reservas` with a valid client and one or more books returns `201` and a
      `Location` header pointing at the created reservation.
* [x] `GET /v1/reservas` returns the client and the reserved books nested per
      reservation, without infinite recursion in the JSON.
* [x] `GET /v1/reservas/cliente/{idCliente}` returns only that client's reservations.
* [x] `DELETE /v1/reservas/{id}` on a non-existent id returns `404`.
* [x] There is no `PUT /v1/reservas/{id}` endpoint.
* [x] The project compiles (`./mvnw -DskipTests compile`).

Verified manually with `curl` against a local run (no automated test covers this flow
yet - the only test in the project is the default `contextLoads()` smoke test).

## Expected Implementation

```text
src/main/java/com/biblioteca/
├── model/Reserva.java
├── model/DetalleReserva.java
├── dto/ReservaDTO.java
├── dto/DetalleReservaDTO.java
├── repo/IReservaRepo.java          (+ findByClienteIdCliente)
├── service/IReservaService.java    (+ saveTransactional, findByCliente)
├── service/impl/ReservaServiceImpl.java
└── controller/ReservaController.java
```

---
