package com.mvorodeveloper.springframeworkpetclinic.services.map;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;
import com.mvorodeveloper.springframeworkpetclinic.model.Pet;
import com.mvorodeveloper.springframeworkpetclinic.model.PetType;
import com.mvorodeveloper.springframeworkpetclinic.services.OwnerService;
import com.mvorodeveloper.springframeworkpetclinic.services.PetService;
import com.mvorodeveloper.springframeworkpetclinic.services.PetTypeService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
// If there is no active profile Spring will take the "default" one
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetService petService;

    private final PetTypeService petTypeService;

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(final Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(final Owner object) {
        return Optional.ofNullable(object)
            .map(obj -> {
                checkOwnerPets(object);
                return super.save(object);
            })
            .orElse(null);
    }

    @Override
    public void delete(final Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(final Long id) {
        super.deleteById(id);
    }

    @Override
    public List<Owner> findByLastNameLike(final String lastName) {
        return findAll().stream()
            .filter(owner -> owner.getLastName().contains(lastName))
            .collect(Collectors.toList());
    }

    private void checkOwnerPets(final Owner owner) {
        owner.getPets().forEach(pet -> {
            checkPetType(pet);
            checkPetId(pet);
        });
    }

    private void checkPetType(final Pet pet) {
        Optional.ofNullable(pet.getPetType())
            .ifPresentOrElse(
                this::checkPetTypeId,
                () -> new RuntimeException("Owner's Pet should have Pet Type.")
            );
    }

    private void checkPetId(final Pet pet) {
        if (pet.getId() == null) {
            petService.save(pet);
        }
    }

    private void checkPetTypeId(final PetType petType) {
        if (petType.getId() == null) {
            petTypeService.save(petType);
        }
    }

}
