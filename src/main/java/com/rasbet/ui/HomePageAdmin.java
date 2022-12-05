package com.rasbet.ui;

import com.rasbet.model.Game;
import com.rasbet.model.RasBetFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class HomePageAdmin {

    @GetMapping("/homePageAdmin")
    public String showHomePage(Model model) {
        String view = "HomePageAdmin";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        model.addAttribute("games", RasBetFacade.getGames());
        return view;
    }
    @GetMapping("/homePageSpec")
    public String showHomePageSpec(Model model) {
        String view = "HomePageSpec";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        model.addAttribute("games", RasBetFacade.getGames());
        return view;
    }

    @GetMapping("/profileAdmin")
    public String showProfile(Model model) {
        String view = "profileAdmin";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        return view;
    }
    @GetMapping("/profileSpec")
    public String showProfileSpec(Model model) {
        String view = "profileSpec";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        return view;
    }
    @GetMapping("/addAdmin")
    public String addAdmin(Model model) {
        String view = "registarAdmin";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        model.addAttribute("add", "admin");
        return view;
    }
    @GetMapping("/addEspecialista")
    public String addEspecialista(Model model) {
        String view = "registarAdmin";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        model.addAttribute("add", "spec");
        return view;
    }
    @GetMapping("/addJogo")
    public String addJogo(Model model) {
        String view = "registarAdmin";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        model.addAttribute("add", "jogo");
        return view;
    }
    @RequestMapping(value = "/registerAdmin", method = RequestMethod.POST)
    public String registerAdmin(@RequestParam String nomeAdmin, @RequestParam String emailAdmin,@RequestParam String passwordAdmin) {
        RasBetFacade.registerUser(nomeAdmin,emailAdmin,passwordAdmin,"999999999",2);
        return "redirect:profileAdmin";
    }
    @RequestMapping(value = "/registerSpec", method = RequestMethod.POST)
    public String registerSpec(@RequestParam String nome, @RequestParam String email,@RequestParam String password) {
        RasBetFacade.registerUser(nome,email,password,"999999999",1);
        return "redirect:profileAdmin";
    }
    @RequestMapping(value = "/registerJogo", method = RequestMethod.POST)
    public String registerJogo(@RequestParam String nome, @RequestParam String email,@RequestParam String password) {
        //Game game = new Game( )
        //RasBetFacade.addGame(game);
        return "redirect:profileAdmin";
    }

    @RequestMapping(value = "/changeOdd", method = RequestMethod.POST)
    public String changeOdds(@RequestParam float[] oddHomeTeam, @RequestParam float[] oddEmpate, @RequestParam float[] oddAwayTeam, @RequestParam String[] gameId) {
        System.out.println("odd homet: " + oddHomeTeam.length);
        System.out.println("odd emp: " + oddHomeTeam.length);
        System.out.println("odd awayt: " + oddHomeTeam.length);

        for (int i = 0; i < oddHomeTeam.length - 1; i++) {
            RasBetFacade.inserirChange(oddHomeTeam[i],gameId[i],0);
            System.out.println("Game: " + gameId[i] + " oddHome: " + oddHomeTeam[i]);
            RasBetFacade.inserirChange(oddAwayTeam[i],gameId[i],1);
            System.out.println("Game: " + gameId[i] + " oddAway: " + oddAwayTeam[i]);
            RasBetFacade.inserirChange(oddEmpate[i],gameId[i],2);
            System.out.println("Game: " + gameId[i] + " oddEmpate: " + oddEmpate[i]);

        }
        return "redirect:homePageSpec";
    }

    @RequestMapping(value = "/changeOddAdmin", method = RequestMethod.POST)
    public String changeOddsAdmin(@RequestParam float[] oddHomeTeam, @RequestParam float[] oddEmpate, @RequestParam float[] oddAwayTeam, @RequestParam String[] gameId) {
        System.out.println("odd homet: " + oddHomeTeam.length);
        System.out.println("odd emp: " + oddHomeTeam.length);
        System.out.println("odd awayt: " + oddHomeTeam.length);

        for (int i = 0; i < oddHomeTeam.length - 1; i++) {
            RasBetFacade.inserirChange(oddHomeTeam[i],gameId[i],0);
            System.out.println("Game: " + gameId[i] + " oddHome: " + oddHomeTeam[i]);
            RasBetFacade.inserirChange(oddAwayTeam[i],gameId[i],1);
            System.out.println("Game: " + gameId[i] + " oddAway: " + oddAwayTeam[i]);
            RasBetFacade.inserirChange(oddEmpate[i],gameId[i],2);
            System.out.println("Game: " + gameId[i] + " oddEmpate: " + oddEmpate[i]);

        }
        return "redirect:homePageAdmin";
    }
}
