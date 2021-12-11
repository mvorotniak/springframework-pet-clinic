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

    // No need of @Autowired
    public DataLoader(OwnerServiceMap ownerServiceMap, VetServiceMap vetServiceMap) {
        this.ownerServiceMap = ownerServiceMap;
        this.vetServiceMap = vetServiceMap;
    }

    @Override
    public void run(String... args) {
        Owner ownerLisa = new Owner();
        ownerLisa.setFirstName("Lisa");
        ownerLisa.setLastName("Simpson");

        Owner ownerBart = new Owner();
        ownerBart.setFirstName("Bart");
        ownerBart.setLastName("Simpson");

        ownerServiceMap.save(ownerLisa);
        ownerServiceMap.save(ownerBart);
        System.out.println("Saved owners..." + ownerServiceMap.findAll().size());

        Vet vetMarta = new Vet();
        vetMarta.setFirstName("Marta");
        vetMarta.setLastName("Thomas");

        Vet vetAlex = new Vet();
        vetAlex.setFirstName("Alex");
        vetAlex.setLastName("Gordon");

        vetServiceMap.save(vetMarta);
        vetServiceMap.save(vetAlex);
        System.out.println("Saved vets..." + vetServiceMap.findAll().size());
    }
}
