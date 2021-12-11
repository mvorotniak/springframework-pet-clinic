package com.mvorodeveloper.springframeworkpetclinic.services;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;

public interface OwnerService extends BaseCrudService<Owner, Long>{

    Owner findByLastName(String lastName);
}