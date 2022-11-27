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
        String view = "homePageBetter";
        if(RasBetFacade.emailAuthenticatedUser == null) view="redirect:login";
        model.addAttribute("user", RasBetFacade.getAuthenticatedUser());
        model.addAttribute("games", RasBetFacade.games);
        return view;
    }
}
