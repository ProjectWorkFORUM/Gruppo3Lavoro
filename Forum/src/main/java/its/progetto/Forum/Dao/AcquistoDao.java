package its.progetto.Forum.Dao;

import its.progetto.Forum.Model.Acquisto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcquistoDao extends JpaRepository<Acquisto, Long> {

    List<Acquisto> findByDataAcquisto(String data_acqusito);
}
