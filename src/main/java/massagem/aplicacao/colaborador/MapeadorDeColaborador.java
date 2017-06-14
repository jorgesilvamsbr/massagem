package massagem.aplicacao.colaborador;

import massagem.dominio.colaborador.Colaborador;
import massagem.dominio.cpf.Cpf;
import massagem.dominio.cpf.CpfInvalido;
import massagem.excecao.ExcecaoDeCampoObrigatorio;

import java.util.List;
import java.util.stream.Collectors;

public class MapeadorDeColaborador {

    public static List<ColaboradorDTO> mapear(List<Colaborador> colaboradoresSelecionados) {
        return colaboradoresSelecionados.stream().map(MapeadorDeColaborador::criarDTO).collect(Collectors.toList());
    }

    private static ColaboradorDTO criarDTO(Colaborador colaborador) {
        ColaboradorDTO colaboradorDTO = new ColaboradorDTO();
        colaboradorDTO.setId(colaborador.getId());
        colaboradorDTO.setNome(colaborador.getNome());
        return colaboradorDTO;
    }

    public static Colaborador mapear(ColaboradorDTO colaboradorDTO) throws CpfInvalido, ExcecaoDeCampoObrigatorio {
        return new Colaborador(colaboradorDTO.getNome(), Cpf.criar(colaboradorDTO.getCpf()));
    }
}
