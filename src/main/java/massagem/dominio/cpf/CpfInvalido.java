package massagem.dominio.cpf;


import massagem.excecao.ExcecaoDeRegraDeNegocio;

public class CpfInvalido extends ExcecaoDeRegraDeNegocio {
private static final long serialVersionUID = 1L;
	
	public CpfInvalido(String cpf) {
		super("O CPF " + cpf + " é inválido.");
	}
}
