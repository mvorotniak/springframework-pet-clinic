package com.mvorodeveloper.springframeworkpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvorodeveloper.springframeworkpetclinic.services.OwnerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listOwners(final Model model) {
        model.addAttribute("owners", this.ownerService.findAll());

        return "owners/index";
    }

    @RequestMapping("/find")
    public String findOwners() {
        return "notimplemented";
    }

    @GetMapping("/{id}")
    public ModelAndView findOwner(@PathVariable final Long id) {
        final ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject(this.ownerService.findById(id));

        return modelAndView;
    }

}
