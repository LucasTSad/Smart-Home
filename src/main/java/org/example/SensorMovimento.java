package org.example;

public class SensorMovimento extends Dispositivo {

    private boolean movimentoDetectado = false;
    private int notificacoesRecebidas = 0;

    public SensorMovimento(String nome) {
        super(nome);
    }

    public void detectarMovimento() {
        movimentoDetectado = true;
        mediator.notificar(this, "MOVIMENTO_DETECTADO");
    }

    public void limpar() {
        movimentoDetectado = false;
        mediator.notificar(this, "SEM_MOVIMENTO");
    }

    @Override
    public void receber(String evento, Dispositivo remetente) {
        notificacoesRecebidas++;
    }

    public boolean isMovimentoDetectado() { return movimentoDetectado; }
    public int getNotificacoesRecebidas() { return notificacoesRecebidas; }
}