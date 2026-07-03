package Model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
    @NotNull(message = "Username richiesto")
    @Column(name="username")
    private String username;

    @NotNull(message = "Nome richiesto")
    @NotBlank(message = "Nome richiesto")
    @Column(name="nome")
    private String nome;

    @Size(min = 2,max=30)
    @NotNull(message = "Cognome richiesto")
    @NotBlank(message = "Cognome richiesto")
    @Column(name="cognome")
    private String cognome;

    @Size(min =4,max=50)
    @Email(message = "Email non valida")
    @NotNull(message = "Email richiesta")
    @Column(name="email")
    private String email;

    @Size(min=8,max=20)
    @NotNull(message = "password richiesta")
    @Column(name="password")
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data di nascita richiesta")
    @Column(name="data_nascita")
    private String data_nascita;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="data_registrazione")
    private String data_registrazione;

    @NotNull(message = "Stato account richiesto")
    @Column(name="stato_account")
    private StatoAccount stato_account;

    @NotNull(message = "Ruolo richiesto")
    @Column(name="ruolo")
    private Ruolo ruolo;

    //forse OneToMany con gli acquisti/recensioni

    public Utenti() {
    }
    
    public Utenti(String username, String nome, String cognome, String email, String password, String data_nascita, String data_registrazione, StatoAccount stato_account, Ruolo ruolo) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.data_nascita = data_nascita;
        this.data_registrazione = data_registrazione;
        this.stato_account = stato_account;
        this.ruolo = ruolo;
    }

    public Utenti(Long id, String username, String nome, String cognome, String email, String password, String data_nascita, String data_registrazione, StatoAccount stato_account, Ruolo ruolo) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.data_nascita = data_nascita;
        this.data_registrazione = data_registrazione;
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

    public String getData_nascita() {
        return data_nascita;
    }

    public void setData_nascita(String data_nascita) {
        this.data_nascita = data_nascita;
    }

    public String getData_registrazione() {
        return data_registrazione;
    }

    public void setData_registrazione(String data_registrazione) {
        this.data_registrazione = data_registrazione;
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
                ", data_nascita='" + data_nascita + '\'' +
                ", data_registrazione='" + data_registrazione + '\'' +
                ", stato_account=" + stato_account +
                ", ruolo=" + ruolo +
                '}';
    }
}

   

