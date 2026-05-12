package org.example;

public class Termostato extends Dispositivo {

    private double temperatura;
    private boolean ativo = true;
    private int notificacoesRecebidas = 0;

    public Termostato(String nome, double temperaturaInicial) {
        super(nome);
        this.temperatura = temperaturaInicial;
    }

    public void ajustarTemperatura(double novaTemperatura) {
        this.temperatura = novaTemperatura;
        mediator.notificar(this, "TEMPERATURA_ALTERADA:" + novaTemperatura);
    }

    @Override
    public void receber(String evento, Dispositivo remetente) {
        notificacoesRecebidas++;
        if (evento.equals("LUZ_DESLIGADA")) {
            ativo = false;
        } else if (evento.equals("LUZ_LIGADA")) {
            ativo = true;
        }
    }

    public double getTemperatura()          { return temperatura; }
    public boolean isAtivo()                { return ativo; }
    public int getNotificacoesRecebidas()   { return notificacoesRecebidas; }
}