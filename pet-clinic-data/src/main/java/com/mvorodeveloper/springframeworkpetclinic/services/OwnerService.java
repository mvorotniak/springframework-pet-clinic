package com.mvorodeveloper.springframeworkpetclinic.services;

import java.util.Set;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;

public interface OwnerService {

    Owner findByLastName(String lastName);

    Owner findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}