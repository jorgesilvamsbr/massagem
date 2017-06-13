package massagem.repositorio;

import massagem.dominio.Massagem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MassagemRepository extends CrudRepository<Massagem, Long> {

    @Modifying
    @Query("DELETE FROM Massagem m WHERE m.data <= ?1")
    void apagarAte(LocalDate dataDeVenciamentoDaMassagem);
}
