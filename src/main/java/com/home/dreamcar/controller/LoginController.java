package com.home.dreamcar.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.home.dreamcar.model.Auction;
import com.home.dreamcar.model.Product;
import com.home.dreamcar.model.User;
import com.home.dreamcar.service.AuctionService;
import com.home.dreamcar.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuctionService auctionService;

	@RequestMapping( value = { "/", "/login" }, method = RequestMethod.GET )
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName( "login" );
		return modelAndView;
	}

	@RequestMapping( value = "/registration", method = RequestMethod.GET )
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for ( GrantedAuthority ga : authorities ) {
			if ( ga.getAuthority().equals( "ADMIN" ) ) {
				modelAndView.addObject( "admin", true );
			} else {
				modelAndView.addObject( "admin", false );
				user.setRole( "USER" );
			}
		}
		modelAndView.addObject( "user", user );
		modelAndView.setViewName( "/registration" );
		return modelAndView;
	}

	@RequestMapping( value = "/registration", method = RequestMethod.POST )
	public ModelAndView createUser( @Valid User user, BindingResult bindingResult ) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail( user.getEmail() );
		if ( userExists != null ) {
			bindingResult.rejectValue( "email", "error.user", "There is already a user registered with the email provided" );
		}

		if ( !bindingResult.hasErrors() ) {
			if ( user.getRole() == null || !user.getRole().equals( "ADMIN" ) ) {
				userService.saveUser( user );
			} else {
				userService.saveAdminUser( user );
			}
			modelAndView.addObject( "successMessage", "Admin User has been registered successfully" );
			modelAndView.addObject( "user", new User() );
		}
		modelAndView.setViewName( "/registration" );
		return modelAndView;
	}

	@RequestMapping( value = "/home", method = RequestMethod.GET )
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail( auth.getName() );
		modelAndView.addObject( "userName", "Welcome " + user.getName() + "(" + user.getEmail() + ")" );
		modelAndView.addObject( "auctions", auctionService.findAll() );
		modelAndView.setViewName( "/home" );
		return modelAndView;
	}

}
