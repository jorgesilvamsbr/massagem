package massagem.dominio.colaborador;

import massagem.dominio.cpf.Cpf;
import massagem.dominio.cpf.CpfInvalido;
import massagem.excecao.ExcecaoDeCampoObrigatorio;

public class ColaboradorBuilder {
	
	private String nome;
	private Cpf cpf;

	public ColaboradorBuilder() throws CpfInvalido {
		this.nome = "Nome Qualquer";
		this.cpf = Cpf.criar("51035254107");
	}
	
	public static ColaboradorBuilder novo() throws CpfInvalido {
		return new ColaboradorBuilder();
	}

	public ColaboradorBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}

	public ColaboradorBuilder comCPF(Cpf cpf) {
		this.cpf = cpf;
		return this;
	}

	public Colaborador criar() throws ExcecaoDeCampoObrigatorio {
		return new Colaborador(nome, cpf);
	}
}