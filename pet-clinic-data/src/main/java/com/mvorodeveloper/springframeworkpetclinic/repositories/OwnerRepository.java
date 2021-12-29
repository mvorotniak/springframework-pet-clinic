package com.mvorodeveloper.springframeworkpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
