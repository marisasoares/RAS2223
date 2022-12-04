package com.rasbet.ui;

import com.rasbet.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Login {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam String nome, @RequestParam String email,@RequestParam String password,@RequestParam String nif) {
        RasBetFacade.registerUser(nome,email,password,nif,0);
        return "redirect:login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterPage() {
        return "register";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String verifyLogin(@RequestParam String email,@RequestParam String password ,Model model) {
        String view = "redirect:login?error";
        if(RasBetFacade.login(email,password)) {
            User user = RasBetFacade.getAuthenticatedUser();
            model.addAttribute("games", RasBetFacade.games);
            model.addAttribute("notReadNotifications", RasBetFacade.listNotReadNotifications(RasBetFacade.emailAuthenticatedUser));
            model.addAttribute("user",user);
            if(user instanceof Specialist) view = "redirect;homePageSpecialist";
            else if (user instanceof Administrator) view = "redirect:homePageAdmin";
            else view = "homePageBetter";
        }
        return view;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String returnToMain() {
        return "redirect:homePage";
    }

}