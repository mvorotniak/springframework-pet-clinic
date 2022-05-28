package com.mvorodeveloper.springframeworkpetclinic.services;

import java.util.List;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;

public interface OwnerService extends BaseCrudService<Owner, Long> {

    List<Owner> findByLastNameLike(String lastName);

}