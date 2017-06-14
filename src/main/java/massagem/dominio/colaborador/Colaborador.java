package massagem.dominio.colaborador;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import massagem.dominio.base.EntidadeBase;
import massagem.dominio.cpf.Cpf;
import massagem.excecao.ExcecaoDeCampoObrigatorio;

@Entity
public class Colaborador extends EntidadeBase {
    private String nome;
    @Embedded
    private Cpf cpf;

    public Colaborador(String nome, Cpf cpf) throws ExcecaoDeCampoObrigatorio {
        validarCampos(nome, cpf);
        this.cpf = cpf;
        this.nome = nome;
    }

    private void validarCampos(String nome, Cpf cpf) throws ExcecaoDeCampoObrigatorio {
        new ExcecaoDeCampoObrigatorio()
                .quandoVazio(nome, "O nome informado está vazio")
                .quandoNulo(cpf, "O cpf informado está nulo")
                .entaoDispara();
    }

    public String getNome() {
        return nome;
    }

    public String getCPF() {
        return cpf.getValorSemMascara();
    }
}
