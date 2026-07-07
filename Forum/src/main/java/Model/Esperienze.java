package Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Esperienze {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    @NotNull(message = "ID richiesto")
    private Long id;

    @Size(min= 4, max=20)
    @NotBlank(message = "Titolo non può essere vuoto")
    private String titolo;


    @Size(min = 5, max=50 ) 
    @NotBlank(message = "Descrizione non può essere vuota")
    private String descrizione;


    // valutare se inserire un prezzo massimo o minimo di esperienza nel analisi
    @Min(value=10,  message  = "Il prezzo del esperienza deve essere di almeno 10 euro")
    @NotBlank(message = "Prezzo non può essere vuoto")
    private Double prezzo;

    @NotEmpty
    @NotBlank(message = "Categorie non può essere vuota")
    @OneToMany(mappedBy = "cliente")
    private Set<Categorie> categorie = new HashSet<>();

    @NotNull(message = "Categorie richieste")
    @NotBlank(message = "Categorie non può essere vuota")
    @OneToMany(mappedBy = "id")
    private Set<Acquisto> acquisto = new HashSet<>();

    public Esperienze() {
    }

    public Esperienze(Long id, String titolo, String descrizione, Double prezzo, Set<Categorie> categorie, Set<Acquisto> acquisto, Set<Acquisto> acquisto1) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.categorie = categorie;
        this.acquisto = acquisto;
    }

     public Esperienze(String titolo, String descrizione, Double prezzo, Set<Categorie> categorie, Set<Acquisto> acquisto, Set<Acquisto> acquisto1) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.categorie = categorie;
        this.acquisto = acquisto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Set<Categorie> getCategorie() {
        return categorie;
    }

    public void setCategorie(Set<Categorie> categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Esperienze{" +
                "titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", prezzo=" + prezzo +
                ", id=" + id +
                ", categorie=" + categorie +
                '}';
    }
}
