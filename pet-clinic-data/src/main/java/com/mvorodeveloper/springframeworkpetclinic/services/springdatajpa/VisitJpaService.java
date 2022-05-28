package com.mvorodeveloper.springframeworkpetclinic.services.springdatajpa;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.mvorodeveloper.springframeworkpetclinic.model.Visit;
import com.mvorodeveloper.springframeworkpetclinic.repositories.VisitRepository;
import com.mvorodeveloper.springframeworkpetclinic.services.VisitService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class VisitJpaService implements VisitService {

    private final VisitRepository visitRepository;

    @Override
    public Set<Visit> findAll() {
        return (Set<Visit>) visitRepository.findAll();
    }

    @Override
    public Visit findById(Long id) {
        return visitRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Unable to find visit with id [" + id + "]"));
    }

    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object) {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }

}
