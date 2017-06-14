package massagem.api;


import massagem.aplicacao.colaborador.ColaboradorDTO;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/colaborador")
public class ColaboradorService {
    public ColaboradorService() {
    }


    @RequestMapping(method= RequestMethod.POST)
    public void adicionarCandidato(@RequestBody ColaboradorDTO colaboradorDTO) throws IOException {
        FileWriter arquivo = new FileWriter("colaboradores.txt");
        PrintWriter gravarArq = new PrintWriter(arquivo);
        gravarArq.printf(colaboradorDTO.getCpf());
        arquivo.close();
    }


    @RequestMapping(method= RequestMethod.POST)
    public void adicionarListaDeColaboradores(@RequestBody List<ColaboradorDTO> colaboradorDTO) throws IOException {
        FileWriter arquivo = new FileWriter("colaboradores.txt");
        PrintWriter gravarArq = new PrintWriter(arquivo);
        gravarArq.printf(colaboradorDTO.getCpf());
        arquivo.close();
    }
}
