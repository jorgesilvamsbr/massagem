package massagem.repositorio;

import massagem.dominio.Colaborador;
import massagem.dominio.Massagem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends CrudRepository<Colaborador, Long> {
}
