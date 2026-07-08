package its.progetto.Forum.Dao;

import Model.Recensioni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecensioniDao extends JpaRepository<Recensioni, Long> {

    List<Recensioni> findByTitoloContainingIgnoreCase(String titolo);
}
