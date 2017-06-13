package massagem.dominio;


import massagem.excecao.ExcecaoDeCampoObrigatorio;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MassagemBuilder {

    private LocalDate data;
    private List<Colaborador> colaboradores;

    public MassagemBuilder() throws ExcecaoDeCampoObrigatorio {
        this.data = LocalDate.now();
        this.colaboradores = Arrays.asList(ColaboradorBuilder.novo().criar());
    }

    public static MassagemBuilder novo() throws ExcecaoDeCampoObrigatorio {
        return new MassagemBuilder();
    }

    public MassagemBuilder comData(LocalDate data) {
        this.data = data;
        return this;
    }

    public MassagemBuilder comColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
        return this;
    }

    public Massagem criar() throws ExcecaoDeCampoObrigatorio {
        return new Massagem(this.data, this.colaboradores);
    }
}
