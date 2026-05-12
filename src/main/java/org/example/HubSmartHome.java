package org.example;

import java.util.ArrayList;
import java.util.List;

public class HubSmartHome implements Mediator {

    private final List<Dispositivo> dispositivos = new ArrayList<>();
    private final List<String> historico = new ArrayList<>();

    @Override
    public void registrar(Dispositivo dispositivo) {
        dispositivo.setMediator(this);
        dispositivos.add(dispositivo);
    }

    @Override
    public void notificar(Dispositivo remetente, String evento) {
        String log = remetente.getNome() + ": " + evento;
        historico.add(log);

        for (Dispositivo d : dispositivos) {
            if (d != remetente) {
                d.receber(evento, remetente);
            }
        }
    }

    public List<String> getHistorico() {
        return historico;
    }

    public int totalDispositivos() {
        return dispositivos.size();
    }
}