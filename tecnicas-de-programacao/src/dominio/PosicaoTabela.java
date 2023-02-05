package dominio;

public record PosicaoTabela(Time time,
                            Long vitorias,
                            Long derrotas,
                            Long empates,
                            Long golsPositivos,
                            Long golsSofridos,
                            Long saldoDeGols,
                            Long jogos,
                            Long pontos) {

    @Override
    public String toString() {
        return  time +
                ", pontos=" + pontos +
                ", vitorias=" + vitorias +
                ", derrotas=" + derrotas +
                ", empates=" + empates +
                ", golsPositivos=" + golsPositivos +
                ", golsSofridos=" + golsSofridos +
                ", saldoDeGols=" + saldoDeGols +
                ", jogos=" + jogos +
                '}';
    }
}
