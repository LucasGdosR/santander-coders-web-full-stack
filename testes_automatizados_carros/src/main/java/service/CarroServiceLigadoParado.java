package service;

import model.Carro;

public class CarroServiceLigadoParado implements CarroService {
    @Override
    public void acelerar(Carro carro, int velocidadeAMais) {
        if (velocidadeAMais < 1) {
            System.out.println("Erro: velocidade deve ser positiva.");
            return;
        }

        System.out.println("Acelerando!");

        if (velocidadeAMais >= carro.getVelocidadeMaxima()) {
            carro.setVelocidadeAtual(carro.getVelocidadeMaxima());
            carro.setEstadoAtual(carro.getLigadoEmVelocidadeMaxima());
            System.out.println("Velocidade máxima!");
        } else {
            carro.setVelocidadeAtual(velocidadeAMais);
            carro.setEstadoAtual(carro.getLigadoEmMovimento());
            System.out.println("Velocidade atual: "+velocidadeAMais);
        }
    }

    @Override
    public void frear(Carro carro, int velocidadeAMenos) {
        System.out.println("Carro já está parado.");
    }

    @Override
    public void ligar(Carro carro) {
        System.out.println("Carro já está ligado.");
    }

    @Override
    public void desligar(Carro carro) {
        System.out.println("Desligando carro.");
        carro.setEstadoAtual(carro.getDesligado());
        System.out.println("Carro desligado.");
    }

    @Override
    public String estadoAtual(Carro carro) {
        return "Carro ligado e parado.";
    }
}
