package its.progetto.Forum.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank(message = "Nome categoria richiesto")
    @Size(min= 3, max=20)
    private String nome;

    public Categoria(){}
    public Categoria(String nome){
        this.nome = nome;
    }

    public Categoria(Long id, String nome){
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

    @OneToMany(mappedBy = "categoria")
    private Set<Esperienze> esprerienze = new HashSet<>();

}
