package Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

enum StatoAccount {
    ATTIVO,
    SOSPESO,
    DISATTIVATO
}

 enum Ruolo {
    UTENTE,
    MODERATORE,
    ADMIN
}
@Entity
@Table(name= "Utenti")
public class Utenti {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;

    @Size(min = 2, max=30)
    @NotBlank(message = "Username richiesto")
    private String username;

    @Size(min = 2,max=30)
    @NotBlank(message = "Nome richiesto")
    private String nome;

    @Size(min = 2,max=30)
    @NotBlank(message = "Cognome richiesto")
    private String cognome;

    @Size(min =4,max=50)
    @Email(message = "Email non valida")
    @NotBlank(message = "Email richiesta")
    private String email;

    @Size(min=8,max=20)
    @NotBlank(message = "password richiesta")
    private String password;

    @NotNull(message = "Data di nascita richiesta")
    @Past(message = "La data di nascita deve essere nel passato")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascita;

    @NotNull(message = "Data di nascita richiesta")
    @Past(message = "La data di nascita deve essere nel passato")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataRegistrazione;

    @NotNull(message = "Stato account richiesto")
    private StatoAccount stato_account;

    @NotNull(message = "Ruolo richiesto")
    private Ruolo ruolo;

    //forse OneToMany con gli acquisti/recensioni

    public Utenti() {
    }
    
    public Utenti(String username, String nome, String cognome, String email, String password, @NotNull @Past LocalDate data_nascita, @NotNull @Past LocalDate data_registrazione, StatoAccount stato_account, Ruolo ruolo) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.dataNascita = data_nascita;
        this.dataRegistrazione = data_registrazione;
        this.stato_account = stato_account;
        this.ruolo = ruolo;
    }

    public Utenti(Long id, String username, String nome, String cognome, String email, String password, @NotNull @Past LocalDate data_nascita, @NotNull @Past LocalDate data_registrazione, StatoAccount stato_account, Ruolo ruolo) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.dataNascita= data_nascita;
        this.dataRegistrazione = data_registrazione;
        this.stato_account = stato_account;
        this.ruolo = ruolo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getData_nascita() {
        return dataNascita;
    }

    public void setData_nascita(@NotNull @Past LocalDate data_nascita) {
        this.dataNascita = data_nascita;
    }


    public LocalDate getDataRegistrazione() {
        return dataRegistrazione;
    }

    public void setDataRegistrazione(LocalDate dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }

    public StatoAccount getStato_account() {
        return stato_account;
    }

    public void setStato_account(StatoAccount stato_account) {
        this.stato_account = stato_account;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public String toString() {
        return "Utenti{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", data_nascita='" + dataNascita + '\'' +
                ", data_registrazione='" + dataRegistrazione + '\'' +
                ", stato_account=" + stato_account +
                ", ruolo=" + ruolo +
                '}';
    }


    @OneToMany(mappedBy = "utenti")
    private Set<Acquisto> acquistoSet = new HashSet<>();
    private Set<Acquisto> risposteSet = new HashSet<>();
}

   

