package massagem.aplicacao;

import massagem.repositorio.MassagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ApagaMassagemVencida {

    private final MassagemRepository massagemRepository;
    private final int quantidadeDeDiasVigenteDaMassagem = 30;

    @Autowired
    public ApagaMassagemVencida(MassagemRepository massagemRepository) {
        this.massagemRepository = massagemRepository;
    }

    @Transactional
    public void apagar() {
        LocalDate dataDeVenciamentoDaMassagem = LocalDate.now().minusDays(quantidadeDeDiasVigenteDaMassagem);
        massagemRepository.apagarAte(dataDeVenciamentoDaMassagem);
    }
}
