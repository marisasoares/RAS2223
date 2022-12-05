package com.rasbet.ui;

import com.rasbet.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/utilizadores")
    public String showUtilizadores(Model model) {
        String view = "redirect:login";
        if(RasBetFacade.emailAuthenticatedUser == null) return view;
        model.addAttribute("user",RasBetFacade.getAuthenticatedUser());
        model.addAttribute("utilizadores", RasBetFacade.getBetters());
        if(RasBetFacade.getAuthenticatedUser() instanceof Administrator)view = "listarAdmin";
        if(RasBetFacade.getAuthenticatedUser() instanceof Specialist)view = "listarSpec";
        return view;
    }

    @GetMapping("/administradores")
    public String showAdmins(Model model) {
        String view = "redirect:login";
        if(RasBetFacade.emailAuthenticatedUser == null) return view;
        model.addAttribute("user",RasBetFacade.getAuthenticatedUser());
        model.addAttribute("administradores", RasBetFacade.getAdmins());
        if(RasBetFacade.getAuthenticatedUser() instanceof Administrator)view = "listarAdmin";
        if(RasBetFacade.getAuthenticatedUser() instanceof Specialist)view = "listarSpec";
        return view;
    }

    @GetMapping("/especialistas")
    public String showEspecialistas(Model model) {
        String view = "redirect:login";
        if(RasBetFacade.emailAuthenticatedUser == null) return view;
        model.addAttribute("user",RasBetFacade.getAuthenticatedUser());
        model.addAttribute("especialistas", RasBetFacade.getSpecialist());
        if(RasBetFacade.getAuthenticatedUser() instanceof Administrator)view = "listarAdmin";
        if(RasBetFacade.getAuthenticatedUser() instanceof Specialist)view = "listarSpec";
        return view;
    }

    @GetMapping("/desportos")
    public String showDesportos(Model model) {
        String view = "redirect:login";
        if(RasBetFacade.emailAuthenticatedUser == null) return view;
        model.addAttribute("user",RasBetFacade.getAuthenticatedUser());
        model.addAttribute("desportos", RasBetFacade.getSportList());
        if(RasBetFacade.getAuthenticatedUser() instanceof Administrator)view = "listarAdmin";
        if(RasBetFacade.getAuthenticatedUser() instanceof Specialist)view = "listarSpec";
        return view;
    }
}