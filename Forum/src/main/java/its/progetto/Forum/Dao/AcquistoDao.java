package its.progetto.Forum.Dao;

import its.progetto.Forum.Model.Acquisto;

import its.progetto.Forum.Model.Esperienze;
import its.progetto.Forum.Model.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcquistoDao extends JpaRepository<Acquisto, Long> {

    List<Acquisto> findByDataAcquisto(String dataAcquisto);
    // da revisione chiedendo a fede
    List<Acquisto> findByUtenteId(Long utenteId);
    List<Acquisto> findByEsperienza(Esperienze esperienza);

    boolean existsByUtenteIdAndEsperienzaId(Long utenteId, Long esperienzaId);
}
