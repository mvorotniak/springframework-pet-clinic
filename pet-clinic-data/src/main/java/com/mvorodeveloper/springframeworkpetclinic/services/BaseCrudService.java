package com.mvorodeveloper.springframeworkpetclinic.services;

import java.util.Set;

public interface BaseCrudService<T, I> {

    Set<T> findAll();

    T findById(I id);

    T save(T object);

    void delete(T object);

    void deleteById(I id);

}
