package Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Audited;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Esperienze {
    @Id
    private Long id;

    @Size(min= 4, max=20)
    private String titolo;


    @Size(min = 5, max=50 )
    private String descrizione;


    // valutare se inserire un prezzo massimo o minimo di esperienza nel analisi
    @Min(value=10,  message  = "Il prezzo del esperienza deve essere di almeno 10 euro")
    private Double prezzo;


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Set<Categorie> categorie = new HashSet<>();
}
