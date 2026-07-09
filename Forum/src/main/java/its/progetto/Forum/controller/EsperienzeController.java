package its.progetto.Forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import its.progetto.Forum.Dao.EsperienzeDao;

@Controller
public class EsperienzeController {

    @Autowired
    private EsperienzeDao esperienzeDao;

    @GetMapping("/esperienze_lista")
    public String esperienze() {
        return "esperienze";
    }

    @GetMapping("/esperienze/{titolo}")
    public String dettagliEsperienzaPerTitolo(@PathVariable String titolo) {
        // Logica per recuperare i dettagli dell'esperienza con il titolo specificato
        // Puoi utilizzare il dao per ottenere l'esperienza dal database
        // Esperienze esperienza = esperienzeDao.findByTitolo(titolo);
        // Aggiungi l'esperienza al modello se necessario

        return "esperienza/{titolo}"; // Nome della vista per i dettagli dell'esperienza
    }

    @GetMapping("/esperienze/{id}")
    public String dettagliEsperienza(@PathVariable Long id) {
        // Logica per recuperare i dettagli dell'esperienza con l'ID specificato
        // Puoi utilizzare il dao per ottenere l'esperienza dal database
        // Esperienze esperienza = esperienzeDao.findById(id).orElse(null);
        // Aggiungi l'esperienza al modello se necessario

        return "esperienza/{id}";} // Nome della vista per i dettagli dell'esperienza

    @GetMapping("/esperienze/categoria/{idCategoria}")
    public String esperienzePerCategoria(@PathVariable Long idCategoria) {
        // Logica per recuperare le esperienze della categoria specificata
        // Puoi utilizzare il dao per ottenere le esperienze dal database
        // List<Esperienze> esperienze = esperienzeDao.findByCategoriaId(idCategoria);

        return "esperienze/categoria/{idCategoria}"; // Nome della vista per le esperienze della categoria
    }
}