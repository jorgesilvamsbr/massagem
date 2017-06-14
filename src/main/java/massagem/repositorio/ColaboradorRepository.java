package massagem.repositorio;

import massagem.dominio.colaborador.Colaborador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends CrudRepository<Colaborador, Long> {
}
