package com.mvorodeveloper.springframeworkpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mvorodeveloper.springframeworkpetclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
