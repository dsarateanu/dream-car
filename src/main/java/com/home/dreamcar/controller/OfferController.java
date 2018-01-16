package com.home.dreamcar.controller;

import com.home.dreamcar.model.Auction;
import com.home.dreamcar.model.Offer;
import com.home.dreamcar.model.User;
import com.home.dreamcar.service.AuctionService;
import com.home.dreamcar.service.OfferService;
import com.home.dreamcar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    OfferService offerService;
    @Autowired
    AuctionService auctionService;
    @Autowired
    UserService userService;

    @GetMapping(value = "/{id}", params = "edit=true")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("offer", offerService.find(id));
        model.addAttribute("isEdit", true);
        return "offer/edit";
    }

    @GetMapping
    public String addForm(@RequestParam("auctionid") Long auctionid, Model model) {
        model.addAttribute("auctionid", auctionid);
        model.addAttribute("offer", new Offer());
        model.addAttribute("isEdit", false);
        return "offer/edit";
    }

    @PostMapping("/{id}")
    public String saveoffer(@PathVariable Long id, @RequestParam("auctionid") Long auctionid, @Valid Offer offer, BindingResult bindingResult, Model model) {
        offer.setId(id);
        Auction auction = auctionService.find(auctionid);
        offer.setAuction(auction);
        offer.setUser(userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", true);
            return "offer/edit";
        }
        offerService.saveOrUpdateOffer(offer);
        return "redirect:/auction/" + auctionid;
    }

    @PostMapping
    public String addoffer(@Valid Offer offer, @RequestParam("auctionid") Long auctionid, BindingResult bindingResult, Model model) {
        Auction auction = auctionService.find(auctionid);
        offer.setAuction(auction);
        offer.setUser(userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "offer/edit";
        }
        offerService.saveOrUpdateOffer(offer);
        return "redirect:/auction/" + auctionid;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, @RequestParam("auctionid") Long auctionid) {
        offerService.delete(id);
        return "redirect:/auction/" + auctionid;
    }

}
