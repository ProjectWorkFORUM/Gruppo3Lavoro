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
    private String data_acqusito;

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

    public Acquisto(String data_acqusito, double prezzo_pagato){
        this.data_acqusito = data_acqusito;
        this.prezzo_pagato = prezzo_pagato;
    }

    public Acquisto(Long id, String data_acqusito, double prezzo_pagato ){
        this.id = id;
        this.data_acqusito = data_acqusito;
        this.prezzo_pagato = prezzo_pagato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData_acqusito() {
        return data_acqusito;
    }

    public void setData_acqusito(String data_acqusito) {
        this.data_acqusito = data_acqusito;
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
