package its.progetto.Forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import its.progetto.Forum.Dao.CategoriaDao;
import its.progetto.Forum.Dao.EsperienzeDao;
import its.progetto.Forum.Dao.RecensioniDao;
import its.progetto.Forum.Model.Esperienze;
import its.progetto.Forum.Model.Recensioni;
import jakarta.servlet.http.HttpSession;

@Controller
public class EsperienzeController {

    @Autowired
    private EsperienzeDao esperienzeDao;

    @Autowired
    private CategoriaDao categoriaDao;

    @Autowired
    private RecensioniDao recensioniDao;

    // lista delle esperienze, con filtro opzionale per categoria (?categoriaId=..)
    @GetMapping("/esperienze")
    public String listaEsperienze(@RequestParam(name = "categoriaId", required = false) Long categoriaId,
                                  Model model) {
        List<Esperienze> esperienze = (categoriaId != null)
                ? esperienzeDao.findByCategoria_Id(categoriaId)
                : esperienzeDao.findAll();

        model.addAttribute("esperienze", esperienze);
        model.addAttribute("categorie", categoriaDao.findAll());
        model.addAttribute("categoriaAttiva", categoriaId);
        return "esperienze-lista";
    }

    // dettaglio di una singola esperienza con le sue recensioni
    @GetMapping("/esperienze/{id}")
    public String dettagliEsperienza(@PathVariable Long id, Model model, HttpSession session) {
        Esperienze esperienza = esperienzeDao.findById(id).orElse(null);
        if (esperienza == null) {
            return "redirect:/esperienze";
        }

        List<Recensioni> recensioni = recensioniDao.findByEsperienzaIdAndVisibileTrue(id);
        double mediaVoti = recensioni.stream().mapToInt(Recensioni::getVoto).average().orElse(0);

        model.addAttribute("esperienza", esperienza);
        model.addAttribute("recensioni", recensioni);
        model.addAttribute("numRecensioni", recensioni.size());
        model.addAttribute("mediaVoti", Math.round(mediaVoti * 10) / 10.0);
        model.addAttribute("user", session.getAttribute("loggedUser"));
        return "Esperienza_page";
    }
}
