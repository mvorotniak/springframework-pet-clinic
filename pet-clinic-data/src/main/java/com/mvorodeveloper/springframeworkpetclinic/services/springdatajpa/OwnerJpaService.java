package com.mvorodeveloper.springframeworkpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.List;
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
        Set<Owner> owners = new HashSet<>();
        this.ownerRepository.findAll().forEach(owners::add);

        return owners;
    }

    @Override
    public Owner findById(final Long id) {
        return this.ownerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Unable to find owner with id [" + id + "]"));
    }

    @Override
    public Owner save(final Owner object) {
        return this.ownerRepository.save(object);
    }

    @Override
    public void delete(final Owner object) {
        this.ownerRepository.delete(object);
    }

    @Override
    public void deleteById(final Long id) {
        this.ownerRepository.deleteById(id);
    }

    @Override
    public List<Owner> findByLastNameLike(final String lastName) {
        return this.ownerRepository.findByLastNameLike(lastName);
    }

}
