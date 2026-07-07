package its.progetto.Forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class PageController {
    @GetMapping("/")
    public String landingPage( ) {
        return "Landing_page";
    }

    @GetMapping("/login")
    public String loginPage( ) {
        return "Login_page";
    }

    @GetMapping("/register")
    public String registerPage( ) {
        return "Register_page";
    }

    @GetMapping("/backoffice")
    public String backofficePage( ) {
        return "Backoffice_page";
    }

    @GetMapping("/dashboard")
    public String dashboardPage( ) {
        return "Dashboard_page";
    }

    @GetMapping("/esperienze")
    public String esperienzePage( ) {
        return "Esperienze_page";
    }
    
    @GetMapping("/home")
    public String homePage( ) {
        return "Home_page";
    }

    @GetMapping("/profilo")
    public String profiloPage( ) {
        return "Profilo_page";
    }

    @GetMapping("/profilo-personale")
    public String profiloPersonalePage( ) {
        return "Profilo_personale_page";
    }

}