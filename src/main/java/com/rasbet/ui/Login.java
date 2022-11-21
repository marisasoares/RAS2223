package com.rasbet.ui;

import com.rasbet.data.UserDAO;
import com.rasbet.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class Login {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("games", RasBetFacade.games);
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam String nome, @RequestParam String email,@RequestParam String password,@RequestParam String nif,Model model) {
        RasBetFacade.registerUser(nome,email,password,nif,0);
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterPage(Model model) {
        return "register";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String verifyLogin(@RequestParam String email,@RequestParam String password ,Model model) {
        String view = "login";
        if(RasBetFacade.login(email,password)) {
            User user = RasBetFacade.getAuthenticatedUser();
            model.addAttribute("user",user);
            if(user instanceof Specialist) view = "homePageSpecialist";
            else if (user instanceof Administrator) view = "homePageAdmin";
            else view = "homePageBetter";
        } else model.addAttribute("invalidLogin",true);
        model.addAttribute("games", RasBetFacade.games);
        return view;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String returnToMain(Model model) {
        return "redirect:login";
    }

}