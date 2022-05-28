package com.mvorodeveloper.springframeworkpetclinic.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    List<Owner> findByLastNameLike(String lastName);

}
