package com.mvorodeveloper.springframeworkpetclinic.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;
import com.mvorodeveloper.springframeworkpetclinic.services.OwnerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/find")
    public String findOwners(final Model model) {
        model.addAttribute("owner", Owner.builder().build());

        return "owners/findOwners";
    }

    @GetMapping
    public String showRelatedOwners(final Owner owner, final BindingResult result, final Model model) {
        final String lastName = Optional.ofNullable(owner.getLastName()).orElse("");

        final List<Owner> relatedOwners = this.ownerService.findByLastNameLike(String.format("%%%s%%", lastName));

        if (relatedOwners.isEmpty()) {
            log.info("No owners found with lastName like [{}]", lastName);
            result.rejectValue("lastName", "404", "Not Found");
            return "owners/findOwners";
        } else if (relatedOwners.size() == 1) {
            log.info("Found 1 owner with lastName like [{}]", lastName);
            return "redirect:/owners/" + relatedOwners.get(0).getId();
        } else {
            log.info("Found [{}] owners with lastName like [{}]", relatedOwners.size(), lastName);
            model.addAttribute("owners", relatedOwners);
            return "owners/index";
        }
    }

    @GetMapping("/{id}")
    public ModelAndView findOwner(@PathVariable final Long id) {
        final ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject(this.ownerService.findById(id));

        return modelAndView;
    }

}
