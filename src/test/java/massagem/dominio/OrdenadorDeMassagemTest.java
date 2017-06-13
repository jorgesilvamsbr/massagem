package massagem.dominio;

import massagem.excecao.ExcecaoDeCampoObrigatorio;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrdenadorDeMassagemTest {

    private OrdenadorDeMassagem ordenadorDeMassagem;

    @Before
    public void init() throws ExcecaoDeCampoObrigatorio {
        Colaborador colaborador1 = ColaboradorBuilder.novo().criar();
        Colaborador colaborador2 = ColaboradorBuilder.novo().criar();
        Colaborador colaborador3 = ColaboradorBuilder.novo().criar();
        Colaborador colaborador4 = ColaboradorBuilder.novo().criar();

    }

    @Test
    public void deve_listar_os_nomes_dos_selecionados_para_massagem_pela_data_de_realizacao_da_massagem() throws Exception {
        Colaborador colaborador1 = ColaboradorBuilder.novo().criar();
        Colaborador colaborador2 = ColaboradorBuilder.novo().criar();
        Colaborador colaborador3 = ColaboradorBuilder.novo().criar();
        Colaborador colaborador4 = ColaboradorBuilder.novo().criar();

        List<Colaborador> selecionados = this.ordenadorDeMassagem.obterSelecionados(Arrays.asList(colaborador1, colaborador2, colaborador3, colaborador4));

        List<String> matriculas = selecionados.stream().map(colaborador -> colaborador.getMatricula()).collect(Collectors.toList());
        assertThat(matriculas, hasItems(colaborador1.getMatricula(), colaborador3.getMatricula()));
    }
}
