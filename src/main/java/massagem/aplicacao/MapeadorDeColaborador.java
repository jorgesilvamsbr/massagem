package massagem.aplicacao;

import massagem.dominio.Colaborador;

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
}
