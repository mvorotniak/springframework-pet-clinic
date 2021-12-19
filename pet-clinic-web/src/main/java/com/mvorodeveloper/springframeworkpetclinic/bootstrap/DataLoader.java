package com.mvorodeveloper.springframeworkpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;
import com.mvorodeveloper.springframeworkpetclinic.model.PetType;
import com.mvorodeveloper.springframeworkpetclinic.model.Vet;
import com.mvorodeveloper.springframeworkpetclinic.services.OwnerService;
import com.mvorodeveloper.springframeworkpetclinic.services.PetTypeService;
import com.mvorodeveloper.springframeworkpetclinic.services.VetService;

/**
 * Fills in-memory data stored into a HashMap
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    // No need of @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) {
        PetType dog = new PetType();
        dog.setName("Dog");

        PetType cat = new PetType();
        cat.setName("Cat");

        petTypeService.save(dog);
        petTypeService.save(cat);
        System.out.println("Saved pet types..." + petTypeService.findAll().size());

        Owner ownerLisa = new Owner();
        ownerLisa.setFirstName("Lisa");
        ownerLisa.setLastName("Simpson");

        Owner ownerBart = new Owner();
        ownerBart.setFirstName("Bart");
        ownerBart.setLastName("Simpson");

        ownerService.save(ownerLisa);
        ownerService.save(ownerBart);
        System.out.println("Saved owners..." + ownerService.findAll().size());

        Vet vetMarta = new Vet();
        vetMarta.setFirstName("Marta");
        vetMarta.setLastName("Thomas");

        Vet vetAlex = new Vet();
        vetAlex.setFirstName("Alex");
        vetAlex.setLastName("Gordon");

        vetService.save(vetMarta);
        vetService.save(vetAlex);
        System.out.println("Saved vets..." + vetService.findAll().size());
    }
}
