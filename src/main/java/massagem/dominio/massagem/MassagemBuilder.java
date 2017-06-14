package massagem.dominio.massagem;


import massagem.dominio.colaborador.Colaborador;
import massagem.dominio.colaborador.ColaboradorBuilder;
import massagem.dominio.cpf.CpfInvalido;
import massagem.excecao.ExcecaoDeCampoObrigatorio;

import java.time.LocalDate;

public class MassagemBuilder {

    private LocalDate data;
    private Colaborador colaborador;

    public MassagemBuilder() throws ExcecaoDeCampoObrigatorio, CpfInvalido {
        this.data = LocalDate.now();
        this.colaborador = ColaboradorBuilder.novo().criar();
    }

    public static MassagemBuilder novo() throws ExcecaoDeCampoObrigatorio, CpfInvalido {
        return new MassagemBuilder();
    }

    public MassagemBuilder comData(LocalDate data) {
        this.data = data;
        return this;
    }

    public MassagemBuilder comColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
        return this;
    }

    public Massagem criar() throws ExcecaoDeCampoObrigatorio {
        return new Massagem(this.data, this.colaborador);
    }
}
