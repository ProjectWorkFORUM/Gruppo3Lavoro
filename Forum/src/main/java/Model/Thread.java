package Model;
    //id,id_risposte,titolo,data_apertura,stato

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
@Table(name= "Thread")
public class Thread {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    @NotNull(message = "ID richiesto")
    private Long id;

    //id-utente da joinare


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data apertura richiesta")
    @Column(name="data_apertura")
    private String data_apertura;

    
    @NotNull(message = "Stato richiesto")   
    @Column(name="stato")
    private boolean  stato;

    //id-esperienza da joinare
    public Thread() {
    }

    public Thread(Long id, String titolo, String data_apertura, boolean stato) {
        this.id = id;
        this.titolo = titolo;
        this.data_apertura = data_apertura;
        this.stato = stato;
    }

        public Thread( String titolo, String data_apertura, boolean stato) {
        this.titolo = titolo;
        this.data_apertura = data_apertura;
        this.stato = stato;
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

    public void setTitolo(String titolo
) {
        this.titolo = titolo;
    }

    public String getData_apertura() {
        return data_apertura;
    }

    public void setData_apertura(String data_apertura) {
        this.data_apertura = data_apertura;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Thread{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", data_apertura='" + data_apertura + '\'' +
                ", stato=" + stato +
                '}';
    }
}
