package its.progetto.Forum.controller;

import its.progetto.Forum.Dao.EsperienzeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class PageController {

    @Autowired
    private EsperienzeDao esperienzeDao;
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

    @GetMapping("/Thread")
    public String threadPage( ) {
        return "Thread_page";
    }

    @GetMapping("/recensione")
    public String recensionePage(Model model) {
        model.addAttribute("esperienze", esperienzeDao.findAll());
        return "crea-recensione";
    }

}