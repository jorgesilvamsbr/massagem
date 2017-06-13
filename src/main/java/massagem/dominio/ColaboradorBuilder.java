package massagem.dominio;

import massagem.excecao.ExcecaoDeCampoObrigatorio;

public class ColaboradorBuilder {
	
	private String nome;
	private String matricula;

	public ColaboradorBuilder() {
		this.nome = "Nome Qualquer";
		this.matricula = "4235";
	}
	
	public Colaborador criar() throws ExcecaoDeCampoObrigatorio {
		return new Colaborador(nome, matricula);
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
}