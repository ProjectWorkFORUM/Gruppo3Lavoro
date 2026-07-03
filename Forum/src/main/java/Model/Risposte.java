package Model;
//id,titolo,testo,data_creazione,id_utente

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @NotNull(message = "ID utente richiesto")
    @Column(name="id_utente")
    private Long id_utente;

    public Risposte(){}
    public Risposte(String titolo, String testo, String data_creazione, Long id_utente){
        this.titolo = titolo;
        this.testo = testo;
        this.data_creazione = data_creazione;
        this.id_utente = id_utente;
    }
    
    public Risposte(Long id, String titolo, String testo, String data_creazione, Long id_utente){
        this.id = id;
        this.titolo = titolo;
        this.testo = testo;
        this.data_creazione = data_creazione;
        this.id_utente = id_utente;
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
        this.id_utente = id_utente;
    }

    public Long getId_utente() {
        return id_utente;
    }   
}