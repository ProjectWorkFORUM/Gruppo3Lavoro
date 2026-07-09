package its.progetto.Forum.Dao;
import its.progetto.Forum.Model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThreadDao extends JpaRepository<Thread, Long> {

    List<Thread> findByStato(boolean stato);

    List<Thread> findByTitoloContainingIgnoreCase(String titolo);

    List<Thread> findByVisibileTrue();

    List<Thread> findByEsperienzaIdAndVisibileTrue(Long esperienzaId);
}
