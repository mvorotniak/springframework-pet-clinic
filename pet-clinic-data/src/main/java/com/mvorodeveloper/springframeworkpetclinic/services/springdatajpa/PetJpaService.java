package com.mvorodeveloper.springframeworkpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.mvorodeveloper.springframeworkpetclinic.model.Pet;
import com.mvorodeveloper.springframeworkpetclinic.repositories.PetRepository;
import com.mvorodeveloper.springframeworkpetclinic.services.PetService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class PetJpaService implements PetService {

    private final PetRepository petRepository;

    @Override
    public Set<Pet> findAll() {
        HashSet<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);

        return pets;
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Unable to find pet with id [" + id + "]"));
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }

}
