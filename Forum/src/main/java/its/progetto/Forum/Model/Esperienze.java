package its.progetto.Forum.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table
public class Esperienze {

    // relazioni foreinkey corrette



    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    //attributi
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
    @NotNull(message = "Prezzo non può essere vuoto")
    private Double prezzo;



    public Esperienze() {
    }

    public Esperienze(Long id, String titolo, String descrizione, Double prezzo, Categoria categoria, Acquisto acquisto) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.categoria = categoria;
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



    @Override
    public String toString() {
        return "Esperienze{" +
                "titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", prezzo=" + prezzo +
                ", id=" + id +
                ", categorie=" + categoria +
                '}';
    }





}
