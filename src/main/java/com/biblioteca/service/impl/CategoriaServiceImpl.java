package com.biblioteca.service.impl;

import com.biblioteca.model.Categoria;
import com.biblioteca.repo.ICategoriaRepo;
import com.biblioteca.repo.IGenericRepo;
import com.biblioteca.service.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl extends CRUDImpl<Categoria, Integer> implements ICategoriaService {

    private final ICategoriaRepo repo;

    @Override
    protected IGenericRepo<Categoria, Integer> getRepo() {
        return repo;
    }
}
