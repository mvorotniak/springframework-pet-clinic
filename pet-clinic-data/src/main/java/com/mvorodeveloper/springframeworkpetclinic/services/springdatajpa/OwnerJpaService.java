package com.mvorodeveloper.springframeworkpetclinic.services.springdatajpa;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;
import com.mvorodeveloper.springframeworkpetclinic.repositories.OwnerRepository;
import com.mvorodeveloper.springframeworkpetclinic.services.OwnerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
// this is added so there are no conflicts with other implementations of OwnerService. This profile is disabled.
@Profile("springdatajpa")
public class OwnerJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public Set<Owner> findAll() {
        return (Set<Owner>) ownerRepository.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Unable to find owner with id [" + id + "]"));
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
            .orElseThrow(() -> new RuntimeException("Unable to find owner with last name [" + lastName + "]"));
    }

}
