package impl;

import dominio.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CampeonatoBrasileiroImpl implements CampeonatoBrasileiro{

    private Map<Integer, List<Jogo>> brasileirao;
    private List<Jogo> jogos;
    private Predicate<Jogo> filtro;

    private List<Jogo> jogosFiltrados;

    public CampeonatoBrasileiroImpl(Path arquivo, Predicate<Jogo> filtro) throws IOException {
        this.jogos = lerArquivo(arquivo);
        this.filtro = filtro;
        this.brasileirao = jogos.stream()
                .filter(filtro) //filtrar por ano
                .collect(Collectors.groupingBy(
                        Jogo::rodada,
                        Collectors.mapping(Function.identity(), Collectors.toList())));
        this.jogosFiltrados = todosOsJogos();

    }

    public List<Jogo> lerArquivo(Path file) throws IOException {
        Stream<String> stream = Files.lines(file);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return stream.skip(1)
                .map(line -> line.split(";"))
                .map(strings -> {
                    Integer rodada = Integer.parseInt(strings[0]);
                    LocalDate data = LocalDate.parse(strings[1], dateFormatter);
                    LocalTime horario = LocalTime.now();
                    DayOfWeek dia = DayOfWeek.MONDAY;
                    DataDoJogo dataDoJogo = new DataDoJogo(data, horario, dia);
                    Time mandante = new Time(strings[4]);
                    Time visitante = new Time(strings[5]);
                    Time vencedor = new Time(strings[6]);
                    String arena = strings[7];
                    Integer mandanteplacar = Integer.parseInt(strings[8]);
                    Integer visitanteplacar = Integer.parseInt(strings[9]);
                    String estadoMandante = strings[10];
                    String estadoVisitante = strings[11];
                    String estadoVencedor = strings[12];
                    return new Jogo (rodada, dataDoJogo, mandante, visitante, vencedor, arena, mandanteplacar, visitanteplacar, estadoMandante, estadoVisitante, estadoVencedor);
                })
                .toList();
    }

    public IntSummaryStatistics getEstatisticasPorJogo() {
        return jogosFiltrados.stream()
                .map((jogo) -> jogo.mandantePlacar() + jogo.visitantePlacar())
                .collect(IntSummaryStatistics::new,
                         IntSummaryStatistics::accept,
                         IntSummaryStatistics::combine);
    }

    /*
    O método getEstatisticasPorJogo já calcula a média de gols por jogo.
    public Map<Jogo, Integer> getMediaGolsPorJogo() {
        return null;
    }
     */

    private List<Jogo> todosOsJogos() {
        return brasileirao.values().stream()
                .flatMap(List::stream)
                .toList();
    }

    public Long getTotalVitoriasEmCasa() {
        return jogosFiltrados.stream()
                .filter((jogo) -> jogo.mandantePlacar() > jogo.visitantePlacar())
                .count();
    }

    public Long getTotalVitoriasForaDeCasa() {
        return jogosFiltrados.stream()
                .filter((jogo) -> jogo.mandantePlacar() < jogo.visitantePlacar())
                .count();
    }

    public Long getTotalEmpates() {
        return jogosFiltrados.stream()
                .filter((jogo) -> jogo.mandantePlacar() == jogo.visitantePlacar())
                .count();
    }

    public Long getTotalJogosComMenosDe3Gols() {
        return jogosFiltrados.stream()
                .filter((jogo) -> jogo.mandantePlacar() + jogo.visitantePlacar() < 3)
                .count();
    }

    public Long getTotalJogosCom3OuMaisGols() {
        return jogosFiltrados.stream()
                .filter((jogo) -> jogo.mandantePlacar() + jogo.visitantePlacar() > 2)
                .count();
    }

    public Map<Resultado, Long> getTodosOsPlacares() {
        return jogosFiltrados.stream()
                .map((jogo) ->
                        jogo.mandantePlacar() > jogo.visitantePlacar() ?
                                new Resultado(jogo.mandantePlacar(), jogo.visitantePlacar()) :
                                new Resultado(jogo.visitantePlacar(), jogo.mandantePlacar()))
                .collect(
                        Collectors.toMap(Function.identity(), resultado -> 1L, Long::sum));
    }

    public Map.Entry<Resultado, Long> getPlacarMaisRepetido() {
        return getTodosOsPlacares().entrySet().stream().max(Map.Entry.comparingByValue()).get();
    }

    public Map.Entry<Resultado, Long> getPlacarMenosRepetido() {
        return getTodosOsPlacares().entrySet().stream().min(Map.Entry.comparingByValue()).get();
    }

    private List<Time> getTodosOsTimes() {
        return jogosFiltrados.stream()
                .map((jogo) -> jogo.mandante())
                .distinct()
                .toList();
    }

    private Map<Time, List<Jogo>> getTodosOsJogosPorTimeComoMandantes() {
        return jogosFiltrados.stream().collect(Collectors.groupingBy((jogo) -> jogo.mandante()));
    }

    private Map<Time, List<Jogo>> getTodosOsJogosPorTimeComoVisitante() {
        return jogosFiltrados.stream().collect(Collectors.groupingBy((jogo) -> jogo.visitante()));
    }

    public Map<Time, List<Jogo>> getTodosOsJogosPorTime() {
        return Stream.of(getTodosOsJogosPorTimeComoMandantes(), getTodosOsJogosPorTimeComoVisitante())
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors
                        .toMap(entry -> entry.getKey(),
                               entry -> entry.getValue(),
                               (list1, list2) -> {
                                    list1.addAll(list2);
                                    return list1;
                               }
                        )
                );
    }

    /*
    Parece desnecessário
    public Map<Time, Map<Boolean, List<Jogo>>> getJogosParticionadosPorMandanteTrueVisitanteFalse() {
        return null;
    }
     */

    public Set<PosicaoTabela> getTabela() {
        return getTodosOsJogosPorTime().entrySet().stream()
                .map(entry -> {
                    Time time = entry.getKey();

                    List<Jogo> jogosDoTime = entry.getValue();

                    Long vitorias = jogosDoTime.stream()
                            .filter(jogo -> jogo.vencedor().equals(time))
                            .count();

                    Time empate = new Time("-");
                    Long empates = jogosDoTime.stream()
                            .filter(jogo -> jogo.vencedor().equals(empate))
                            .count();

                    Long jogos = jogosDoTime.stream().count();

                    Long derrotas = jogos - vitorias - empates;

                    Long golsPositivos = jogosDoTime.stream().reduce(0L,
                            (gols, jogo) ->
                                    Long.sum(gols,
                                            (jogo.mandante().equals(time) ?
                                                    Long.valueOf(jogo.mandantePlacar()) :
                                                    Long.valueOf(jogo.visitantePlacar()))),
                            (a, b) -> a + b);

                    Long golsSofridos = jogosDoTime.stream().reduce(0L,
                            (gols, jogo) ->
                                    Long.sum(gols,
                                            (jogo.mandante().equals(time) ?
                                                    Long.valueOf(jogo.visitantePlacar()) :
                                                    Long.valueOf(jogo.mandantePlacar()))),
                            (a, b) -> a + b);

                    Long saldoDeGols = golsPositivos - golsSofridos;

                    Long pontos = 3 * vitorias + empates;

                    return new PosicaoTabela(time, vitorias, derrotas, empates, golsPositivos, golsSofridos, saldoDeGols, jogos, pontos);
                })
                .sorted(Comparator.comparing(PosicaoTabela::pontos).thenComparing(PosicaoTabela::vitorias).reversed()).
                collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /*
    Parece irrelevante
    private DayOfWeek getDayOfWeek(String dia) {
        return null;
    }
     */

    /*
    Parece irrelevante
    private Map<Integer, Integer> getTotalGolsPorRodada() {
        return null;
    }
     */

    /* Não implementado
    private Map<Time, Integer> getTotalDeGolsPorTime() {
        return null;
    }
     */

    /*
    Parece irrelevante
    private Map<Integer, Double> getMediaDeGolsPorRodada() {
        return null;
    }
     */
}