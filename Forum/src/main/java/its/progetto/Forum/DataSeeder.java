package its.progetto.Forum;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import its.progetto.Forum.Dao.AcquistoDao;
import its.progetto.Forum.Dao.EsperienzeDao;
import its.progetto.Forum.Dao.RecensioniDao;
import its.progetto.Forum.Dao.UtentiDao;
import its.progetto.Forum.Model.Acquisto;
import its.progetto.Forum.Model.Esperienze;
import its.progetto.Forum.Model.Recensioni;
import its.progetto.Forum.Model.Ruolo;
import its.progetto.Forum.Model.StatoAccount;
import its.progetto.Forum.Model.Utenti;

/**
 * Popola il database con dati fittizi al primo avvio:
 * un utente demo con acquisti gia' fatti (cosi' il dropdown di crea-recensione
 * si popola rispettando la regola BR-02), un admin e alcune recensioni di esempio.
 * Gira dopo data.sql, quindi le esperienze/categorie sono gia' presenti.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final UtentiDao utentiDao;
    private final EsperienzeDao esperienzeDao;
    private final AcquistoDao acquistoDao;
    private final RecensioniDao recensioniDao;

    public DataSeeder(UtentiDao utentiDao, EsperienzeDao esperienzeDao,
                      AcquistoDao acquistoDao, RecensioniDao recensioniDao) {
        this.utentiDao = utentiDao;
        this.esperienzeDao = esperienzeDao;
        this.acquistoDao = acquistoDao;
        this.recensioniDao = recensioniDao;
    }

    @Override
    public void run(String... args) {
        // se l'utente demo esiste gia' non riseminiamo (evita duplicati a ogni avvio)
        if (utentiDao.existsByUsername("mario")) {
            return;
        }
        if (utentiDao.count() > 0) {
            return;
        }
        // --- Admin ---
        Utenti admin = new Utenti();
        admin.setUsername("admin");
        admin.setNome("Anna");
        admin.setCognome("Rossi");
        admin.setEmail("admin@pugliamare.it");
        admin.setPassword("admin123");
        admin.setDataNascita(LocalDate.of(1990, 5, 12));
        admin.setDataRegistrazione(LocalDate.now());
        admin.setStato_account(StatoAccount.ATTIVO);
        admin.setRuolo(Ruolo.ADMIN);
        utentiDao.save(admin);

        // --- Utente demo (con acquisti) ---
        Utenti mario = new Utenti();
        mario.setUsername("mario");
        mario.setNome("Marco");
        mario.setCognome("Pollo");
        mario.setEmail("marco.pollo@example.com");
        mario.setPassword("password123");
        mario.setDataNascita(LocalDate.of(1995, 3, 20));
        mario.setDataRegistrazione(LocalDate.now());
        mario.setStato_account(StatoAccount.ATTIVO);
        mario.setRuolo(Ruolo.UTENTE);
        utentiDao.save(mario);

        // --- Secondo utente per dare varieta' al feed ---
        Utenti giulia = new Utenti();
        giulia.setUsername("giulia");
        giulia.setNome("Giulia");
        giulia.setCognome("Ferri");
        giulia.setEmail("giulia.ferri@example.com");
        giulia.setPassword("password123");
        giulia.setDataNascita(LocalDate.of(1998, 11, 2));
        giulia.setDataRegistrazione(LocalDate.now());
        giulia.setStato_account(StatoAccount.ATTIVO);
        giulia.setRuolo(Ruolo.UTENTE);
        utentiDao.save(giulia);

        // le esperienze arrivano da data.sql
        List<Esperienze> esperienze = esperienzeDao.findAll();
        if (esperienze.isEmpty()) {
            return;
        }

        // --- Acquisti finti: Marco "ha svolto" le prime 3 esperienze ---
        int quanti = Math.min(3, esperienze.size());
        for (int i = 0; i < quanti; i++) {
            Esperienze exp = esperienze.get(i);
            Acquisto acquisto = new Acquisto();
            acquisto.setUtente(mario);
            acquisto.setEsperienza(exp);
            acquisto.setDataAcquisto(LocalDate.now().minusDays(7 + i).toString());
            acquisto.setPrezzo_pagato(exp.getPrezzo() != null ? exp.getPrezzo() : 0);
            acquistoDao.save(acquisto);
        }

        // Giulia ha svolto la prima esperienza
        Acquisto acqGiulia = new Acquisto();
        acqGiulia.setUtente(giulia);
        acqGiulia.setEsperienza(esperienze.get(0));
        acqGiulia.setDataAcquisto(LocalDate.now().minusDays(3).toString());
        acqGiulia.setPrezzo_pagato(esperienze.get(0).getPrezzo() != null ? esperienze.get(0).getPrezzo() : 0);
        acquistoDao.save(acqGiulia);

        // --- Recensioni di esempio, cosi' il feed della home non e' vuoto ---
        salvaRecensione("Esperienza da sogno", 5,
                "Le grotte al tramonto e l'aperitivo in barca. Lo skipper conosceva ogni anfratto della costa. Da rifare!",
                esperienze.get(0), giulia);

        if (esperienze.size() > 1) {
            salvaRecensione("Cooking class top", 5,
                    "Ho imparato a fare orecchiette e focaccia con una chef simpaticissima. Cena finale inclusa. Top!",
                    esperienze.get(1), mario);
        }
    }

    private void salvaRecensione(String titolo, int voto, String testo,
                                 Esperienze esperienza, Utenti autore) {
        Recensioni r = new Recensioni();
        r.setTitolo(titolo);
        r.setVoto(voto);
        r.setTesto(testo);
        r.setEsperienza(esperienza);
        r.setAutore(autore);
        r.setDataCreazione(LocalDate.now());
        recensioniDao.save(r);
    }
}
