package service;

import model.Carro;

public class CarroServiceLigadoEmVelocidadeMaxima implements CarroService {
    @Override
    public void acelerar(Carro carro, int velocidadeAMais) {
        System.out.println("Já está na velocidade máxima!");
    }

    @Override
    public void frear(Carro carro, int velocidadeAMenos) {
        if (velocidadeAMenos < 1) {
            System.out.println("Erro: velocidade deve ser positiva.");
            return;
        }

        System.out.println("Freando.");

        if (velocidadeAMenos >= carro.getVelocidadeAtual()) {
            carro.setVelocidadeAtual(0);
            carro.setEstadoAtual(carro.getLigadoParado());
            System.out.println("Carro parado.");
        } else {
            carro.setVelocidadeAtual(carro.getVelocidadeAtual() - velocidadeAMenos);
            carro.setEstadoAtual(carro.getLigadoEmMovimento());
            System.out.println("Velocidade atual: "+carro.getVelocidadeAtual());
        }
    }

    @Override
    public void ligar(Carro carro) {
        System.out.println("Carro já está ligado.");
    }

    @Override
    public void desligar(Carro carro) {
        System.out.println("Carro em movimento. Freie para desligar.");
    }

    @Override
    public String estadoAtual(Carro carro) {
        return "Carro na velocidade máxima!";
    }
}
