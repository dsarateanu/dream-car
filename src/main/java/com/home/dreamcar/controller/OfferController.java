package com.home.dreamcar.controller;

import com.home.dreamcar.model.Offer;
import com.home.dreamcar.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/offer/")
public class OfferController {

    @Autowired
    OfferService offerService;

    @GetMapping("{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("offer", offerService.find(id));
        return "offer/details";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "{id}", params = "edit=true")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("offer", offerService.find(id));
        model.addAttribute("isEdit", true);
        return "offer/edit";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public String addForm(Model model) {
        model.addAttribute("offer", new Offer());
        model.addAttribute("isEdit", false);
        return "admin/edit-offer";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("{id}")
    public String saveoffer(@PathVariable Long id, @Valid Offer offer, BindingResult bindingResult, Model model) {
        offer.setId(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", true);
            return "offer/edit";
        }
        offerService.saveOrUpdateOffer(offer);
        return "redirect:/home";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String addoffer(@Valid Offer offer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "offer/edit";
        }
        offerService.saveOrUpdateOffer(offer);
        return "redirect:/home";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("{id}/delete")
    public String delete(@PathVariable Long id) {
        offerService.delete(id);
        return "redirect:/home";
    }

}
