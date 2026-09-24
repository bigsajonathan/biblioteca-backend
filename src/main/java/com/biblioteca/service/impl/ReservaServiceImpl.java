package com.biblioteca.service.impl;

import com.biblioteca.model.Reserva;
import com.biblioteca.repo.IGenericRepo;
import com.biblioteca.repo.IReservaRepo;
import com.biblioteca.service.IReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl extends CRUDImpl<Reserva, Integer> implements IReservaService {

    private final IReservaRepo repo;

    @Override
    protected IGenericRepo<Reserva, Integer> getRepo() {
        return repo;
    }

    @Transactional
    @Override
    public Reserva saveTransactional(Reserva reserva) {
        reserva.getDetalleReserva().forEach(detalle -> detalle.setReserva(reserva));

        return repo.save(reserva);
    }

    @Override
    public List<Reserva> findByCliente(Integer idCliente) {
        return repo.findByClienteIdCliente(idCliente);
    }
}
