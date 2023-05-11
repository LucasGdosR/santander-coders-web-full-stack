package service;

import model.Carro;

public class CarroServiceLigadoEmMovimento implements CarroService {

    @Override
    public void acelerar(Carro carro, int velocidadeAMais) {
        if (velocidadeAMais < 1) {
            System.out.println("Erro: velocidade deve ser positiva.");
            return;
        }

        System.out.println("Acelerando.");

        if (carro.getVelocidadeAtual() + velocidadeAMais >= carro.getVelocidadeMaxima()) {
            carro.setVelocidadeAtual(carro.getVelocidadeMaxima());
            carro.setEstadoAtual(carro.getLigadoEmVelocidadeMaxima());
            System.out.println("Velocidade máxima!");
        } else {
            carro.setVelocidadeAtual(carro.getVelocidadeAtual() + velocidadeAMais);
            System.out.println("Velocidade atual: "+carro.getVelocidadeAtual());
        }
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
        return "Carro ligado em movimento. Velocidade: "+carro.getVelocidadeAtual();
    }
}
