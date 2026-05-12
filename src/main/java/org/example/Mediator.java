package org.example;

public interface Mediator {
    void registrar(Dispositivo dispositivo);
    void notificar(Dispositivo remetente, String evento);
}