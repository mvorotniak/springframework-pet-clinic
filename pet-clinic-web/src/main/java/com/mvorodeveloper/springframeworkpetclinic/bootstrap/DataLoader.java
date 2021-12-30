package com.mvorodeveloper.springframeworkpetclinic.bootstrap;

import java.time.LocalDate;

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

import lombok.extern.slf4j.Slf4j;

/**
 * Fills in-memory data stored into a HashMap
 */
@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    // No need of @Autowired
    public DataLoader(
        OwnerService ownerService,
        VetService vetService,
        PetTypeService petTypeService,
        SpecialtyService specialtyService,
        VisitService visitService
    ) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) {
        loadData();
    }

    private void loadData() {
        PetType dog = PetType.builder().name("Dog").build();
        PetType cat = PetType.builder().name("Cat").build();

        petTypeService.save(dog);
        petTypeService.save(cat);
        log.info("Saved pet types..." + petTypeService.findAll().size());

        Specialty radiology = Specialty.builder().description("Radiology").build();
        Specialty surgery = Specialty.builder().description("Surgery").build();
        Specialty dentistry = Specialty.builder().description("Dentistry").build();

        specialtyService.save(radiology);
        specialtyService.save(surgery);
        specialtyService.save(dentistry);
        log.info("Saved specialties..." + specialtyService.findAll().size());

        Owner ownerLisa = Owner.builder()
            .firstName("Lisa")
            .lastName("Simpson")
            .address("Main Street 1")
            .city("New York")
            .telephone("+123422524")
            .build();

        Pet lisaPet = Pet.builder().name("Sparkles").petType(dog).birthDate(LocalDate.now()).owner(ownerLisa).build();

        ownerLisa.getPets().add(lisaPet);

        Owner ownerBart = Owner.builder()
            .firstName("Bart")
            .lastName("Simpson")
            .address("Light Street 23")
            .city("Springfield")
            .telephone("+123444524")
            .build();

        Pet bartPet = Pet.builder().name("Mr Friss").petType(cat).birthDate(LocalDate.now()).owner(ownerBart).build();

        ownerBart.getPets().add(bartPet);

        ownerService.save(ownerLisa);
        ownerService.save(ownerBart);
        log.info("Saved owners..." + ownerService.findAll().size());

        Visit lisaPetVisit = Visit.builder()
            .description("Crazy dog")
            .date(LocalDate.now())
            .pet(lisaPet)
            .build();

        visitService.save(lisaPetVisit);

        Vet vetMarta = Vet.builder().firstName("Marta").lastName("Thomas").build();
        vetMarta.getSpecialties().add(radiology);

        Vet vetAlex = Vet.builder().firstName("Alex").lastName("Gordon").build();
        vetAlex.getSpecialties().add(surgery);

        vetService.save(vetMarta);
        vetService.save(vetAlex);
        log.info("Saved vets..." + vetService.findAll().size());
    }
}
