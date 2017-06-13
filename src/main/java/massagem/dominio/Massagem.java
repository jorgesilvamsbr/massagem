package massagem.dominio;

import massagem.dominio.base.EntidadeBase;
import massagem.excecao.ExcecaoDeCampoObrigatorio;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Massagem extends EntidadeBase{

    private final LocalDate data;
    private  final List<Colaborador> colaboradores;

    public Massagem(LocalDate data, List<Colaborador> colaboradores) throws ExcecaoDeCampoObrigatorio {
        validarCamposObrigatorios(data, colaboradores);
        this.colaboradores = colaboradores;
        this.data = data;
    }

    private void validarCamposObrigatorios(LocalDate data, List<Colaborador> colaboradores) throws ExcecaoDeCampoObrigatorio {
        new ExcecaoDeCampoObrigatorio()
                .quandoNulo(data, "A data informada é inválida")
                .quandoListaVazia(colaboradores, "A lista informada é vazia")
                .entaoDispara();
    }

    public LocalDate getData() {
        return data;
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }
}
