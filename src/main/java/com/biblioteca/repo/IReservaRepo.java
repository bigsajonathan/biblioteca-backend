package com.biblioteca.repo;

import com.biblioteca.model.Reserva;

import java.util.List;

public interface IReservaRepo extends IGenericRepo<Reserva, Integer> {

    List<Reserva> findByClienteIdCliente(Integer idCliente);
}
