package com.mvorodeveloper.springframeworkpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvorodeveloper.springframeworkpetclinic.services.VetService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class VetsController {

    private final VetService vetService;

    @RequestMapping({"/vets", "/vets/", "/vets/index", "/vets/index.html", "/vets.html"})
    public String listVets(Model model) {
        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }

}
