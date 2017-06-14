package massagem.dominio.colaborador;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import massagem.dominio.base.EntidadeBase;
import massagem.dominio.cpf.Cpf;
import massagem.excecao.ExcecaoDeCampoObrigatorio;

@Entity
public class Colaborador extends EntidadeBase {

    private String nome;
    private String matricula;
    @Embedded
    private Cpf cpf;

    public Colaborador(String nome, String matricula, Cpf cpf) throws ExcecaoDeCampoObrigatorio {
        validarCampos(nome, matricula, cpf);
        this.cpf = cpf;
        this.cpf = cpf;
        this.nome = nome;
        this.matricula = matricula;
    }

    private void validarCampos(String nome, String matricula, Cpf cpf) throws ExcecaoDeCampoObrigatorio {
        new ExcecaoDeCampoObrigatorio()
                .quandoVazio(nome, "O nome informado está vazio")
                .quandoVazio(matricula, "A matricula informada está vazia")
                .quandoNulo(cpf, "O cpf informado está nulo")
                .entaoDispara();
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCPF() {
        return cpf.getValorSemMascara();
    }
}
