package massagem.api;


import massagem.aplicacao.colaborador.AdicionaColaborador;
import massagem.aplicacao.colaborador.ColaboradorDTO;
import massagem.dominio.cpf.CpfInvalido;
import massagem.excecao.ExcecaoDeCampoObrigatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/colaborador")
public class ColaboradorService {

    private final AdicionaColaborador adicionaColaborador;

    @Autowired
    public ColaboradorService(AdicionaColaborador adicionaColaborador) {
        this.adicionaColaborador = adicionaColaborador;
    }


    @RequestMapping(method= RequestMethod.POST)
    public void adicionarCandidato(@RequestBody ColaboradorDTO colaboradorDTO) throws IOException {
        //TODO: Exportar para uma classe para fazer este trabalho com os arquivos e fazer guardar varios cpfs por um tempo determinado
        File file = new File("colaboradores.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter gravarArq = new PrintWriter(file);
        gravarArq.printf(colaboradorDTO.getCpf() + "%n");
        gravarArq.close();
    }

    @RequestMapping(method= RequestMethod.POST, value = "/insere")
    public void adicionarListaDeColaboradores(@RequestBody List<ColaboradorDTO> colaboradoresDTO){
        colaboradoresDTO.forEach(colaboradorDTO -> {
            try {
                adicionaColaborador.adicionaColaborador(colaboradorDTO);
            } catch (CpfInvalido|ExcecaoDeCampoObrigatorio e) {
                e.printStackTrace();
            }
        });
    }
}
