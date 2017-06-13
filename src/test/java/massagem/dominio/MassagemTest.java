package massagem.dominio;

import massagem.excecao.ExcecaoDeCampoObrigatorio;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MassagemTest {

    private static final LocalDate NULA = null;
    public static final List<Colaborador> LISTA_VAZIA = Arrays.asList();

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
    public void deve_possuir_uma_lista_de_colaboradores() throws Exception {
        Colaborador colaboradorEsperado = ColaboradorBuilder.novo().comMatricula("2664").criar();
        List<Colaborador> colaboradores = Arrays.asList(colaboradorEsperado);

        Massagem massagem = MassagemBuilder.novo().comColaboradores(colaboradores).criar();

        assertTrue(massagem.getColaboradores().stream().filter(colaborador -> colaboradorEsperado.getMatricula().equals(colaborador.getMatricula())).findFirst().isPresent());
    }

    @Test(expected = ExcecaoDeCampoObrigatorio.class)
    public void nao_deve_ser_possivel_informar_uma_lista_de_colaboradores_nula() throws Exception {
        MassagemBuilder.novo().comColaboradores(LISTA_VAZIA).criar();
    }
}
