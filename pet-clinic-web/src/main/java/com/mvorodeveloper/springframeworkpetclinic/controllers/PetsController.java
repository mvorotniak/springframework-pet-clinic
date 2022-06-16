package com.mvorodeveloper.springframeworkpetclinic.controllers;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;
import com.mvorodeveloper.springframeworkpetclinic.model.PetType;
import com.mvorodeveloper.springframeworkpetclinic.services.OwnerService;
import com.mvorodeveloper.springframeworkpetclinic.services.PetTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RequestMapping("/owners/{ownerId}")
@Controller
public class PetsController {

    private PetTypeService petTypeService;

    private OwnerService ownerService;

    /**
     * Binds method return value to `types` model attribute
     */
    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return this.petTypeService.findAll();
    }

    /**
     * Binds method return value to `owner` model attribute
     */
    @ModelAttribute("owner")
    public Owner setOwner(@PathVariable Long ownerId) {
        return this.ownerService.findById(ownerId);
    }

    @InitBinder
    public void setAllowedFields(final WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

}
