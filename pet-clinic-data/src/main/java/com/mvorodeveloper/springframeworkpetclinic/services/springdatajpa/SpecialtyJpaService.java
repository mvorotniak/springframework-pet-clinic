package com.mvorodeveloper.springframeworkpetclinic.services.springdatajpa;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.mvorodeveloper.springframeworkpetclinic.model.Specialty;
import com.mvorodeveloper.springframeworkpetclinic.repositories.SpecialtyRepository;
import com.mvorodeveloper.springframeworkpetclinic.services.SpecialtyService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class SpecialtyJpaService implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    @Override
    public Set<Specialty> findAll() {
        return (Set<Specialty>) specialtyRepository.findAll();
    }

    @Override
    public Specialty findById(Long id) {
        return specialtyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Unable to find specialty with id [" + id + "]"));
    }

    @Override
    public Specialty save(Specialty object) {
        return specialtyRepository.save(object);
    }

    @Override
    public void delete(Specialty object) {
        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);
    }

}
