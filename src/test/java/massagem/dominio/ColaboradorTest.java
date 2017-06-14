package massagem.dominio;

import static org.junit.Assert.*;

import massagem.dominio.cpf.Cpf;
import massagem.dominio.cpf.CpfInvalido;
import org.junit.Test;

import massagem.excecao.ExcecaoDeCampoObrigatorio;

public class ColaboradorTest {

	private static final String VAZIO = "";

	@Test
	public void deve_possuir_uma_matricula() throws Exception {
		String nomeEsperado = "Jorge Luiz Gomes da Silva";
		
		Colaborador colaborador = ColaboradorBuilder.novo().comNome(nomeEsperado).criar();
		
		assertEquals(nomeEsperado, colaborador.getNome());
	}
	
	@Test
	public void deve_conter_um_numero_de_matricula()  throws Exception {
		String matriculaEsperada = "2664";
		
		Colaborador colaborador = ColaboradorBuilder.novo().comMatricula(matriculaEsperada).criar();
		
		assertEquals(matriculaEsperada, colaborador.getMatricula());
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_ser_possivel_existir_um_colaborador_com_nome_vazio() throws Exception {
		ColaboradorBuilder.novo().comNome(VAZIO).criar();	
	}
	
	@Test(expected = ExcecaoDeCampoObrigatorio.class)
	public void nao_deve_ser_possivel_existir_um_colaborador_com_matricula_vazia() throws Exception {
		ColaboradorBuilder.novo().comMatricula(VAZIO).criar();	
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
