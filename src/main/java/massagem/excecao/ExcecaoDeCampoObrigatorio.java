package massagem.excecao;

import java.util.*;
import java.math.*;

public class ExcecaoDeCampoObrigatorio extends Exception {
	private static final long serialVersionUID = 1L;

	private final List<String> erros = new ArrayList<String>();

	public ExcecaoDeCampoObrigatorio() {
	}

	public String getMensagemDeErro() {
		return "";
	}

	private void inserir(String msg) {
		erros.add(msg);
	}

	private boolean possuiErro() {
		return !erros.isEmpty();
	}

	public List<String> getErros() {
		return erros;
	}

	private ExcecaoDeCampoObrigatorio quando(boolean condicao, String mensagem) {
		if (condicao) {
			inserir(mensagem);
		}
		return this;
	}

	public ExcecaoDeCampoObrigatorio entaoDispara() throws ExcecaoDeCampoObrigatorio {
		if (!erros.isEmpty()) {
			String mensagemDeErro = "";
			for (String mensagem : erros) {
				mensagemDeErro = mensagemDeErro + " - " + mensagem;
			}
			throw this;
		}

		return this;
	}

	public ExcecaoDeCampoObrigatorio quandoNulo(Object obj, String mensagem) {
		quando(obj == null, mensagem);
		return this;
	}

	public ExcecaoDeCampoObrigatorio quandoVazio(String valor, String mensagem) {
		quandoNulo(valor, mensagem);
		if (!possuiErro()) {
			quando(valor.isEmpty(), mensagem);
		}
		return this;
	}

	public ExcecaoDeCampoObrigatorio quandoValorMenorOuIgualZero(BigDecimal valor, String mensagem) {
		quandoNulo(valor, mensagem);
		if (!possuiErro()) {
			quando(valor.compareTo(BigDecimal.ZERO) <= 0, mensagem);
		}
		return this;
	}
	
	public ExcecaoDeCampoObrigatorio quandoValorMenorOuIgualZero(Integer valor, String mensagem) {
		quandoNulo(valor, mensagem);
		if (!possuiErro()) {
			quando(valor <= 0, mensagem);
		}
		return this;
	}

	public ExcecaoDeCampoObrigatorio quandoValorMenorOuIgualZero(Double valor, String mensagem) {
		quandoNulo(valor, mensagem);
		if (!possuiErro()) {
			quando(valor <= 0, mensagem);
		}
		return this;
	}

	public ExcecaoDeCampoObrigatorio quandoValorMenorQueZero(Integer valor, String mensagem) {
		quandoNulo(valor, mensagem);
		if (!possuiErro()) {
			quando(valor < 0, mensagem);
		}
		return this;
	}
	
	public ExcecaoDeCampoObrigatorio quandoValorMenorQueZero(Double valor, String mensagem) {
		quandoNulo(valor, mensagem);
		if (!possuiErro()) {
			quando(valor < 0, mensagem);
		}
		return this;
	}

	public ExcecaoDeCampoObrigatorio quandoListaVazia(List<?> valor, String mensagem) {
		quandoNulo(valor, mensagem);
		if (!possuiErro()) {
			quando(valor.isEmpty(), mensagem);
		}
		return this;
	}
}