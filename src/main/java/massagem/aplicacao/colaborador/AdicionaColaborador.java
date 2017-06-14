package massagem.aplicacao.colaborador;

import massagem.dominio.colaborador.Colaborador;
import massagem.dominio.cpf.CpfInvalido;
import massagem.excecao.ExcecaoDeCampoObrigatorio;
import massagem.repositorio.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdicionaColaborador {
    private final ColaboradorRepository colaboradorRepository;

    @Autowired
    public AdicionaColaborador(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    @Transactional
    public void adicionaColaborador(ColaboradorDTO colaboradorDTO) throws CpfInvalido, ExcecaoDeCampoObrigatorio {
        Colaborador colaborador = MapeadorDeColaborador.mapear(colaboradorDTO);
        this.colaboradorRepository.save(colaborador);
        colaboradorDTO.setId(colaborador.getId());
    }
}
