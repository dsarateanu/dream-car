package com.home.dreamcar.controller;

import com.home.dreamcar.model.Auction;
import com.home.dreamcar.service.AuctionService;
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
@RequestMapping("/auction/")
public class AuctionController {

    @Autowired
    AuctionService auctionService;

    @GetMapping("{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("auction", auctionService.find(id));
        return "auction/details";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "{id}", params = "edit=true")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("auction", auctionService.find(id));
        model.addAttribute("isEdit", true);
        return "/home";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public String addForm(Model model) {
        model.addAttribute("auction", new Auction());
        model.addAttribute("isEdit", false);
        return "/home";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("{id}")
    public String saveAuction(@PathVariable Long id, @Valid Auction auction, BindingResult bindingResult, Model model) {
        auction.setId(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", true);
            return "auction/edit";
        }
        auctionService.saveOrUpdateAuction(auction);
        return "redirect:/home";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String addAuction(@Valid Auction auction, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "auction/edit";
        }
        auctionService.saveOrUpdateAuction(auction);
        return "redirect:/home";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("{id}/delete")
    public String delete(@PathVariable Long id) {
        auctionService.delete(id);
        return "redirect:/home";
    }

}
