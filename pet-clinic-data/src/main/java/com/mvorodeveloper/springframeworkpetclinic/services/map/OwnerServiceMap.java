package com.mvorodeveloper.springframeworkpetclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;
import com.mvorodeveloper.springframeworkpetclinic.model.Pet;
import com.mvorodeveloper.springframeworkpetclinic.model.PetType;
import com.mvorodeveloper.springframeworkpetclinic.services.OwnerService;
import com.mvorodeveloper.springframeworkpetclinic.services.PetService;
import com.mvorodeveloper.springframeworkpetclinic.services.PetTypeService;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerServiceMap(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if (object != null) {
            checkOwnerPets(object);
            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        // TODO: add realization
        return null;
    }

    private void checkOwnerPets(Owner owner) {
        owner.getPets().forEach(pet -> {
            checkPetType(pet);
            checkPetId(pet);
        });
    }

    private void checkPetType(Pet pet) {
        if (pet.getPetType() != null) {
            checkPetTypeId(pet.getPetType());
        } else {
            throw new RuntimeException("Owner's Pet should have Pet Type.");
        }
    }

    private void checkPetId(Pet pet) {
        if (pet.getId() == null) {
            petService.save(pet);
        }
    }

    private void checkPetTypeId(PetType petType) {
        if (petType.getId() == null) {
            petTypeService.save(petType);
        }
    }
}
