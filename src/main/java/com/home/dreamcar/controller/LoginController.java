package com.home.dreamcar.controller;

import com.home.dreamcar.model.Status;
import com.home.dreamcar.model.User;
import com.home.dreamcar.model.UserType;
import com.home.dreamcar.service.AuctionService;
import com.home.dreamcar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuctionService auctionService;

    @GetMapping(value = {"/", "/login"})
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value = {"/user"})
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
        modelAndView.setViewName("user/list");
        return modelAndView;
    }

    @GetMapping(value = {"/validate/{id}"})
    public ModelAndView validateUser(@PathVariable Long id) {
        User user = userService.findUserById(id);
        user.setActive(1);
        userService.updateUser(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/list");
        return modelAndView;
    }

    @GetMapping(value = "/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.addObject("successMessage", null);
        modelAndView.setViewName("/registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());

        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
        }

        if (!bindingResult.hasErrors()) {
            if (user.getRole() == null || user.getRole().equals(UserType.USER.toString())) {
                user.setRole(UserType.USER.toString());
                if (getLoggedUser().getRole().equals(UserType.ADMIN)) {
                    modelAndView.addObject("successMessage", "User has been registered successfully");
                    userService.saveUser(user, true);
                } else {
                    modelAndView.addObject("successMessage", "User has been registered successfully. After validation of admin user, you will receive an email and your account should be activated. ");
                    userService.saveUser(user, false);
                }
            } else {
                modelAndView.addObject("successMessage", "User has been registered successfully");
                userService.saveAdminUser(user);
            }
            modelAndView.addObject("user", new User());
        }

        modelAndView.setViewName("/registration");
        return modelAndView;
    }

    @GetMapping(value = "/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        User loggedUser = getLoggedUser();
        modelAndView.addObject("userName", "Welcome " + loggedUser.getName() + "(" + loggedUser.getEmail() + ")");
        if (loggedUser.getRole() == UserType.ADMIN.toString()) {
            modelAndView.addObject("auctions", auctionService.findAll());
        } else {
            modelAndView.addObject("auctions", auctionService.findAllByStatus(Status.ACTIVE.toString()));
        }
        modelAndView.setViewName("/home");
        return modelAndView;
    }

    public User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserByEmail(auth.getName());
    }

}
