package com.rasbet.ui;

import com.rasbet.model.Better;
import com.rasbet.model.RasBetFacade;
import com.rasbet.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        //model.addAttribute("RasBetFacade", RasBetFacade);
        return view;
    }

}
