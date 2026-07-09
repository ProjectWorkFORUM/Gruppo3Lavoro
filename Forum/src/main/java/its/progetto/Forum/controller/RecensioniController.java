package its.progetto.Forum.controller;

import its.progetto.Forum.Dao.AcquistoDao;
import its.progetto.Forum.Dao.EsperienzeDao;
import its.progetto.Forum.Dao.RecensioniDao;
import its.progetto.Forum.Model.Acquisto;
import its.progetto.Forum.Model.Esperienze;
import its.progetto.Forum.Model.LoginForm;
import its.progetto.Forum.Model.Recensioni;
import its.progetto.Forum.Model.Utenti;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;


// creazione recensioni
// deve essere loggate
// deve aver acquistato un esperienza

@Controller
public class RecensioniController {

    @Autowired
    private RecensioniDao recensioniDao;

    @Autowired
    private EsperienzeDao esperienzeDao;

    @Autowired
    private AcquistoDao acquistoDao;

    // recensioni/399393939
    // prendere id_esperienza
    // id_ utente
    // id_recensione

// creazione recensine
    @GetMapping("/esperienze/{esperienzaId}/recensioni/nuova")
    public String nuovaRecensione(@PathVariable Long esperienzaId,
                                  Recensioni recensioni,
                                  Model model,
                                  HttpSession session) {

        Utenti loggato = (Utenti) session.getAttribute("loggedUser");
        if (loggato == null) {
            return "redirect:/login";
        }

        // BR-02: può recensire solo esperienze che ha acquistato
        if (!acquistoDao.existsByUtenteIdAndEsperienzaId(loggato.getId(), esperienzaId)) {
            return "redirect:/esperienze/" + esperienzaId + "?nonAcquistata";
        }

        model.addAttribute("esperienza", esperienzeDao.findById(esperienzaId).orElseThrow());
        model.addAttribute("esperienze", esperienzeDao.findAll());
        return "crea-recensione";
    }

    @PostMapping("/esperienze/{esperienzaId}/recensioni")
    public String salvaRecensione(@PathVariable Long esperienzaId,
                                  @Valid Recensioni recensioni,
                                  BindingResult bindingResult,
                                  HttpSession session,
                                  Model model) {

        Utenti loggato = (Utenti) session.getAttribute("loggedUser");
        if (loggato == null) {
            return "redirect:/login";
        }

        Esperienze esperienza = esperienzeDao.findById(esperienzaId).orElseThrow();

        // sei loggato

        if (!acquistoDao.existsByUtenteIdAndEsperienzaId(loggato.getId(), esperienzaId)) {
            return "redirect:/esperienze/" + esperienzaId + "?nonAcquistata";
        }

        // hai comprato l'esperienza

        if (bindingResult.hasErrors()) {
            model.addAttribute("esperienza", esperienza);
            model.addAttribute("esperienze", acquistoDao.findByUtenteId(loggato.getId())
                    .stream()
                    .map(Acquisto::getEsperienza)
                    .distinct()
                    .toList());
            return "crea-recensione";
        }

        recensioni.setEsperienza(esperienza);
        recensioni.setAutore(loggato);
        recensioni.setDataCreazione(LocalDate.now());
        recensioniDao.save(recensioni);

        return "redirect:/home";
    }






}