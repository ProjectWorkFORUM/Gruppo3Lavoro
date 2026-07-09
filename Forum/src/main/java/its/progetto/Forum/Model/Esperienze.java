package its.progetto.Forum.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Esperienze {

    //primary key

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    @NotNull(message = "ID richiesto")
    private Long id;

    // relazioni foreignkey corrette

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    //attributi


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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
