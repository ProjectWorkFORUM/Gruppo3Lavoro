package its.progetto.Forum.Dao;

import Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaDao extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNome(String nome);

    boolean existsByNome(String nome);
}
