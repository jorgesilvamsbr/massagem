package massagem.dominio.massagem;

import massagem.dominio.colaborador.Colaborador;
import massagem.dominio.colaborador.ColaboradorBuilder;
import massagem.dominio.massagem.Massagem;
import massagem.dominio.massagem.MassagemBuilder;
import massagem.excecao.ExcecaoDeCampoObrigatorio;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class MassagemTest {

    private static final LocalDate NULA = null;

    @Test
    public void deve_ter_uma_data() throws Exception {
        LocalDate dataEsperada = LocalDate.now();

        Massagem massagem = MassagemBuilder.novo().comData(dataEsperada).criar();

        assertEquals(dataEsperada, massagem.getData());
    }

    @Test(expected = ExcecaoDeCampoObrigatorio.class)
    public void nao_deve_ser_possivel_informar_uma_data_nula() throws Exception {
        MassagemBuilder.novo().comData(NULA).criar();
    }

    @Test
    public void deve_possuir_um_colaboradore() throws Exception {
        Colaborador colaboradorEsperado = ColaboradorBuilder.novo().comMatricula("2664").criar();

        Massagem massagem = MassagemBuilder.novo().comColaborador(colaboradorEsperado).criar();

        assertThat(colaboradorEsperado, equalTo(massagem.getColaborador()));
    }

    @Test(expected = ExcecaoDeCampoObrigatorio.class)
    public void nao_deve_ser_possivel_informar_uma_lista_de_colaboradores_nula() throws Exception {
        MassagemBuilder.novo().comColaborador(null).criar();
    }
}
