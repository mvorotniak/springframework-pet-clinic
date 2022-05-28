package com.mvorodeveloper.springframeworkpetclinic.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Optional<Owner> findByLastName(String lastName);

}
