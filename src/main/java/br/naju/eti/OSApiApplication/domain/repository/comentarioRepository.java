package br.naju.eti.OSApiApplication.domain.repository;


import br.naju.eti.OSApiApplication.domain.model.Comentario;
import br.naju.eti.OSApiApplication.domain.model.OrdemServico;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sesi3dia
 */
public interface comentarioRepository extends JpaRepository<Comentario, Long> {
    
    List<Comentario> findByDescricaoContaining (String descricao);
    List<Comentario> findByDataEnvio (LocalDateTime dataEnvio);
}
