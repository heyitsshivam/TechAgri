/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.repository.FeedbackRepository;
import com.example.demo.repository.ContactformRepository;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.CustomUserDetailsService;
import javax.validation.Valid;

import com.example.demo.service.WholesalerService;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.repository.SeedRepo;

/**
 *
 * @author didin
 */
@Controller
public class LoginController {
@Autowired
    ProductRepo productRepo;
    @Autowired
    private CustomUserDetailsService userService;
    @Autowired
    private WholesalerService wholesalerService;
    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    ContactformRepository contactformRepository;
    @Autowired
    SeedRepo seedRepo;


    @RequestMapping(value="/viewseedinfo",method = RequestMethod.GET)
    public String manufacturer(Model model) {
        model.addAttribute("seedList", seedRepo.findAll());
        return "viewseedinfo";
    }

    @RequestMapping(value = "/viewseedinfo", method = RequestMethod.POST)
    public String addseedinfo(@ModelAttribute SeedInfo seedInfo) {
        seedRepo.save(seedInfo);
        return "redirect:viewseedinfo";
    }


    @RequestMapping(value = "/search")
    public String search(@RequestParam String search) {
        return "viewseedinfo";
    }


    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("about");
        return modelAndView;
    }

    @RequestMapping(value = {"/tracking"}, method = RequestMethod.GET)
    public ModelAndView tracking() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tracking");
        return modelAndView;
    }

    @RequestMapping(value = {"/shoppingcart"}, method = RequestMethod.GET)
    public ModelAndView shoppingcart() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shoppingcart");
        return modelAndView;
    }
    @RequestMapping(value = {"/payment"}, method = RequestMethod.GET)
    public ModelAndView chiku() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("payment");
        return modelAndView;
    }
    @RequestMapping(value= {"/shoppingcart"} , method = RequestMethod.POST)
    public String shoppingcart(@ModelAttribute Product product){
        productRepo.save(product);
        return "payment";
    }

    @RequestMapping(value = {"/checkoutform"}, method = RequestMethod.GET)
    public ModelAndView checkoutform() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("checkoutform");
        return modelAndView;
    }
    @RequestMapping(value = {"/governmentschemes"}, method = RequestMethod.GET)
    public ModelAndView governmentschemes() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("governmentschemes");
        return modelAndView;
    }

    @RequestMapping(value = {"/contactform"}, method = RequestMethod.GET)
    public ModelAndView contactform() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contactform");
        return modelAndView;
    }

    @RequestMapping(value= {"/contactform"} , method = RequestMethod.POST)
    public String addfeedback(@ModelAttribute ContactForm contactForm){
        contactformRepository.save(contactForm);
        return "index";
    }


    @RequestMapping(value = {"/feedbackform"}, method = RequestMethod.GET)
    public ModelAndView feedbackform() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("feedbackform");
        return modelAndView;
    }



    @RequestMapping(value= {"/feedbackform"} , method = RequestMethod.POST)
    public String addfeedback(@ModelAttribute Feeduser feeduser){
        feedbackRepository.save(feeduser);
        return "index";
    }



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @RequestMapping(value = "/wholesalelogin", method = RequestMethod.GET)
    public ModelAndView wholesalelogin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("wholesalelogin");
        return modelAndView;
    }



    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @RequestMapping(value = "/wholsesalesignup", method = RequestMethod.GET)
    public ModelAndView wholesalesignup() {
        ModelAndView modelAndView = new ModelAndView();
        Wholesaler wholesaler = new Wholesaler();
        modelAndView.addObject("wholesaler", wholesaler);
        modelAndView.setViewName("wholesalesignup");
        return modelAndView;
    }
    @RequestMapping(value = "/wholesalesignup", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid Wholesaler wholesaler, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        Wholesaler wholesalerExists = wholesalerService.findUserByEmail(wholesaler.getEmail());
        if (wholesalerExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("wholsesalesignup");
        } else {
            wholesalerService.saveUser(wholesaler);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("wholesaler", new Wholesaler());
            modelAndView.setViewName("wholesalelogin");
        }
        return modelAndView;
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("signup");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }


}
