package its.progetto.Forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import its.progetto.Forum.Dao.AcquistoDao;
import its.progetto.Forum.Dao.EsperienzeDao;
import its.progetto.Forum.Dao.RecensioniDao;
import its.progetto.Forum.Dao.RisposteDao;
import its.progetto.Forum.Dao.ThreadDao;
import its.progetto.Forum.Dao.UtentiDao;
import its.progetto.Forum.Model.Acquisto;
import its.progetto.Forum.Model.Esperienze;
import its.progetto.Forum.Model.Recensioni;
import its.progetto.Forum.Model.Ruolo;
import its.progetto.Forum.Model.Utenti;
import jakarta.servlet.http.HttpSession;


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

    @Autowired
    private AcquistoDao acquistoDao;

    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("recensioni", recensioniDao.findByVisibileTrueOrderByIdDesc());
        return "Home_page";
    }



    @GetMapping("/backoffice")
    public String backofficePage(HttpSession session, Model model,
                                  @RequestParam(name = "tab", required = false, defaultValue = "thread") String tab) {
        Utenti utente = (Utenti) session.getAttribute("loggedUser");

        if (utente == null) {
            return "redirect:/login";
        }
        if (utente.getRuolo() != Ruolo.ADMIN) {
            return "redirect:/";
        }

        model.addAttribute("threads", threadDao.findByVisibileTrue());
        model.addAttribute("risposte", risposteDao.findByVisibileTrue());
        model.addAttribute("recensioni", recensioniDao.findByVisibileTrue());
        model.addAttribute("utenti", utentiDao.findAll());
        model.addAttribute("activeTab", tab);

        return "backoffice_page";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(HttpSession session, Model model) {
        Utenti loggato = (Utenti) session.getAttribute("loggedUser");
        if (loggato == null) {
            return "redirect:/login";
        }
        model.addAttribute("ultimeRecensioni", recensioniDao.findByAutoreIdAndVisibileTrueOrderByIdDesc(loggato.getId()));
        model.addAttribute("numAcquisti", acquistoDao.findByUtenteId(loggato.getId()).size());
        return "dashboard_page";
    }

 

    @GetMapping("/profilo")
    public String profiloPage(HttpSession session, Model model) {
        Utenti loggato = (Utenti) session.getAttribute("loggedUser");
        if (loggato == null) {
            return "redirect:/login";
        }

        List<Recensioni> recensioni = recensioniDao.findByAutoreIdAndVisibileTrueOrderByIdDesc(loggato.getId());
        model.addAttribute("profilo", loggato);
        model.addAttribute("recensioni", recensioni);
        model.addAttribute("numRecensioni", recensioni.size());
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


    public String recensionePage(Recensioni recensioni, Model model, HttpSession session) {
        Utenti loggato = (Utenti) session.getAttribute("loggedUser");
        if (loggato == null) {
            return "redirect:/login";
        }

        List<Esperienze> acquistate = acquistoDao.findByUtenteId(loggato.getId())
                .stream()
                .map(Acquisto::getEsperienza)
                .distinct()
                .toList();

        model.addAttribute("esperienze", acquistate);
        return "crea-recensione";
    }
}