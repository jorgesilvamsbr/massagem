package massagem.aplicacao;

import massagem.repositorio.MassagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ApagaMassagemVencida {

    private final MassagemRepository massagemRepository;
    private static final int QUANTIDADE_DE_DIAS_VIGENTE_DA_MASSAGEM = 30;

    @Autowired
    public ApagaMassagemVencida(MassagemRepository massagemRepository) {
        this.massagemRepository = massagemRepository;
    }

    @Transactional
    public void apagar() {
        LocalDate dataDeVenciamentoDaMassagem = LocalDate.now().minusDays(QUANTIDADE_DE_DIAS_VIGENTE_DA_MASSAGEM);
        massagemRepository.apagarAte(dataDeVenciamentoDaMassagem);
    }
}
