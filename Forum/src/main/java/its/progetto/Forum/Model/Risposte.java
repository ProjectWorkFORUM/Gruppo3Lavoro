package its.progetto.Forum.Model;
//id,titolo,testo,data_creazione,id_utente

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name= "Risposte")
public class Risposte {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max=30)
    @NotNull(message = "Titolo richiesto")
    @NotBlank(message = "Titolo richiesto")
    @Column(name="titolo")
    private String titolo;

    @Size(min = 3, max=255)
    @NotNull(message = "Testo richiesto")
    @NotBlank(message = "Testo richiesto")
    @Column(name="testo")
    private String testo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data creazione richiesta")
    @Column(name="data_creazione")
    private String data_creazione;

    @ManyToOne
    @JoinColumn
    private Utenti utente;

    public Risposte(){}
    public Risposte(String titolo, String testo, String data_creazione){
        this.titolo = titolo;
        this.testo = testo;
        this.data_creazione = data_creazione;
    }
    
    public Risposte(Long id, String titolo, String testo, String data_creazione){
        this.id = id;
        this.titolo = titolo;
        this.testo = testo;
        this.data_creazione = data_creazione;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public void setTitolo(String titolo
) {
        this.titolo = titolo;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getTesto() {
        return testo;
    }

    public void setData_creazione(String data_creazione) {
        this.data_creazione = data_creazione;
    }

    public String getData_creazione() {
        return data_creazione;
    }

    public void setId_utente(Long id_utente) {
        this.id = id_utente;
    }

    public Long getId_utente() {
        return id;
    }   
}