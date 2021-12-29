package com.mvorodeveloper.springframeworkpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mvorodeveloper.springframeworkpetclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
