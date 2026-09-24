# biblioteca-backend

API REST para un sistema de reserva de libros de biblioteca. Spring Boot 4.1.0, Java 25,
PostgreSQL.

## Prerequisitos

* Java 25 (JDK).
* PostgreSQL 17 corriendo localmente.

## Base de datos

Se requiere una base PostgreSQL. Dos opciones:

**A. Crear una nueva**, con los valores por defecto del proyecto:

```
psql -U postgres -h localhost -c "CREATE DATABASE biblioteca;"
```

**B. Usar una existente**, con nombre/usuario/contraseña propios: copiar
`.env.example` a `.env` (en la raíz del proyecto) y completar los datos ahí, o
directamente editar los valores en `src/main/resources/application.yaml`.

En cualquiera de los dos casos, las tablas se crean solas al arrancar la aplicación
(`ddl-auto: update`) - no hace falta correr ningún script.

Si no se usa `.env`, estos son los valores por defecto:

| Variable | Default |
|---|---|
| `DB_URL` | `jdbc:postgresql://localhost:5432/biblioteca` |
| `DB_USERNAME` | `postgres` |
| `DB_PASSWORD` | `postgres` |
| `SERVER_PORT` | `8080` |

## Ejecutar

```
./mvnw spring-boot:run
```

Si existe un `.env` en la raíz del proyecto, se carga automáticamente (no hace falta
ningún flag ni perfil adicional).

## Endpoints

* `/v1/categorias`, `/v1/libros`, `/v1/clientes` - CRUD completo (`GET`, `GET /{id}`,
  `POST`, `PUT /{id}`, `DELETE /{id}`).
* `/v1/reservas` - `POST` (registra una reserva con uno o más libros), `GET` (listado
  con cliente y libros), `GET /{id}`, `GET /cliente/{idCliente}` (reservas de un
  cliente), `DELETE /{id}`.

## Specs y Agentes de IA

La Spec del flujo de Reserva y los Agentes usados para construir el proyecto están en
`.agents/` (ver también `CLAUDE.md`).
