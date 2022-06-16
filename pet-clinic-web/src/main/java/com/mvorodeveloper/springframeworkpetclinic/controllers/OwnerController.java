package com.mvorodeveloper.springframeworkpetclinic.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvorodeveloper.springframeworkpetclinic.model.Owner;
import com.mvorodeveloper.springframeworkpetclinic.services.OwnerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.mvorodeveloper.springframeworkpetclinic.utils.ViewsConstants.CREATE_OR_UPDATE_OWNER_VIEW;
import static com.mvorodeveloper.springframeworkpetclinic.utils.ViewsConstants.FIND_OWNERS_VIEW;
import static com.mvorodeveloper.springframeworkpetclinic.utils.ViewsConstants.OWNERS_INDEX_VIEW;
import static com.mvorodeveloper.springframeworkpetclinic.utils.ViewsConstants.OWNER_DETAILS_VIEW;
import static com.mvorodeveloper.springframeworkpetclinic.utils.ViewsConstants.OWNER_HOME_REDIRECT;

@Slf4j
@AllArgsConstructor
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    /**
     * Prevents the model from getting the id, so it cannot be submitted if the end-user modifies the page
     * or the request
     */
    @InitBinder
    public void setAllowedFields(final WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/find")
    public String findOwnersForm(final Model model) {
        model.addAttribute("owner", Owner.builder().build());

        return FIND_OWNERS_VIEW;
    }

    @GetMapping
    public String showRelatedOwners(final Owner owner, final BindingResult result, final Model model) {
        final String lastName = Optional.ofNullable(owner.getLastName()).orElse("");

        final List<Owner> relatedOwners = this.ownerService.findByLastNameLike(String.format("%%%s%%", lastName));

        if (relatedOwners.isEmpty()) {
            log.info("No owners found with lastName like [{}]", lastName);
            result.rejectValue("lastName", "404", "Owner Not Found");
            return FIND_OWNERS_VIEW;
        } else if (relatedOwners.size() == 1) {
            log.info("Found 1 owner with lastName like [{}]", lastName);
            return OWNER_HOME_REDIRECT + relatedOwners.get(0).getId();
        } else {
            log.info("Found [{}] owners with lastName like [{}]", relatedOwners.size(), lastName);
            model.addAttribute("owners", relatedOwners);
            return OWNERS_INDEX_VIEW;
        }
    }

    @GetMapping("/{id}")
    public ModelAndView findOwner(@PathVariable final Long id) {
        final ModelAndView modelAndView = new ModelAndView(OWNER_DETAILS_VIEW);
        modelAndView.addObject(this.ownerService.findById(id));

        return modelAndView;
    }

    @GetMapping("/new")
    public String createOwnerForm(final Model model) {
        model.addAttribute("owner", Owner.builder().build());

        return CREATE_OR_UPDATE_OWNER_VIEW;
    }

    @PostMapping("/new")
    public String addOwner(final Owner owner, final BindingResult result) {
        if (result.hasErrors()) {
            log.error("Error occurred while trying to save owner with firstName=[{}], lastName=[{}]",
                owner.getFirstName(), owner.getLastName());
            return CREATE_OR_UPDATE_OWNER_VIEW;
        } else {
            final Owner savedOwner = this.ownerService.save(owner);
            return OWNER_HOME_REDIRECT + savedOwner.getId();
        }
    }

    @GetMapping("/{id}/edit")
    public String updateOwnerForm(@PathVariable final Long id, final Model model) {
        final Owner owner = this.ownerService.findById(id);

        model.addAttribute("owner", owner);
        return CREATE_OR_UPDATE_OWNER_VIEW;
    }

    @PostMapping("/{id}/edit")
    public String editOwner(@Valid final Owner owner, final BindingResult result, @PathVariable final Long id) {
        if (result.hasErrors()) {
            log.error("Error occurred while trying to update owner with firstName=[{}], lastName=[{}]",
                owner.getFirstName(), owner.getLastName());
            return CREATE_OR_UPDATE_OWNER_VIEW;
        } else {
            owner.setId(id);
            final Owner savedOwner = this.ownerService.save(owner);
            return OWNER_HOME_REDIRECT + savedOwner.getId();
        }
    }

}
