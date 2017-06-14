package massagem.dominio.colaborador;

import static org.junit.Assert.*;

import massagem.dominio.colaborador.Colaborador;
import massagem.dominio.colaborador.ColaboradorBuilder;
import massagem.dominio.cpf.Cpf;
import massagem.dominio.cpf.CpfInvalido;
import org.junit.Test;

import massagem.excecao.ExcecaoDeCampoObrigatorio;

public class ColaboradorTest {

	private static final String VAZIO = "";

	@Test
	public void deve_possuir_um_nome() throws Exception {
		String nomeEsperado = "Jorge Luiz Gomes da Silva";
		
		Colaborador colaborador = ColaboradorBuilder.novo().comNome(nomeEsperado).criar();
		
		assertEquals(nomeEsperado, colaborador.getNome());
	}

	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_ser_possivel_existir_um_colaborador_com_nome_vazio() throws Exception {
		ColaboradorBuilder.novo().comNome(VAZIO).criar();	
	}
	
	@Test
	public void um_colaborador_deve_conter_um_cpf() throws Exception {
		Cpf cpfEsperado = Cpf.criar("55792814473");

		Colaborador colaborador = ColaboradorBuilder.novo().comCPF(cpfEsperado).criar();

		assertEquals(cpfEsperado.getValorSemMascara(), colaborador.getCPF());
	}

	@Test(expected = CpfInvalido.class)
	public void nao_deve_ser_possivel_informar_cpf_invalido() throws Exception {
		ColaboradorBuilder.novo().comCPF(Cpf.criar("00011122233")).criar();
	}
}