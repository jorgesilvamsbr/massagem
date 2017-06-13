package massagem.dominio;

import massagem.excecao.ExcecaoDeCampoObrigatorio;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

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
}
