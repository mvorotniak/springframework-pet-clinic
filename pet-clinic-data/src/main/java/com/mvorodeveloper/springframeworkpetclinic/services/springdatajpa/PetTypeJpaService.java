package com.mvorodeveloper.springframeworkpetclinic.services.springdatajpa;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.mvorodeveloper.springframeworkpetclinic.model.PetType;
import com.mvorodeveloper.springframeworkpetclinic.repositories.PetTypeRepository;
import com.mvorodeveloper.springframeworkpetclinic.services.PetTypeService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class PetTypeJpaService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    @Override
    public Set<PetType> findAll() {
        return (Set<PetType>) petTypeRepository.findAll();
    }

    @Override
    public PetType findById(Long id) {
        return petTypeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Unable to find pet type with id [" + id + "]"));
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }

}
