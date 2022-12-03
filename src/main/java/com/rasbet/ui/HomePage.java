package com.rasbet.ui;

import com.rasbet.model.Better;
import com.rasbet.model.RasBetFacade;
import com.rasbet.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class HomePage {

    @GetMapping("/homePage")
    public String showHomePage(Model model) {
        String view = "homePageBetter";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        model.addAttribute("games", RasBetFacade.games);
        return view;
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        String view = "profile";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        return view;
    }
    @GetMapping("/saveChanges")
    public String showSaveChanges(Model model, @RequestParam String nome,@RequestParam String email,@RequestParam String password) {
        String view = "redirect:profile";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        if(Objects.equals(password, "")) {
            RasBetFacade.changeInfo(nome,email);
        }
        else RasBetFacade.changeInfo(nome, password, email);
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        return view;
    }
    @GetMapping("/betHistory")
    public String showBetHistory(Model model) {
        String view = "betHistory";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        model.addAttribute("betsSimple", RasBetFacade.getSimpleBetsListByEmail(RasBetFacade.emailAuthenticatedUser));
        return view;
    }
    @GetMapping("/logout")
    public String logout(Model model) {
        RasBetFacade.emailAuthenticatedUser = null;
        return "redirect:login";
    }


    @RequestMapping(value = "/depositar", method = RequestMethod.POST)
    public String depositar(Model model,@RequestParam String currency,@RequestParam float value) {
        String view = "redirect:profile";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        if(currency.equals("euros")){
            RasBetFacade.addMovementEuros(value,RasBetFacade.emailAuthenticatedUser,"Deposito");
        }
        else RasBetFacade.addMovementDollars(value,RasBetFacade.emailAuthenticatedUser,"Deposito");
        return view;
    }

    @RequestMapping(value = "/depositar", method = RequestMethod.GET)
    public String depositar(Model model) {
        String view = "depositar";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        return view;
    }

    @RequestMapping(value = "/levantar", method = RequestMethod.POST)
    public String levantar(Model model,@RequestParam String currency,@RequestParam float value) {
        String view = "redirect:profile";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        if(currency.equals("euros")){
            RasBetFacade.addMovementEuros(-value,RasBetFacade.emailAuthenticatedUser,"Levantamento");
        }
        else RasBetFacade.addMovementDollars(-value,RasBetFacade.emailAuthenticatedUser,"Levantamento");
        return view;
    }
    @RequestMapping(value = "/levantar", method = RequestMethod.GET)
    public String levantar(Model model) {
        String view = "levantar";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        return view;
    }

}
