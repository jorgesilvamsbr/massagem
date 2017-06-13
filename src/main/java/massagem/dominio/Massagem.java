package massagem.dominio;

import massagem.dominio.base.EntidadeBase;
import massagem.excecao.ExcecaoDeCampoObrigatorio;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Massagem extends EntidadeBase{

    private final LocalDate data;
    private  final Colaborador colaborador;

    public Massagem(LocalDate data, Colaborador colaborador) throws ExcecaoDeCampoObrigatorio {
        validarCamposObrigatorios(data, colaborador);
        this.colaborador = colaborador;
        this.data = data;
    }

    private void validarCamposObrigatorios(LocalDate data, Colaborador colaborador) throws ExcecaoDeCampoObrigatorio {
        new ExcecaoDeCampoObrigatorio()
                .quandoNulo(data, "A data informada é inválida")
                .quandoNulo(colaborador, "O colaborador não pode ser vazio")
                .entaoDispara();
    }

    public LocalDate getData() {
        return data;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }
}
