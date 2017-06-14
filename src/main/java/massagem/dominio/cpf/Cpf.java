package massagem.dominio.cpf;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Cpf implements Serializable {

	private static final int ONZE = 11;

	private static final int NOVE = 9;

	private static final long serialVersionUID = 1L;

	@Column(length = 14)
	private String cpf = "";

	@Transient
	private static final int[] peso = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	private Cpf() {
	}

	private Cpf(String cpf) throws CpfInvalido {
		this.cpf = cpf;
	}
	
	public static Cpf criar(String cpf) throws CpfInvalido {
		valicarCampoObrigatorio(cpf);
		cpf = removerEspacosEmBranco(cpf);
		String cpfSemMascara = removerCaracteresEspeciais(cpf);
		validarSeCpfPossuiLetras(cpfSemMascara);
		validarAQuantidadeDeDigitos(cpfSemMascara);
		validarSequenciaDeDigitosRepetidos(cpfSemMascara);
		validarDigitosVerificadores(cpfSemMascara);
		return new Cpf(cpf);
	}

	private static String removerEspacosEmBranco(String cpf) {
		return cpf.replace(" ", "");
	}

	private static String removerCaracteresEspeciais(String cpf) {
		return cpf.replace(".", "").replaceAll("-", "");
	}

	private static void valicarCampoObrigatorio(String cpf) throws CpfInvalido {
		if (cpf == null) {
			throw new CpfInvalido(cpf);
		}
	}

	public static void validarDigitosVerificadores(String cpf) throws CpfInvalido {
		Integer digito1 = calcularDigito(cpf.substring(0, NOVE));
		Integer digito2 = calcularDigito(cpf.substring(0, NOVE) + digito1);
		String cpfCalculado = cpf.substring(0, NOVE) + digito1.toString() + digito2.toString();
		
		if(!cpf.equals(cpfCalculado)) {
			throw new CpfInvalido(cpf);
		}
	}

	private static void validarSequenciaDeDigitosRepetidos(String cpf) throws CpfInvalido {
		if(cpf.matches("([0]{11}|[1]{11}|[2]{11}|[3]{11}|[4]{11}|[5]{11}|[6]{11}|[7]{11}|[8]{11}|[9]{11})")) {
			throw new CpfInvalido(cpf);
		}
	}

	private static void validarAQuantidadeDeDigitos(String cpf) throws CpfInvalido {
		if (cpf.length() != ONZE) {
			throw new CpfInvalido(cpf);
		}
	}
	
	private static void validarSeCpfPossuiLetras(String cpf) throws CpfInvalido {
		try {
			Long.parseLong(cpf);
		} catch (NumberFormatException e) {
			throw new CpfInvalido(cpf);
		}
	}

	private static int calcularDigito(String str) {
		int soma = 0;
		int digito;
		for (int indice = str.length() - 1; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = ONZE - soma % ONZE;
		return soma > NOVE ? 0 : soma;
	}

	public String getValor() {
		return this.cpf;
	}

	public String getValorSemMascara() {
		return this.cpf.replace(".", "").replace("-", "");
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Cpf)) {
			return false;
		}
		Cpf outro = (Cpf) o;
		return this.cpf.equals(outro.cpf);
	}

	@Override
	public int hashCode() {
		return this.cpf.hashCode();
	}

}
