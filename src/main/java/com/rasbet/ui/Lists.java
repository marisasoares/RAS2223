package com.rasbet.ui;

import com.rasbet.data.UserDAO;
import com.rasbet.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class Lists {

    @GetMapping("/notifications")
    public String showNotifications(Model model) {
        String view = "redirect:login";
        if(RasBetFacade.emailAuthenticatedUser == null) return view;
        model.addAttribute("user",RasBetFacade.getAuthenticatedUser());
        model.addAttribute("notifications", RasBetFacade.listAllNotifications(RasBetFacade.emailAuthenticatedUser));
        RasBetFacade.markNotificationsAsRead(RasBetFacade.emailAuthenticatedUser);
        view = "notifications";
        return view;
    }

    @GetMapping("/transactions")
    public String showTransactions(Model model) {
        String view = "redirect:login";
        if(RasBetFacade.emailAuthenticatedUser == null) return view;
        model.addAttribute("user",RasBetFacade.getAuthenticatedUser());
        model.addAttribute("transactions", RasBetFacade.transHistory(RasBetFacade.emailAuthenticatedUser));
        view = "transactions";
        return view;
    }
}