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

import java.util.*;

@Controller
public class HomePage {

    @GetMapping("/homePage")
    public String showHomePage(Model model) {
        String view = "homePageBetter";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        model.addAttribute("games", RasBetFacade.getGames());
        model.addAttribute("notReadNotifications",RasBetFacade.listNotReadNotifications(RasBetFacade.getEmailAuthenticatedUser()));
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
        model.addAttribute("betsMultiple", RasBetFacade.getMultipleBetsListByEmail(RasBetFacade.emailAuthenticatedUser));
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
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        if(currency.equals("euros")){
            if(value >= 0) RasBetFacade.addMovementEuros(value,RasBetFacade.emailAuthenticatedUser,"Deposito Euros");
        }
        else {
            if(value >= 0) RasBetFacade.addMovementDollars(value,RasBetFacade.emailAuthenticatedUser,"Deposito Dollars");
        }
        return view;
    }

    @RequestMapping(value = "/depositar", method = RequestMethod.GET)
    public String depositar(Model model) {
        String view = "depositar";
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        return view;
    }

    @RequestMapping(value = "/levantar", method = RequestMethod.POST)
    public String levantar(Model model,@RequestParam String currency,@RequestParam float value) {
        String view = "redirect:profile";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        if(currency.equals("euros")){
            if(value >= 0) RasBetFacade.addMovementEuros(-value,RasBetFacade.emailAuthenticatedUser,"Levantamento Euros");
        }
        else {
            if(value >= 0) RasBetFacade.addMovementDollars(-value,RasBetFacade.emailAuthenticatedUser,"Levantamento Dollars");
        }
        return view;
    }
    @RequestMapping(value = "/levantar", method = RequestMethod.GET)
    public String levantar(Model model) {
        String view = "levantar";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        return view;
    }

    @RequestMapping(value = "/bet", method = RequestMethod.POST)
    public String makeBet(Model model, @RequestParam(value = "gameId") String[] gameIds, @RequestParam(value = "bettedTeam") int[] bettedTeams, @RequestParam float oddTotal, @RequestParam int multipleId, @RequestParam float bettedValue, @RequestParam String currency, @RequestParam String betType) {
        String view = "redirect:homePage";
        if(betType.equals("simples")) multipleId = 0;
        else multipleId = new Random().nextInt();
        RasBetFacade.addBet(gameIds,RasBetFacade.getEmailAuthenticatedUser(),bettedValue,bettedTeams,multipleId,currency,oddTotal*bettedValue);
        return view;
    }


}
