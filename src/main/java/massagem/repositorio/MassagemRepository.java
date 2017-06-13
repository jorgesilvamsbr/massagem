package massagem.repositorio;

import massagem.dominio.Massagem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MassagemRepository extends CrudRepository<Massagem, Long> {
}
