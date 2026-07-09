package its.progetto.Forum.controller;

import its.progetto.Forum.Dao.EsperienzeDao;
import its.progetto.Forum.Dao.RecensioniDao;
import its.progetto.Forum.Dao.RisposteDao;
import its.progetto.Forum.Dao.ThreadDao;
import its.progetto.Forum.Dao.UtentiDao;
import its.progetto.Forum.Model.Ruolo;
import its.progetto.Forum.Model.Utenti;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PageController {

    @Autowired
    private EsperienzeDao esperienzeDao;

    @Autowired
    private ThreadDao threadDao;

    @Autowired
    private RisposteDao risposteDao;

    @Autowired
    private RecensioniDao recensioniDao;

    @Autowired
    private UtentiDao utentiDao;

    @GetMapping("/")
    public String landingPage( ) {
        return "Landing_page";
    }



    @GetMapping("/backoffice")
    public String backofficePage(HttpSession session, Model model,
                                  @RequestParam(name = "tab", required = false, defaultValue = "thread") String tab) {
        Utenti utente = (Utenti) session.getAttribute("loggedUser");

        if (utente == null) {
            return "redirect:/login";
        }
        if (utente.getRuolo() != Ruolo.ADMIN) {
            return "redirect:/home";
        }

        model.addAttribute("threads", threadDao.findByVisibileTrue());
        model.addAttribute("risposte", risposteDao.findByVisibileTrue());
        model.addAttribute("recensioni", recensioniDao.findByVisibileTrue());
        model.addAttribute("utenti", utentiDao.findAll());
        model.addAttribute("activeTab", tab);

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