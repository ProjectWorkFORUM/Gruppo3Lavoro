package its.progetto.Forum.Model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name="Recensioni")
public class Recensioni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Titolo richiesto")
    @Size(min = 3, max=30 )
    private String titolo;

    @NotBlank(message = "Testo richiesto")
    @Size(min = 3, max=255 )
    private String testo;

    @Min(value = 1)
    @Max(value = 5)
    private int voto;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCreazione;

    @ManyToOne
    @JoinColumn(name = "esperienza_id")
    private Esperienze esperienza;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Utenti autore;

    private boolean visibile = true;

    public Recensioni(){}

    public Recensioni(String titolo, String testo, int voto){
        this.titolo = titolo;
        this.testo = testo;
        this.voto = voto;
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

    public void setDataCreazione(LocalDate dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public int getVoto() {
        return voto;
    }

    public void setEsperienza(Esperienze esperienza) {
        this.esperienza = esperienza;
    }

    public Esperienze getEsperienza() {
        return esperienza;
    }

    public void setAutore(Utenti autore) {
        this.autore = autore;
    }

    public Utenti getAutore() {
        return autore;
    }

    public boolean isVisibile() {
        return visibile;
    }

    public void setVisibile(boolean visibile) {
        this.visibile = visibile;
    }
}
