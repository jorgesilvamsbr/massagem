package massagem.api;

import massagem.aplicacao.ColaboradorDTO;
import massagem.aplicacao.GerarListaDeSelecionados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin("*")
@RequestMapping("/massagem")
public class MassagemService {

    private final GerarListaDeSelecionados gerarListaDeSelecionados;

    @Autowired
    public MassagemService(GerarListaDeSelecionados gerarListaDeSelecionados) {
        this.gerarListaDeSelecionados = gerarListaDeSelecionados;
    }

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<?> adicionarCandidato(@RequestBody ColaboradorDTO colaboradorDTO) {
        // TODO: Como iremos adicionar o candidato????????????
        return criarRespostaComAEmpresaAdicionada(colaboradorDTO);
    }

    private ResponseEntity<?> criarRespostaComAEmpresaAdicionada(ColaboradorDTO colaboradorDTO) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(colaboradorDTO.getId()).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }
}
