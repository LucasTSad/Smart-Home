package org.example;

public abstract class Dispositivo {

    protected final String nome;
    protected Mediator mediator;

    public Dispositivo(String nome) {
        this.nome = nome;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public String getNome() {
        return nome;
    }

    public abstract void receber(String evento, Dispositivo remetente);
}