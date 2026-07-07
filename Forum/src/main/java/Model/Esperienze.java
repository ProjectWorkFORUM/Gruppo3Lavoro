package Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Esperienze {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    @NotNull(message = "ID richiesto")
    @NotBlank(message = "ID non può essere vuoto")
    private Long id;

    @Size(min= 4, max=20)
    @NotNull(message = "Titolo richiesto")
    @NotBlank(message = "Titolo non può essere vuoto")
    private String titolo;


    @Size(min = 5, max=50 ) 
    @NotNull(message = "Descrizione richiesta")
    @NotBlank(message = "Descrizione non può essere vuota")
    private String descrizione;


    // valutare se inserire un prezzo massimo o minimo di esperienza nel analisi
    @NotNull(message = "Prezzo richiesto")
    @Min(value=10,  message  = "Il prezzo del esperienza deve essere di almeno 10 euro")
    @NotBlank(message = "Prezzo non può essere vuoto")
    private Double prezzo;

    @NotNull(message = "Categorie richieste")
    @NotBlank(message = "Categorie non può essere vuota")
    @OneToMany(mappedBy = "cliente")
    private Set<Categorie> categorie = new HashSet<>();

    @NotNull(message = "Categorie richieste")
    @NotBlank(message = "Categorie non può essere vuota")
    @OneToMany(mappedBy = "id")
    private Set<Acquisti> acquisti = new HashSet<>();

    public Esperienze() {
    }

    public Esperienze(Long id, String titolo, String descrizione, Double prezzo, Set<Categorie> categorie, Set<Acquisti> acquisti, Set<Acquisti> acquisti1) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.categorie = categorie;
        this.acquisti = acquisti;
    }

     public Esperienze( String titolo, String descrizione, Double prezzo, Set<Categorie> categorie, Set<Acquisti> acquisti, Set<Acquisti> acquisti1) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.categorie = categorie;
        this.acquisti = acquisti;
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
