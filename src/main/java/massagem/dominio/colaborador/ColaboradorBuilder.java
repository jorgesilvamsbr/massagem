package massagem.dominio.colaborador;

import massagem.dominio.cpf.Cpf;
import massagem.excecao.ExcecaoDeCampoObrigatorio;

public class ColaboradorBuilder {
	
	private String nome;
	private String matricula;
	private Cpf cpf;

	public ColaboradorBuilder() {
		this.nome = "Nome Qualquer";
		this.matricula = "4235";
	}
	
	public static ColaboradorBuilder novo() {
		return new ColaboradorBuilder();
	}

	public ColaboradorBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}

	public ColaboradorBuilder comMatricula(String matricula) {
		this.matricula = matricula;
		return this;
	}

	public ColaboradorBuilder comCPF(Cpf cpf) {
		this.cpf = cpf;
		return this;
	}

	public Colaborador criar() throws ExcecaoDeCampoObrigatorio {
		return new Colaborador(nome, matricula, cpf);
	}
}