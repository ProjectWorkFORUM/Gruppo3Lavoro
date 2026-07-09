package its.progetto.Forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import its.progetto.Forum.Dao.RecensioniDao;
import its.progetto.Forum.Dao.RisposteDao;
import its.progetto.Forum.Dao.ThreadDao;
import its.progetto.Forum.Dao.UtentiDao;
import its.progetto.Forum.Model.Recensioni;
import its.progetto.Forum.Model.Risposte;
import its.progetto.Forum.Model.Ruolo;
import its.progetto.Forum.Model.StatoAccount;
import its.progetto.Forum.Model.Thread;
import its.progetto.Forum.Model.Utenti;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UtentiDao utentiDao;
    private final ThreadDao threadDao;
    private final RisposteDao risposteDao;
    private final RecensioniDao recensioniDao;

    @Autowired
    public AdminController(UtentiDao utentiDao,
                            ThreadDao threadDao,
                            RisposteDao risposteDao,
                            RecensioniDao recensioniDao) {
        this.utentiDao = utentiDao;
        this.threadDao = threadDao;
        this.risposteDao = risposteDao;
        this.recensioniDao = recensioniDao;
    }

    /**
     * Restituisce l'utente corrente memorizzato nella sessione.
     */
    private Utenti utenteSessione(HttpSession session) {
        return (Utenti) session.getAttribute("loggedUser");
    }

    /**
     * Verifica se l'utente della sessione ha il ruolo ADMIN.
     */
    private boolean isAdmin(HttpSession session) {
        Utenti utente = utenteSessione(session);
        return utente != null && utente.getRuolo() == Ruolo.ADMIN;
    }

    /**
     * Disattiva un account utente invece di eliminarlo fisicamente.
     */
    @PostMapping("/utenti/{id}/elimina")
    public String eliminaUtente(@PathVariable(name = "id") Long id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";

        utentiDao.findById(id).ifPresent(utente -> {
            utente.setStato_account(StatoAccount.DISATTIVATO);
            utentiDao.save(utente);
        });

        return "redirect:/backoffice?tab=utenti";
    }

    /**
     * Riattiva un account utente precedentemente disattivato.
     */
    @PostMapping("/utenti/{id}/riabilita")
    public String riabilitaUtente(@PathVariable(name = "id") Long id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";

        utentiDao.findById(id).ifPresent(utente -> {
            utente.setStato_account(StatoAccount.ATTIVO);
            utentiDao.save(utente);
        });

        return "redirect:/backoffice?tab=utenti";
    }

    /**
     * Nasconde un thread e tutte le risposte associate.
     */
    @Transactional
    @PostMapping("/threads/{id}/elimina")
    public String eliminaThread(@PathVariable(name = "id") Long id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";

        Thread thread = threadDao.findById(id).orElse(null);

        if (thread != null) {
            thread.setVisibile(false);
            threadDao.save(thread);

            List<Risposte> risposte = risposteDao.findByThread(thread);
            for (Risposte risposta : risposte) {
                risposta.setVisibile(false);
            }
            risposteDao.saveAll(risposte);
        }

        return "redirect:/backoffice?tab=thread";
    }

    /**
     * Nasconde una singola risposta invece di eliminarla fisicamente.
     */
    @PostMapping("/risposte/{id}/elimina")
    public String eliminaRisposta(@PathVariable(name = "id") Long id,
                                   @RequestParam(name = "redirectTo", required = false) String redirectTo,
                                   HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";

        risposteDao.findById(id).ifPresent(risposta -> {
            risposta.setVisibile(false);
            risposteDao.save(risposta);
        });

        return "redirect:" + (redirectTo != null && !redirectTo.isBlank()
                ? redirectTo
                : "/backoffice?tab=risposte");
    }

    /**
     * Nasconde una recensione invece di eliminarla fisicamente.
     */
    @PostMapping("/recensioni/{id}/elimina")
    public String eliminaRecensione(@PathVariable(name = "id") Long id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";

        recensioniDao.findById(id).ifPresent(recensione -> {
            recensione.setVisibile(false);
            recensioniDao.save(recensione);
        });

        return "redirect:/backoffice?tab=recensioni";
    }
}
