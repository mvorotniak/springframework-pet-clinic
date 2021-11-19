package com.mvorodeveloper.springframeworkpetclinic.services;

import java.util.Set;

import com.mvorodeveloper.springframeworkpetclinic.model.Vet;

public interface VetService {

    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}