package com.mvorodeveloper.springframeworkpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;
import com.mvorodeveloper.springframeworkpetclinic.model.Vet;
import com.mvorodeveloper.springframeworkpetclinic.services.map.OwnerServiceMap;
import com.mvorodeveloper.springframeworkpetclinic.services.map.VetServiceMap;

/**
 * Fills in-memory data stored into a HashMap
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerServiceMap ownerServiceMap;
    private final VetServiceMap vetServiceMap;

    public DataLoader() {
        ownerServiceMap = new OwnerServiceMap();
        vetServiceMap = new VetServiceMap();
    }

    @Override
    public void run(String... args) {
        Owner ownerLisa = new Owner();
        ownerLisa.setId(1L);
        ownerLisa.setFirstName("Lisa");
        ownerLisa.setLastName("Simpson");

        Owner ownerBart = new Owner();
        ownerBart.setId(2L);
        ownerBart.setFirstName("Bart");
        ownerBart.setLastName("Simpson");

        ownerServiceMap.save(ownerLisa);
        ownerServiceMap.save(ownerBart);
        System.out.println("Saved owners..." + ownerServiceMap.findAll().size());

        Vet vetMarta = new Vet();
        vetMarta.setId(1L);
        vetMarta.setFirstName("Marta");
        vetMarta.setLastName("Thomas");

        Vet vetAlex = new Vet();
        vetAlex.setId(2L);
        vetAlex.setFirstName("Alex");
        vetAlex.setLastName("Gordon");

        vetServiceMap.save(vetMarta);
        vetServiceMap.save(vetAlex);
        System.out.println("Saved vets..." + vetServiceMap.findAll().size());
    }
}
