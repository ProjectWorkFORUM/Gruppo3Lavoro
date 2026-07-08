package its.progetto.Forum.Dao;

import Model.Acquisto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcquistoDao extends JpaRepository<Acquisto, Long> {

    List<Acquisto> findByData_acqusito(String data_acqusito);
}
