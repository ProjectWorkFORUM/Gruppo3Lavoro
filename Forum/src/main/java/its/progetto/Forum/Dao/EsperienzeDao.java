package its.progetto.Forum.Dao;

import Model.Categoria;
import Model.Esperienze;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EsperienzeDao extends JpaRepository<Esperienze, Long> {

    List<Esperienze> findByCategoria(Categoria categoria);

    List<Esperienze> findByCategoria_Id(Long idCategoria);

    List<Esperienze> findByTitoloContainingIgnoreCase(String titolo);
}
