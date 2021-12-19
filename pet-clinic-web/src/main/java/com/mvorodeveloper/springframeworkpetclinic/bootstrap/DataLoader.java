package com.mvorodeveloper.springframeworkpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;
import com.mvorodeveloper.springframeworkpetclinic.model.Pet;
import com.mvorodeveloper.springframeworkpetclinic.model.PetType;
import com.mvorodeveloper.springframeworkpetclinic.model.Specialty;
import com.mvorodeveloper.springframeworkpetclinic.model.Vet;
import com.mvorodeveloper.springframeworkpetclinic.services.OwnerService;
import com.mvorodeveloper.springframeworkpetclinic.services.PetTypeService;
import com.mvorodeveloper.springframeworkpetclinic.services.SpecialtyService;
import com.mvorodeveloper.springframeworkpetclinic.services.VetService;

/**
 * Fills in-memory data stored into a HashMap
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    // No need of @Autowired
    public DataLoader(
        OwnerService ownerService,
        VetService vetService,
        PetTypeService petTypeService,
        SpecialtyService specialtyService
    ) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) {
        loadData();
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");

        PetType cat = new PetType();
        cat.setName("Cat");

        petTypeService.save(dog);
        petTypeService.save(cat);
        System.out.println("Saved pet types..." + petTypeService.findAll().size());

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");

        specialtyService.save(radiology);
        specialtyService.save(surgery);
        specialtyService.save(dentistry);
        System.out.println("Saved specialties..." + specialtyService.findAll().size());

        Owner ownerLisa = new Owner();
        ownerLisa.setFirstName("Lisa");
        ownerLisa.setLastName("Simpson");
        ownerLisa.setAddress("Main Street 1");
        ownerLisa.setCity("New York");
        ownerLisa.setTelephone("+123422524");

        Pet lisaPet = new Pet();
        lisaPet.setName("Sparkles");
        lisaPet.setPetType(dog);
        lisaPet.setBirthDate(LocalDate.now());
        lisaPet.setOwner(ownerLisa);

        ownerLisa.getPets().add(lisaPet);

        Owner ownerBart = new Owner();
        ownerBart.setFirstName("Bart");
        ownerBart.setLastName("Simpson");
        ownerBart.setAddress("Light Street 23");
        ownerBart.setCity("Springfield");
        ownerBart.setTelephone("+123444524");

        Pet bartPet = new Pet();
        bartPet.setName("Mr Friss");
        bartPet.setPetType(cat);
        bartPet.setBirthDate(LocalDate.now());
        bartPet.setOwner(ownerBart);

        ownerBart.getPets().add(bartPet);

        ownerService.save(ownerLisa);
        ownerService.save(ownerBart);
        System.out.println("Saved owners..." + ownerService.findAll().size());

        Vet vetMarta = new Vet();
        vetMarta.setFirstName("Marta");
        vetMarta.setLastName("Thomas");
        vetMarta.getSpecialties().add(radiology);

        Vet vetAlex = new Vet();
        vetAlex.setFirstName("Alex");
        vetAlex.setLastName("Gordon");
        vetAlex.getSpecialties().add(surgery);

        vetService.save(vetMarta);
        vetService.save(vetAlex);
        System.out.println("Saved vets..." + vetService.findAll().size());
    }
}
