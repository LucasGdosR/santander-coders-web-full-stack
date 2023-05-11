package model;

import service.*;

public class Carro {
    private String cor;
    private String marca;
    private String modelo;
    private int ano;
    private int velocidadeAtual;
    private int velocidadeMaxima;
    private final CarroService desligado;
    private final CarroService ligadoParado;
    private final CarroService ligadoEmMovimento;
    private final CarroService ligadoEmVelocidadeMaxima;
    private CarroService estadoAtual;

    public Carro(String cor, String marca, String modelo, int ano, int velocidadeMaxima) {
        this.cor = cor;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.velocidadeAtual = 0;
        this.velocidadeMaxima = velocidadeMaxima;
        desligado = new CarroServiceDesligado();
        ligadoParado = new CarroServiceLigadoParado();
        ligadoEmMovimento = new CarroServiceLigadoEmMovimento();
        ligadoEmVelocidadeMaxima = new CarroServiceLigadoEmVelocidadeMaxima();
        estadoAtual = getDesligado();
    }

    public void setEstadoAtual(CarroService estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    public CarroService getDesligado() {
        return desligado;
    }

    public CarroService getLigadoParado() {
        return ligadoParado;
    }

    public CarroService getLigadoEmMovimento() {
        return ligadoEmMovimento;
    }

    public CarroService getLigadoEmVelocidadeMaxima() {
        return ligadoEmVelocidadeMaxima;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getVelocidadeAtual() {
        return velocidadeAtual;
    }

    public void setVelocidadeAtual(int velocidadeAtual) {
        this.velocidadeAtual = velocidadeAtual;
    }

    public int getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    public void setVelocidadeMaxima(int velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "cor='" + cor + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano='" + ano + '\'' +
                ", velocidadeAtual=" + velocidadeAtual +
                ", velocidadeMaxima=" + velocidadeMaxima +
                '}';
    }

    public void acelerar(int velocidadeAMais) {
        estadoAtual.acelerar(this, velocidadeAMais);
    }

    public void frear(int velocidadeAMenos) {
        estadoAtual.frear(this, velocidadeAMenos);
    }

    public void ligar() {
        estadoAtual.ligar(this);
    }

    public void desligar() {
        estadoAtual.desligar(this);
    }

    public String estadoAtual() {
        return estadoAtual.estadoAtual(this);
    }
}
