package com.mvorodeveloper.springframeworkpetclinic.services.map;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class OwnerMapServiceTest {

    private final String lastName = "Smith";

    private OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        // Manual dependency injection
        ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService());

        // Adding an Owner object to the HashMap
        ownerMapService.save(Owner.builder().lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(1L);

        assertNotNull(owner);
        assertEquals(lastName, owner.getLastName());
    }

    @Test
    void save() {
        String ownerLastName = "French";
        Owner owner = ownerMapService.save(Owner.builder().lastName(ownerLastName).build());

        assertNotNull(owner);
        assertEquals(ownerLastName, owner.getLastName());
        assertEquals(2L, owner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findByLastNameLike(lastName).get(0));
        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(0, owners.size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(1L);
        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(0, owners.size());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastNameLike(lastName).get(0);

        assertNotNull(owner);
        assertEquals(1L, owner.getId());
        assertEquals(lastName, owner.getLastName());
    }

    @Test
    void findByLastName_notFound() {
        final String randomLastName = "Da Vinci";
        final List<Owner> owners = ownerMapService.findByLastNameLike(randomLastName);

        assertThat(owners).isEmpty();
    }
}