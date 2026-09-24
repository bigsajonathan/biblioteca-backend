package com.biblioteca.service.impl;

import com.biblioteca.model.Libro;
import com.biblioteca.repo.IGenericRepo;
import com.biblioteca.repo.ILibroRepo;
import com.biblioteca.service.ILibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl extends CRUDImpl<Libro, Integer> implements ILibroService {

    private final ILibroRepo repo;

    @Override
    protected IGenericRepo<Libro, Integer> getRepo() {
        return repo;
    }
}
