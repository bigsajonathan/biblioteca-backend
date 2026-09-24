package com.biblioteca.service;

import com.biblioteca.model.Reserva;

import java.util.List;

public interface IReservaService extends ICRUD<Reserva, Integer> {

    Reserva saveTransactional(Reserva reserva);

    List<Reserva> findByCliente(Integer idCliente);
}
