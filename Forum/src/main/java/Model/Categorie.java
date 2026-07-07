package Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull(message = "Nome categoria richiesto")
    @NotBlank(message = "Nome categoria richiesto")
    @Size(min= 3, max=20)
    private String nome;

    public Categorie(){}
    public Categorie(String nome){
        this.nome = nome;
    }

    public Categorie(Long id, String nome){
        this.id = id;
        this.nome = nome;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
