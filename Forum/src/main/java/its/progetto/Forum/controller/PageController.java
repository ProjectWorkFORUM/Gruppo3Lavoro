package its.progetto.Forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class PageController {
    @GetMapping("/")
    public String landingPage( ) {
        return "Landing_page";
    }



    @GetMapping("/backoffice")
    public String backofficePage( ) {
        return "backoffice_page";
    }

    @GetMapping("/dashboard")
    public String dashboardPage( ) {
        return "dashboard_page";
    }

    @GetMapping("/esperienze")
    public String esperienzePage( ) {
        return "Esperienza_page";
    }
    
    @GetMapping("/home")
    public String homePage( ) {
        return "Home_page";
    }

    @GetMapping("/profilo")
    public String profiloPage( ) {
        return "profile_page";
    }

    @GetMapping("/profilo-personale")
    public String profiloPersonalePage( ) {
        return "Personal-profile_page";
    }

    @GetMapping("/recensione")
    public String recensionePage( ) {
        return "crea-recensione";
    }

}