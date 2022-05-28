package com.mvorodeveloper.springframeworkpetclinic.bootstrap;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;
import com.mvorodeveloper.springframeworkpetclinic.model.Pet;
import com.mvorodeveloper.springframeworkpetclinic.model.PetType;
import com.mvorodeveloper.springframeworkpetclinic.model.Specialty;
import com.mvorodeveloper.springframeworkpetclinic.model.Vet;
import com.mvorodeveloper.springframeworkpetclinic.model.Visit;
import com.mvorodeveloper.springframeworkpetclinic.services.OwnerService;
import com.mvorodeveloper.springframeworkpetclinic.services.PetTypeService;
import com.mvorodeveloper.springframeworkpetclinic.services.SpecialtyService;
import com.mvorodeveloper.springframeworkpetclinic.services.VetService;
import com.mvorodeveloper.springframeworkpetclinic.services.VisitService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Fills in-memory data stored into a HashMap
 */
@AllArgsConstructor
@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;

    private final VetService vetService;

    private final PetTypeService petTypeService;

    private final SpecialtyService specialtyService;

    private final VisitService visitService;

    @Override
    public void run(String... args) {
        loadData();
    }

    private void loadData() {
        final PetType dog = PetType.builder().name("Dog").build();
        final PetType cat = PetType.builder().name("Cat").build();
        this.petTypeService.save(dog);
        this.petTypeService.save(cat);
        final Set<PetType> petTypes = this.petTypeService.findAll();
        log.info("Saved [{}] pet types: [{}]", petTypes.size(), petTypes);

        final Specialty radiology = Specialty.builder().description("Radiology").build();
        final Specialty surgery = Specialty.builder().description("Surgery").build();
        final Specialty dentistry = Specialty.builder().description("Dentistry").build();
        this.specialtyService.save(radiology);
        this.specialtyService.save(surgery);
        this.specialtyService.save(dentistry);
        final Set<Specialty> specialties = this.specialtyService.findAll();
        log.info("Saved [{}] specialties: [{}]", specialties.size(), specialties);

        final Owner ownerLisa = Owner.builder()
            .firstName("Lisa")
            .lastName("Simpson")
            .address("Main Street 1")
            .city("New York")
            .telephone("+123422524")
            .build();
        final Pet lisaPet = Pet.builder().name("Sparkles").petType(dog).birthDate(LocalDate.now()).owner(ownerLisa).build();
        ownerLisa.getPets().add(lisaPet);
        final Owner ownerBart = Owner.builder()
            .firstName("Bart")
            .lastName("Simpson")
            .address("Light Street 23")
            .city("Springfield")
            .telephone("+123444524")
            .build();
        final Pet bartPet = Pet.builder().name("Mr Friss").petType(cat).birthDate(LocalDate.now()).owner(ownerBart).build();
        ownerBart.getPets().add(bartPet);
        this.ownerService.save(ownerLisa);
        this.ownerService.save(ownerBart);
        final Set<Owner> owners = this.ownerService.findAll();
        log.info("Saved [{}] owners: [{}]", owners.size(), owners);

        final Visit lisaPetVisit = Visit.builder()
            .description("Crazy dog")
            .date(LocalDate.now())
            .pet(lisaPet)
            .build();
        this.visitService.save(lisaPetVisit);
        final Set<Visit> visits = this.visitService.findAll();
        log.info("Saved [{}] visits: [{}]", visits.size(), visits);

        final Vet vetMarta = Vet.builder().firstName("Marta").lastName("Thomas").build();
        vetMarta.getSpecialties().add(radiology);
        final Vet vetAlex = Vet.builder().firstName("Alex").lastName("Gordon").build();
        vetAlex.getSpecialties().add(surgery);
        this.vetService.save(vetMarta);
        this.vetService.save(vetAlex);
        final Set<Vet> vets = this.vetService.findAll();
        log.info("Saved [{}] vets: [{}]", vets.size(), vets);
    }

}
