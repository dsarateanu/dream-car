package com.home.dreamcar.controller;

import com.home.dreamcar.model.Product;
import com.home.dreamcar.service.ProductService;
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
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.find(id));
        return "product/details";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "{id}", params = "edit=true")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.find(id));
        model.addAttribute("isEdit", true);
        return "product/edit";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("isEdit", false);
        return "admin/edit-product";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("{id}")
    public String saveproduct(@PathVariable Long id, @Valid Product product, BindingResult bindingResult, Model model) {
        product.setId(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", true);
            return "product/edit";
        }
        productService.saveOrUpdateProduct(product);
        return "redirect:/home";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String addproduct(@Valid Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "product/edit";
        }
        productService.saveOrUpdateProduct(product);
        return "redirect:/home";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/home";
    }

}
