package massagem.aplicacao;

import massagem.dominio.Colaborador;
import massagem.dominio.Massagem;
import massagem.dominio.OrdenadorDeMassagem;
import massagem.excecao.ExcecaoDeCampoObrigatorio;
import massagem.repositorio.ColaboradorRepository;
import massagem.repositorio.MassagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GerarListaDeSelecionados {

    private final ColaboradorRepository colaboradorRepository;
    private final MassagemRepository massagemRepository;
    private final OrdenadorDeMassagem ordenadorDeMassagem;

    @Autowired
    public GerarListaDeSelecionados(ColaboradorRepository colaboradorRepository, MassagemRepository massagemRepository) {
        this.colaboradorRepository = colaboradorRepository;
        this.massagemRepository = massagemRepository;
        this.ordenadorDeMassagem = new OrdenadorDeMassagem(this.massagemRepository);
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