package massagem.dominio;

import massagem.dominio.base.EntidadeBase;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class OrdenadorDeMassagem extends EntidadeBase {


    public List<Colaborador> obterSelecionados(List<Colaborador> colaboradoresInteressados) {
        return null;
    }
}
