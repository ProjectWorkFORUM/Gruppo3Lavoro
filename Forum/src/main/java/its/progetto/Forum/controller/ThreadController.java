package its.progetto.Forum.controller;

import its.progetto.Forum.Model.Esperienze;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import its.progetto.Forum.Dao.AcquistoDao;
import its.progetto.Forum.Dao.EsperienzeDao;
import its.progetto.Forum.Dao.ThreadDao;
import its.progetto.Forum.Model.Thread;
import its.progetto.Forum.Model.Utenti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ThreadController {

    @Autowired
    private ThreadDao threadDao;
    @Autowired
    private AcquistoDao acquistoDao;
    @Autowired
    private EsperienzeDao esperienzeDao;

    @GetMapping("/Threads")
        public String listaThread (Model model, HttpSession session){
        List<Thread> lista = threadDao.findAll();
        model.addAttribute("listaThread", lista);
        model.addAttribute("user", session.getAttribute("loggedUser"));
        return "Thread_page";
    };

    // discussioni di una singola esperienza: aperte a tutti (anche non loggati) in visualizzazione
    @GetMapping("/esperienze/{esperienzaId}/discussioni")
    public String discussioniEsperienza(@PathVariable(name = "esperienzaId") Long esperienzaId, Model model, HttpSession session){
        Esperienze esperienza = esperienzeDao.findById(esperienzaId).orElse(null);
        if(esperienza == null){
            return "redirect:/esperienze";
        }
        model.addAttribute("esperienza", esperienza);
        model.addAttribute("listaThread", threadDao.findByEsperienzaIdAndVisibileTrue(esperienzaId));
        model.addAttribute("user", session.getAttribute("loggedUser"));
        return "Thread_page";
    }

    @GetMapping("/esperienze/{esperienzaId}/Thread/nuovo")
    public String nuovoThread(@PathVariable(name = "esperienzaId") Long esperienzaId, Thread thread, Model model, HttpSession session){
        Utenti loggato = (Utenti) session.getAttribute("loggedUser");
        if(loggato == null){
            return "redirect:/login";
        }
        if(!acquistoDao.existsByUtenteIdAndEsperienzaId(loggato.getId(), esperienzaId)){
            return "redirect:/esperienze";
        }
        model.addAttribute("esperienza", esperienzeDao.findById(esperienzaId).orElseThrow());
        model.addAttribute("user", loggato);
        return "Thread_page";

    }

    @PostMapping("/esperienze/{esperienzaId}/Thread")
    public String salvaThread(@PathVariable(name = "esperienzaId") Long esperienzaId, @Valid Thread thread, BindingResult bindingResult, HttpSession session, Model model){
        Utenti loggato = (Utenti) session.getAttribute("loggedUser");
        if(loggato == null){
            return "redirect:/login";
        }

        Esperienze esperienza = esperienzeDao.findById(esperienzaId).orElseThrow();

        // le discussioni sono aperte a qualsiasi utente loggato (nessun vincolo di acquisto)
        if(bindingResult.hasErrors()){
            model.addAttribute("esperienza", esperienza);
            model.addAttribute("listaThread", threadDao.findByEsperienzaIdAndVisibileTrue(esperienzaId));
            model.addAttribute("user", loggato);
            return "Thread_page";
        }
        thread.setData_apertura(java.time.LocalDate.now().toString());
        thread.setStato(true);
        thread.setEsperienza(esperienza);
        threadDao.save(thread);
        return "redirect:/esperienze/" + esperienzaId + "/discussioni";
    }
}
