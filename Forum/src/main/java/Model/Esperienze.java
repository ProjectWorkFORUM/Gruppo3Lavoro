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

    public Esperienze() {
    }

    public Esperienze(Long id, String titolo, String descrizione, Double prezzo, Set<Categorie> categorie) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.categorie = categorie;
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
