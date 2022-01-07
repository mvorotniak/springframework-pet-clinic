package com.mvorodeveloper.springframeworkpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;
import com.mvorodeveloper.springframeworkpetclinic.repositories.OwnerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    private final String lastName = "ExampleOwner";

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerJpaService ownerJpaService;

    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().lastName(lastName).build();
        owner.setId(1L);
    }

    @Test
    void findAll() {
        Owner owner1 = Owner.builder().lastName("Owner1").build();
        owner1.setId(1L);

        Owner owner2 = Owner.builder().lastName("Owner2").build();
        owner2.setId(2L);

        Set<Owner> owners = new HashSet<>();
        owners.add(owner1);
        owners.add(owner2);

        when(ownerRepository.findAll()).thenReturn(owners);

        Set<Owner> result = ownerJpaService.findAll();

        assertEquals(2, result.size());
        verify(ownerRepository).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));

        Owner result = ownerJpaService.findById(1L);

        assertEquals(lastName, result.getLastName());
        verify(ownerRepository).findById(1L);
    }

    @Test
    void findById_notFound() {
        when(ownerRepository.findById(2L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> ownerJpaService.findById(2L));
    }

    @Test
    void save() {
        when(ownerRepository.save(owner)).thenReturn(owner);

        Owner result = ownerJpaService.save(owner);

        assertEquals(1L, result.getId());
        assertEquals(lastName, result.getLastName());
        verify(ownerRepository).save(owner);
    }

    @Test
    void delete() {
        ownerJpaService.delete(owner);

        verify(ownerRepository).delete(owner);
    }

    @Test
    void deleteById() {
        ownerJpaService.deleteById(1L);

        verify(ownerRepository).deleteById(1L);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(lastName)).thenReturn(Optional.of(owner));

        Owner result = ownerJpaService.findByLastName(lastName);

        assertEquals(1L, result.getId());
        assertEquals(lastName, result.getLastName());
        verify(ownerRepository).findByLastName(lastName);
    }

    @Test
    void findByLastName_notFound() {
        when(ownerRepository.findByLastName(lastName)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> ownerJpaService.findByLastName(lastName));
    }
}