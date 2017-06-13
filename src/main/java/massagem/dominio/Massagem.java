package massagem.dominio;

import massagem.dominio.base.EntidadeBase;
import massagem.excecao.ExcecaoDeCampoObrigatorio;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Massagem extends EntidadeBase{

    private LocalDate data;

    public Massagem(LocalDate data) throws ExcecaoDeCampoObrigatorio {
        validarCamposObrigatorios(data);
        this.data = data;
    }

    private void validarCamposObrigatorios(LocalDate data) throws ExcecaoDeCampoObrigatorio {
        new ExcecaoDeCampoObrigatorio()
                .quandoNulo(data, "A data informada é inválida")
                .entaoDispara();
    }

    public LocalDate getData() {
        return data;
    }
}
