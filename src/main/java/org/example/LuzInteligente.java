package org.example;

public class LuzInteligente extends Dispositivo {

    private boolean ligada = false;
    private int notificacoesRecebidas = 0;

    public LuzInteligente(String nome) {
        super(nome);
    }

    public void ligar() {
        ligada = true;
        mediator.notificar(this, "LUZ_LIGADA");
    }

    public void desligar() {
        ligada = false;
        mediator.notificar(this, "LUZ_DESLIGADA");
    }

    @Override
    public void receber(String evento, Dispositivo remetente) {
        notificacoesRecebidas++;
        if (evento.equals("MOVIMENTO_DETECTADO")) {
            ligada = true;
        } else if (evento.equals("SEM_MOVIMENTO")) {
            ligada = false;
        }
    }

    public boolean isLigada()                  { return ligada; }
    public int getNotificacoesRecebidas()       { return notificacoesRecebidas; }
}