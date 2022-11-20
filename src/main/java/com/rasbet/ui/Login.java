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

    @GetMapping("/login")
    public String login(Model model) {
        Better b = new Better("Miguel","mig@gmail.com","12345678".hashCode(),"123456789");
        Better b1 = new Better("Benjamim","benja@gmail.com","12345678".hashCode(),"123456789");
        Better b2 = new Better("Diogo","diogo@gmail.com","12345678".hashCode(),"123456789");
        List<User> users = new ArrayList<>();
        users.add(b); users.add(b1);users.add(b2);
        model.addAttribute("users",users);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String verifyLogin(@RequestParam String email,@RequestParam String password ,Model model) {
        String view = "login";
        if(RasBetFacade.login(email,password)) {
            User user = RasBetFacade.getAuthenticatedUser();
            model.addAttribute("user",user);
            if(user instanceof Specialist) view = "homePageSpecialist";
            else if (user instanceof Administrator) view = "homePageAdmin";
            else view = "homePageBetter";
        } else model.addAttribute("invalidLogin",true);
        // TEST --------------
        view = "homePageBetter";
        Better user = new Better("Miguel","mig@gmail.com","12345678".hashCode(),"123456789");
        user.getWallet().setEuros(32.5f);
        user.getWallet().setDollars(23.45f);
        model.addAttribute("user",user);
        model.addAttribute("games",RasBetFacade.games);
        // -------------------- $
        return view;
    }

}