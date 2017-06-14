package massagem.aplicacao.colaborador;

import massagem.aplicacao.massagem.ApagaMassagemVencida;
import massagem.dominio.colaborador.Colaborador;
import massagem.dominio.massagem.Massagem;
import massagem.dominio.massagem.OrdenadorDeMassagem;
import massagem.excecao.ExcecaoDeCampoObrigatorio;
import massagem.repositorio.ColaboradorRepository;
import massagem.repositorio.MassagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaListaDeColaboradoresSelecionados {

    private final ColaboradorRepository colaboradorRepository;
    private final MassagemRepository massagemRepository;
    private final OrdenadorDeMassagem ordenadorDeMassagem;
    private final ApagaMassagemVencida apagaMassagemVencida;

    @Autowired
    public ConsultaListaDeColaboradoresSelecionados(ColaboradorRepository colaboradorRepository, MassagemRepository massagemRepository, ApagaMassagemVencida apagaMassagemVencida) {
        this.colaboradorRepository = colaboradorRepository;
        this.massagemRepository = massagemRepository;
        this.apagaMassagemVencida = apagaMassagemVencida;
        this.ordenadorDeMassagem = new OrdenadorDeMassagem(this.massagemRepository, this.apagaMassagemVencida);
    }

    public List<ColaboradorDTO> selecionar(List<ColaboradorDTO> candidatosAMassagem){
        List<Colaborador> colaboradores = candidatosAMassagem.stream().map(candidatoDTO -> this.colaboradorRepository.findOne(candidatoDTO.getId())).collect(Collectors.toList());
        List<Colaborador> colaboradoresSelecionados = this.ordenadorDeMassagem.obterSelecionados(colaboradores);
        salvarMassagemDoDia(colaboradoresSelecionados);
        return MapeadorDeColaborador.mapear(colaboradoresSelecionados);
    }

    private void salvarMassagemDoDia(List<Colaborador> colaboradoresSelecionados) {
        colaboradoresSelecionados.forEach(colaborador -> {
            try {
                massagemRepository.save(new Massagem(LocalDate.now(), colaborador));
            } catch (ExcecaoDeCampoObrigatorio excecaoDeCampoObrigatorio) {
                excecaoDeCampoObrigatorio.printStackTrace();
            }
        });
    }
}
