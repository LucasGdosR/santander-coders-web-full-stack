package service;

import model.Carro;

public class CarroServiceDesligado implements CarroService {
    @Override
    public void acelerar(Carro carro, int velocidadeAMais) {
        System.out.println("Carro está desligado. Ligue antes de acelerar.");
    }

    @Override
    public void frear(Carro carro, int velocidadeAMenos) {
        System.out.println("Carro está desligado. Ligue antes de frear.");
    }

    @Override
    public void ligar(Carro carro) {
        System.out.println("Ligando carro.");
        carro.setEstadoAtual(carro.getLigadoParado());
        System.out.println("Carro ligado.");
    }

    @Override
    public void desligar(Carro carro) {
        System.out.println("Carro já está desligado.");
    }

    @Override
    public String estadoAtual(Carro carro) {
        return "Carro desligado e parado.";
    }
}
