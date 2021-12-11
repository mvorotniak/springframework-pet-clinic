package com.mvorodeveloper.springframeworkpetclinic.services.map;

import java.util.Set;

import com.mvorodeveloper.springframeworkpetclinic.model.Pet;
import com.mvorodeveloper.springframeworkpetclinic.services.BaseCrudService;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements BaseCrudService<Pet, Long> {

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
