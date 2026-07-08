package its.progetto.Forum.Dao;

import Model.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtentiDao extends JpaRepository<Utenti, Long> {

    Optional<Utenti> findByUsername(String username);

    Optional<Utenti> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
