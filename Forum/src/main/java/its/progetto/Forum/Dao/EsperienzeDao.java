package its.progetto.Forum.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import its.progetto.Forum.Model.Categoria;
import its.progetto.Forum.Model.Esperienze;

public interface EsperienzeDao extends JpaRepository<Esperienze, Long> {

    //List<Esperienze> findById(Long id);

    List<Esperienze> findByCategoria(Categoria categoria);

    List<Esperienze> findByCategoria_Id(Long idCategoria);

    List<Esperienze> findByTitoloContainingIgnoreCase(String titolo);
}
