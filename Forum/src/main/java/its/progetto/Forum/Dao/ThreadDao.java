package its.progetto.Forum.Dao;

import Model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThreadDao extends JpaRepository<Thread, Long> {

    List<Thread> findByStato(boolean stato);

    List<Thread> findByTitoloContainingIgnoreCase(String titolo);
}
