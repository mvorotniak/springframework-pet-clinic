package com.mvorodeveloper.springframeworkpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mvorodeveloper.springframeworkpetclinic.model.Specialty;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
