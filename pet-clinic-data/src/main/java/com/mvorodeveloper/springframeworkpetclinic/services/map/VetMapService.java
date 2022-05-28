package com.mvorodeveloper.springframeworkpetclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.mvorodeveloper.springframeworkpetclinic.model.Vet;
import com.mvorodeveloper.springframeworkpetclinic.services.SpecialtyService;
import com.mvorodeveloper.springframeworkpetclinic.services.VetService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile({"default", "map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {
        if (!object.getSpecialties().isEmpty()) {
            checkSpecialties(object);
        }

        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    private void checkSpecialties(Vet vet) {
        vet.getSpecialties().forEach(specialty -> {
            if (specialty.getId() == null) {
                specialtyService.save(specialty);
            }
        });
    }

}
