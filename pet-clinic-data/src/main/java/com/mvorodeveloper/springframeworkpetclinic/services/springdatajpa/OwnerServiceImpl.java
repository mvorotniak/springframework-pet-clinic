package com.mvorodeveloper.springframeworkpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;
import com.mvorodeveloper.springframeworkpetclinic.repositories.OwnerRepository;
import com.mvorodeveloper.springframeworkpetclinic.services.OwnerService;

public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().iterator().forEachRemaining(owners::add);

        return owners;
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Unable to find owner with id " + id));
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName)
            .orElseThrow(() -> new RuntimeException("Unable to find owner with last name " + lastName));
    }
}
