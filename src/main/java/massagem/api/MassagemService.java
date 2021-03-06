package massagem.api;

import massagem.aplicacao.colaborador.ColaboradorDTO;
import massagem.aplicacao.colaborador.ConsultaListaDeColaboradoresSelecionados;
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

    private final ConsultaListaDeColaboradoresSelecionados geraListaDeSelecionados;

    @Autowired
    public MassagemService(ConsultaListaDeColaboradoresSelecionados geraListaDeSelecionados) {
        this.geraListaDeSelecionados = geraListaDeSelecionados;
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
