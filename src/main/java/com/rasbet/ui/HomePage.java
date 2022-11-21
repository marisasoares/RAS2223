package com.rasbet.ui;

import com.rasbet.model.Better;
import com.rasbet.model.RasBetFacade;
import com.rasbet.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomePage {

    @GetMapping("/homePage")
    public String showHomePage(Model model) {
        model.addAttribute("games", RasBetFacade.games);
        return "homePageBetter";
    }
}
