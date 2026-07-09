package its.progetto.Forum.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
public class Acquisto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private String dataAcquisto;

    @NotNull
    private double prezzo_pagato;


    // relazioni le due forein key
    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utenti utente;

    @ManyToOne
    @JoinColumn(name = "esperienza_id")
    private Esperienze esperienza;

    public Acquisto(){}

    public Acquisto(String dataAcquisto, double prezzo_pagato){
        this.dataAcquisto = dataAcquisto;
        this.prezzo_pagato = prezzo_pagato;
    }

    public Acquisto(Long id, String dataAcquisto, double prezzo_pagato ){
        this.id = id;
        this.dataAcquisto = dataAcquisto;
        this.prezzo_pagato = prezzo_pagato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(String data_acqusito) {
        this.dataAcquisto = data_acqusito;
    }

    public double getPrezzo_pagato() {
        return prezzo_pagato;
    }

    public void setPrezzo_pagato(double prezzo_pagato) {
        this.prezzo_pagato = prezzo_pagato;
    }

    public Utenti getUtente() {
        return utente;
    }

    public void setUtente(Utenti utente) {
        this.utente = utente;
    }

    public Esperienze getEsperienza() {
        return esperienza;
    }

    public void setEsperienza(Esperienze esperienza) {
        this.esperienza = esperienza;
    }
}
