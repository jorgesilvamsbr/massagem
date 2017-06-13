package massagem.dominio;

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

    public static final int NUMERO_MAXIMO_DE_COLABORADORES_PERMITIDOS = 11;
    private final MassagemRepository massagemRepository;
    private Map<Colaborador, Integer> colaboradorComCalculoDoCoefiente = new HashMap<>();

    public OrdenadorDeMassagem(MassagemRepository massagemRepository) {
        this.massagemRepository = massagemRepository;
    }

    public List<Colaborador> obterSelecionados(List<Colaborador> colaboradoresInteressados) {
        List<Massagem> massagens = (List<Massagem>) this.massagemRepository.findAll();

        colaboradoresInteressados.forEach(colaborador -> {
            Integer coeficienteDoColaborador = validarUltimasMassagensDentroDoPeriodoDe60Dias(massagens, colaborador);
            colaboradorComCalculoDoCoefiente.put(colaborador, coeficienteDoColaborador);
        });

        return selecionarApenasOsDozePrimeirosColaboradores();
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

        for (Massagem massagem : massagens ) {
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
