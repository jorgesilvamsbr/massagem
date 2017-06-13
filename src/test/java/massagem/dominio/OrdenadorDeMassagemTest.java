package massagem.dominio;

import massagem.excecao.ExcecaoDeCampoObrigatorio;
import massagem.repositorio.MassagemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class OrdenadorDeMassagemTest {

    private OrdenadorDeMassagem ordenadorDeMassagem;
    private Colaborador colaborador1;
    private Massagem massagem1;
    private Colaborador colaborador2;
    private Massagem massagem2;
    private Colaborador colaborador3;
    private Massagem massagem3;

    @Before
    public void init() throws ExcecaoDeCampoObrigatorio {
        colaborador1 = ColaboradorBuilder.novo().comMatricula("2667").criar();
        massagem1 = MassagemBuilder.novo().comData(LocalDate.of(2017, 4,13)).comColaborador(colaborador1).criar();
        colaborador2 = ColaboradorBuilder.novo().comMatricula("2367").criar();
        massagem2 = MassagemBuilder.novo().comData(LocalDate.of(2017, 3,13)).comColaborador(colaborador2).criar();
        colaborador3 = ColaboradorBuilder.novo().comMatricula("2627").criar();
        massagem3 = MassagemBuilder.novo().comData(LocalDate.of(2017, 5,25)).comColaborador(colaborador3).criar();

        MassagemRepository massagemRepository = Mockito.mock(MassagemRepository.class);
        this.ordenadorDeMassagem = new OrdenadorDeMassagem(massagemRepository, apagaMassagemVencida);
        Mockito.when(massagemRepository.findAll()).thenReturn(Arrays.asList(massagem1, massagem2, massagem3));
    }

    @Test
    public void deve_listar_os_nomes_dos_selecionados_para_massagem_pela_data_de_realizacao_da_massagem() throws Exception {
        List<Colaborador> selecionados = this.ordenadorDeMassagem.obterSelecionados(Arrays.asList(colaborador1, colaborador2, colaborador3));

        List<String> matriculas = selecionados.stream().map(colaborador -> colaborador.getMatricula()).collect(Collectors.toList());
        assertThat(matriculas, contains(colaborador2.getMatricula(), colaborador1.getMatricula(), colaborador3.getMatricula()));
    }
}
