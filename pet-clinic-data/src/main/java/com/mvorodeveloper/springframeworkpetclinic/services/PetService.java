package com.mvorodeveloper.springframeworkpetclinic.services;

import java.util.Set;

import com.mvorodeveloper.springframeworkpetclinic.model.Pet;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}