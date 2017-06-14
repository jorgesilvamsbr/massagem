package massagem.aplicacao;

import massagem.aplicacao.massagem.ApagaMassagemVencida;
import massagem.dominio.colaborador.Colaborador;
import massagem.dominio.colaborador.ColaboradorBuilder;
import massagem.dominio.massagem.Massagem;
import massagem.dominio.massagem.MassagemBuilder;
import massagem.excecao.ExcecaoDeCampoObrigatorio;
import massagem.repositorio.ColaboradorRepository;
import massagem.repositorio.MassagemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;


@DataJpaTest
@RunWith(SpringRunner.class)
public class ApagaMassagemVencidaTest extends TesteBase {

    @Autowired
    private ApagaMassagemVencida apagaMassagemVencida;

    @Autowired
    private MassagemRepository massagemRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;



    @Test
    public void apaga_as_massagem_que_aconteceram_com_mais_de_30_dias() throws Exception {
        long quantidadeMassagensEsperadas = 1L;
        Massagem massagemHoje = massagemRealizadaHoje();
        massagemRealizadaHaTrintaDias();
        massagemRealizadaHaSessentaDias();

        apagaMassagemVencida.apagar();

        Iterable<Massagem> massagens = massagemRepository.findAll();
        assertThat(massagens, hasItem(massagemHoje));
        assertThat(quantidadeMassagensEsperadas, equalTo(massagens.spliterator().estimateSize()));
    }

    private void massagemRealizadaHaSessentaDias() throws ExcecaoDeCampoObrigatorio {
        int sessentaDias = 60;
        Massagem massagemDe60Dias = MassagemBuilder.novo().comData(LocalDate.now().minusDays(sessentaDias)).comColaborador(criarColaborador()).criar();
        massagemRepository.save(massagemDe60Dias);
    }

    private void massagemRealizadaHaTrintaDias() throws ExcecaoDeCampoObrigatorio {
        int trintaDias = 30;
        Massagem massagemDe30Dias = MassagemBuilder.novo().comData(LocalDate.now().minusDays(trintaDias)).comColaborador(criarColaborador()).criar();
        massagemRepository.save(massagemDe30Dias);
    }

    private Massagem massagemRealizadaHoje() throws ExcecaoDeCampoObrigatorio {
        Colaborador colaborador = criarColaborador();
        Massagem massagemHoje = MassagemBuilder.novo().comData(LocalDate.now()).comColaborador(colaborador).criar();
        massagemRepository.save(massagemHoje);
        return massagemHoje;
    }

    private Colaborador criarColaborador() throws ExcecaoDeCampoObrigatorio {
        Colaborador colaborador = ColaboradorBuilder.novo().criar();
        colaboradorRepository.save(colaborador);
        return colaborador;
    }
}