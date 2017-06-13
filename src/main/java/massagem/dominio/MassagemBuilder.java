package massagem.dominio;


import massagem.excecao.ExcecaoDeCampoObrigatorio;

import java.time.LocalDate;

public class MassagemBuilder {

    private LocalDate data;

    public static MassagemBuilder novo() {
        return new MassagemBuilder();
    }

    public MassagemBuilder comData(LocalDate data) {
        this.data = data;
        return this;
    }

    public Massagem criar() throws ExcecaoDeCampoObrigatorio {
        return new Massagem(this.data);
    }
}
