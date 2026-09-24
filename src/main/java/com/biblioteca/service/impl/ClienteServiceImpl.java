package com.biblioteca.service.impl;

import com.biblioteca.model.Cliente;
import com.biblioteca.repo.IClienteRepo;
import com.biblioteca.repo.IGenericRepo;
import com.biblioteca.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl extends CRUDImpl<Cliente, Integer> implements IClienteService {

    private final IClienteRepo repo;

    @Override
    protected IGenericRepo<Cliente, Integer> getRepo() {
        return repo;
    }
}
