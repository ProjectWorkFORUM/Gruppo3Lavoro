package its.progetto.Forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import its.progetto.Forum.Dao.AcquistoDao;
import its.progetto.Forum.Dao.RisposteDao;
import its.progetto.Forum.Dao.ThreadDao;
import its.progetto.Forum.Model.Risposte;
import its.progetto.Forum.Model.Thread;
import its.progetto.Forum.Model.Utenti;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class RisposteController {

    @Autowired
    private RisposteDao risposteDao;
    @Autowired
    private ThreadDao threadDao;
    @Autowired
    private AcquistoDao acquistoDao;

    // Un Acquisto è legato a un'Esperienza, non direttamente al Thread:
    // per sapere se l'utente può rispondere risaliamo dal thread alla sua esperienza.
    private boolean puoRispondere(Utenti utente, Thread thread) {
        return utente != null
                && thread != null
                && thread.getEsperienza() != null
                && acquistoDao.existsByUtenteIdAndEsperienzaId(utente.getId(), thread.getEsperienza().getId());
    }

    // Lista delle risposte di un thread: visibile a tutti (anche non loggati).
    @GetMapping("/Threads/{threadId}/Risposte")
    public String listaRisposte(@PathVariable(name = "threadId") Long threadId, Model model, HttpSession session) {
        Thread thread = threadDao.findById(threadId).orElse(null);
        if (thread == null) {
            return "redirect:/Threads";
        }
        Utenti loggato = (Utenti) session.getAttribute("loggedUser");
        model.addAttribute("thread", thread);
        model.addAttribute("listaRisposte", risposteDao.findByThreadIdAndVisibileTrueOrderByIdAsc(threadId));
        model.addAttribute("puoRispondere", puoRispondere(loggato, thread));
        return "Risposte_page";
    }

    // Form per una nuova risposta: solo utenti che hanno acquistato l'esperienza del thread.
    @GetMapping("/Threads/{threadId}/Risposte/nuova")
    public String nuovaRisposta(@PathVariable(name = "threadId") Long threadId, Risposte risposte, Model model, HttpSession session) {
        Utenti loggato = (Utenti) session.getAttribute("loggedUser");
        if (loggato == null) {
            return "redirect:/login";
        }
        Thread thread = threadDao.findById(threadId).orElse(null);
        if (thread == null) {
            return "redirect:/Threads";
        }
        if (!puoRispondere(loggato, thread)) {
            return "redirect:/Threads/" + threadId + "/Risposte";
        }
        model.addAttribute("thread", thread);
        return "Risposta_form";
    }

    // Salvataggio della nuova risposta (POST): stessi controlli del form.
    @PostMapping("/Threads/{threadId}/Risposte/salva")
    public String salvaRisposta(@PathVariable(name = "threadId") Long threadId, @Valid Risposte risposte, BindingResult bindingResult, HttpSession session, Model model) {
        Utenti loggato = (Utenti) session.getAttribute("loggedUser");
        if (loggato == null) {
            return "redirect:/login";
        }
        Thread thread = threadDao.findById(threadId).orElse(null);
        if (thread == null) {
            return "redirect:/Threads";
        }
        if (!puoRispondere(loggato, thread)) {
            return "redirect:/Threads/" + threadId + "/Risposte";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("thread", thread);
            return "Risposta_form";
        }
        risposte.setThread(thread);
        risposte.setUtente(loggato);
        risposte.setData_creazione(java.time.LocalDate.now().toString());
        risposte.setVisibile(true);
        risposteDao.save(risposte);
        return "redirect:/Threads/" + threadId + "/Risposte";
    }
}
