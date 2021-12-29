package com.mvorodeveloper.springframeworkpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mvorodeveloper.springframeworkpetclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
