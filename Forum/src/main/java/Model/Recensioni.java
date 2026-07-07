package Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Recensioni")
public class Recensioni {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "ID richiesto")
    
    private Long id;

    @Size(min = 3, max=30 )
    private String titolo;

    @Size(min = 3, max=255 )
    private String testo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String data_creazione;

    //id-utente da joinare

    public Recensioni(){}

    public Recensioni(String titolo, String testo, String data_creazione){
        this.titolo = titolo;
        this.testo = testo;
        this.data_creazione = data_creazione;
    }

    public Recensioni(Long id, String titolo, String testo, String data_creazione){
        this.id= id;
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

    public void setTitolo(String titolo) {
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
}
