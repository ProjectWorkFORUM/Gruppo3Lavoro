package Model;

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
}
