package com.mvorodeveloper.springframeworkpetclinic.controllers;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvorodeveloper.springframeworkpetclinic.model.Vet;
import com.mvorodeveloper.springframeworkpetclinic.services.VetService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class VetsController {

    private final VetService vetService;

    @GetMapping({"/vets", "/vets/", "/vets/index", "/vets/index.html", "/vets.html"})
    public String listVets(final Model model) {
        model.addAttribute("vets", this.vetService.findAll());

        return "vets/index";
    }

    /**
     * @return List of Vets from the DB in JSON format
     */
    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> listVetsJson() {
        return this.vetService.findAll();
    }

}
