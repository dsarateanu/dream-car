package com.home.dreamcar.controller;

import com.home.dreamcar.model.Auction;
import com.home.dreamcar.model.Currency;
import com.home.dreamcar.model.Product;
import com.home.dreamcar.model.Status;
import com.home.dreamcar.service.AuctionService;
import com.home.dreamcar.service.OfferService;
import com.home.dreamcar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private ProductService productService;

    @GetMapping("{id}")
    public String details(@PathVariable Long id, Model model) {
        Auction auction = auctionService.find(id);
        model.addAttribute("auction", auction);
        model.addAttribute("offers", offerService.findByAuction(auction));
        return "auction/details";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/{id}", params = "edit=true")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("auction", auctionService.find(id));
        setDropdownValues(model);
        model.addAttribute("isEdit", true);
        return "auction/edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String addForm(Model model) {
        Auction auction = new Auction();
        auction.setExpirationDate(new Date());
        model.addAttribute("auction", auction);
        setDropdownValues(model);
        model.addAttribute("isEdit", false);
        return "auction/edit";
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

    private void setDropdownValues(Model model){
        List<String> statuses = Arrays.asList(Status.ACTIVE.toString(), Status.CLOSED.toString());
        model.addAttribute("statuses", statuses);
        List<String> currencies = Arrays.asList(Currency.RON.toString(), Currency.EUR.toString(), Currency.USD.toString());
        model.addAttribute("currencies", currencies);
        Iterable<Product> products = productService.findAll();
        model.addAttribute("products", products);
    }
}
