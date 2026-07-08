package its.progetto.Forum.Dao;
import its.progetto.Forum.Model.Risposte;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RisposteDao extends JpaRepository<Risposte, Long> {

    List<Risposte> findByTitoloContainingIgnoreCase(String titolo);
}
