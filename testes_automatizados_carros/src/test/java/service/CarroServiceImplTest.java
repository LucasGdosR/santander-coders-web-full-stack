package service;

import model.Carro;
import org.junit.Assert;
import org.junit.Test;

public class CarroServiceImplTest {

    @Test
    public void deveAcelerarCorretamente() {
        // Given
        Carro carroTeste01 = new Carro("Azul", "Fiat", "Uno", 2015, 150);
        carroTeste01.ligar();

        // When
        carroTeste01.acelerar(10);

        // Then
        // Assertivas
        // O Junit busca por Asserts no método para validar que o teste passou
        Assert.assertTrue(carroTeste01.getVelocidadeAtual() == 10);
    }

    @Test
    public void deveLigarCorretamente() {
        // Dado
        Carro carro =
                new Carro("vermelho", "marca", "modelo", 1990, 100);

        // Quando
        carro.ligar();

        // Então
        Assert.assertEquals("Carro ligado e parado.", carro.estadoAtual());
    }

    @Test
    public void velocidadeNaoDeveSerMenorQueZero() {
        // O Junit busca por Assets no método para validar que o teste passou

        // Dado:
        Carro celtinha = new Carro("Prata", "Chevrolet", "Celta", 2001, 50);
        celtinha.ligar();

        // Quando:
        celtinha.frear(10);

        // Então:
        Assert.assertEquals("Carro ligado e parado.", celtinha.estadoAtual());
        Assert.assertEquals(0, celtinha.getVelocidadeAtual());
    }

    @Test
    public void deveLigarCorretamenteEAcelerarCorretamente() {
        // Dado:
        Carro celtinha = new Carro("Prata", "Chevrolet", "Celta", 2001, 50);

        // Quando:
        celtinha.ligar();
        celtinha.acelerar(10);

        // Então:
        Assert.assertEquals("Carro ligado em movimento. Velocidade: "+celtinha.getVelocidadeAtual(), celtinha.estadoAtual());
        Assert.assertEquals(10, celtinha.getVelocidadeAtual());
    }

}
