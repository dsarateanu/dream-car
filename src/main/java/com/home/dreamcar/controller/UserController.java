package com.home.dreamcar.controller;

import com.home.dreamcar.model.User;
import com.home.dreamcar.service.EmailService;
import com.home.dreamcar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @GetMapping
    public ModelAndView userPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.findAll());
        modelAndView.setViewName("user/list");
        return modelAndView;
    }

    @GetMapping(value = {"/invalidate/{id}"})
    public ModelAndView invalidateUser(@PathVariable Long id) {
        User user = userService.findUserById(id);
        user.setActive(0);
        userService.updateUser(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.findAll());
        modelAndView.setViewName("user/list");
        return modelAndView;
    }

    @GetMapping(value = {"/validate/{id}"})
    public ModelAndView validateUser(@PathVariable Long id) {
        User user = userService.findUserById(id);
        user.setActive(1);
        userService.updateUser(user);
        emailService.sendMailToActivatedUser(user.getEmail());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.findAll());
        modelAndView.setViewName("user/list");
        return modelAndView;
    }
}
