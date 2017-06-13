package massagem.dominio;

import javax.persistence.Entity;

import massagem.dominio.base.EntidadeBase;
import massagem.excecao.ExcecaoDeCampoObrigatorio;

@Entity
public class Colaborador extends EntidadeBase {

	private String nome;
	private String matricula;

	public Colaborador(String nome, String matricula) throws ExcecaoDeCampoObrigatorio {
		validarCampos(nome, matricula);
		this.nome = nome;
		this.matricula = matricula;
	}

	private void validarCampos(String nome, String matricula) throws ExcecaoDeCampoObrigatorio {
		new ExcecaoDeCampoObrigatorio()
		.quandoVazio(nome, "O nome informado está vazio")
		.quandoVazio(matricula, "A matricula informada está vazia")
		.entaoDispara();
	}

	public String getNome() {
		return nome;
	}
	
	public String getMatricula() {
		return matricula;
	}
}
