package its.progetto.Forum.Dao;

import its.progetto.Forum.Model.Categoria;
import its.progetto.Forum.Model.Esperienze;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EsperienzeDao extends JpaRepository<Esperienze, Long> {

    List<Esperienze> findByCategoria(Categoria categoria);

    List<Esperienze> findByCategoria_Id(Long idCategoria);

    List<Esperienze> findByTitoloContainingIgnoreCase(String titolo);
}
