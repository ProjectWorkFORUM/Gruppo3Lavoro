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
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Size(max=30)
    @Column(name="titolo")
    private String titolo;

    @Size(min = 3, max=255)
    @NotBlank(message = "Testo richiesto")
    @Column(name="testo")
    private String testo;

    // valorizzata lato server al salvataggio, non è un campo del form
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="data_creazione")
    private String data_creazione;

    @ManyToOne
    @JoinColumn(name="id_utente")
    private Utenti utente;
    // correzione relazione modifiche fede
    @ManyToOne
    @JoinColumn(name="id_thread")
    private Thread thread;

    @Column(name="visibile")
    private boolean visibile = true;

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

    public Utenti getUtente() {
        return utente;
    }

    public void setUtente(Utenti utente) {
        this.utente = utente;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public boolean isVisibile() {
        return visibile;
    }

    public void setVisibile(boolean visibile) {
        this.visibile = visibile;
    }
}