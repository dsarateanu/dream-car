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
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ModelAndView productList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", productService.findAll());
        modelAndView.addObject("isEdit", null);
        modelAndView.addObject("product", new Product());
        modelAndView.setViewName("product/list");
        return modelAndView;
    }

    @GetMapping(value = "/{id}", params = "edit=true")
    public ModelAndView editForm(@PathVariable Long id, ModelAndView modelAndView) {
        modelAndView.addObject("products", productService.findAll());
        modelAndView.addObject("product", productService.find(id));
        modelAndView.addObject("isEdit", true);
        modelAndView.setViewName("product/list");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addForm(ModelAndView modelAndView) {
        modelAndView.addObject("products", productService.findAll());
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("isEdit", false);
        modelAndView.setViewName("product/list");
        return modelAndView;
    }

    @PostMapping("/{id}")
    public String saveproduct(@PathVariable Long id, @Valid Product product, BindingResult bindingResult, Model model) {
        product.setId(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", true);
            return "product/list";
        }
        productService.saveOrUpdateProduct(product);
        return "redirect:/product";
    }

    @PostMapping
    public String addproduct(@Valid Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "product/list";
        }
        productService.saveOrUpdateProduct(product);
        return "redirect:/product";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/product";
    }
}
