package com.mvorodeveloper.springframeworkpetclinic.services.map;

import java.util.Set;

import com.mvorodeveloper.springframeworkpetclinic.model.PetType;
import com.mvorodeveloper.springframeworkpetclinic.services.PetTypeService;

public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }

    @Override
    public PetType save(PetType object) {
        return super.save(object);
    }

    @Override
    public void delete(PetType object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
