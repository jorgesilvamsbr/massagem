package massagem.dominio.massagem;

import massagem.aplicacao.massagem.ApagaMassagemVencida;
import massagem.dominio.colaborador.Colaborador;
import massagem.repositorio.MassagemRepository;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrdenadorDeMassagem {

    private static final int NUMERO_MAXIMO_DE_COLABORADORES_PERMITIDOS = 11;
    private Map<Colaborador, Integer> colaboradorComCalculoDoCoefiente = new HashMap<>();
    private final MassagemRepository massagemRepository;
    private final ApagaMassagemVencida apagaMassagemVencida;

    public OrdenadorDeMassagem(MassagemRepository massagemRepository, ApagaMassagemVencida apagaMassagemVencida) {
        this.massagemRepository = massagemRepository;
        this.apagaMassagemVencida = apagaMassagemVencida;
    }

    public List<Colaborador> obterSelecionados(List<Colaborador> colaboradoresInteressados) {
        apagaMassagemVencida.apagar();
        List<Massagem> massagens = (List<Massagem>) this.massagemRepository.findAll();
        priorizarInteressados(colaboradoresInteressados, massagens);
        return selecionarApenasOsDozePrimeirosColaboradores();
    }

    private void priorizarInteressados(List<Colaborador> colaboradoresInteressados, List<Massagem> massagens) {
        colaboradoresInteressados.forEach(colaborador -> {
            Integer coeficienteDoColaborador = validarUltimasMassagensDentroDoPeriodoDe60Dias(massagens, colaborador);
            colaboradorComCalculoDoCoefiente.put(colaborador, coeficienteDoColaborador);
        });
    }

    private List<Colaborador> selecionarApenasOsDozePrimeirosColaboradores() {
        List<Colaborador> colaboradoresFiltrados = colaboradorComCalculoDoCoefiente.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .map(colaborador -> colaborador.getKey()).collect(Collectors.toList());
        return colaboradoresFiltrados.size() >= NUMERO_MAXIMO_DE_COLABORADORES_PERMITIDOS ? colaboradoresFiltrados.subList(0, NUMERO_MAXIMO_DE_COLABORADORES_PERMITIDOS) : colaboradoresFiltrados;
    }

    private Integer validarUltimasMassagensDentroDoPeriodoDe60Dias(List<Massagem> massagens, Colaborador colaborador) {
        int quantidadeDeMassagensRealizadasNosUltimos60Dias = 0;
        int intervaloDeDiasMaisProximoDaDataDeHoje = Integer.MAX_VALUE;
        LocalDate ultimaDataDeMassagem = LocalDate.MIN;

        for (Massagem massagem : massagens) {
            if (colaborador.getMatricula().equals(massagem.getColaborador().getMatricula())) {
                quantidadeDeMassagensRealizadasNosUltimos60Dias++;
                Period intervalo = Period.between(massagem.getData(), LocalDate.now());
                if (ChronoUnit.DAYS.between(massagem.getData(), LocalDate.now()) < intervaloDeDiasMaisProximoDaDataDeHoje) {
                    intervaloDeDiasMaisProximoDaDataDeHoje = intervalo.getDays();
                    ultimaDataDeMassagem = massagem.getData();
                }
            }
        }
        return Integer.valueOf(
                        String.valueOf(quantidadeDeMassagensRealizadasNosUltimos60Dias)
                        + String.valueOf(ultimaDataDeMassagem.getMonth().getValue())
                        + String.valueOf(ultimaDataDeMassagem.getDayOfMonth()));
    }
}
