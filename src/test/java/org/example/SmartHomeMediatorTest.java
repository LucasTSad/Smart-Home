package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SmartHomeMediatorTest {

    private HubSmartHome hub;
    private SensorMovimento sensor;
    private LuzInteligente luz;
    private Termostato termostato;

    @BeforeEach
    void configurar() {
        hub        = new HubSmartHome();
        sensor     = new SensorMovimento("Sensor Sala");
        luz        = new LuzInteligente("Luz Sala");
        termostato = new Termostato("Termostato", 22.0);
        hub.registrar(sensor);
        hub.registrar(luz);
        hub.registrar(termostato);
    }

    // -- Registro de dispositivos ------------------------------------------

    @Test
    void hubRegistraTresDispositivos() {
        assertEquals(3, hub.totalDispositivos());
    }

    // -- Sensor de movimento -----------------------------------------------

    @Test
    void sensorDetectaMovimentoELigaLuz() {
        sensor.detectarMovimento();
        assertTrue(luz.isLigada());
    }

    @Test
    void sensorSemMovimentoDesligaLuz() {
        sensor.detectarMovimento();
        sensor.limpar();
        assertFalse(luz.isLigada());
    }

    @Test
    void sensorNaoNotificaASiMesmo() {
        sensor.detectarMovimento();
        assertEquals(0, sensor.getNotificacoesRecebidas());
    }

    // -- Luz inteligente ---------------------------------------------------

    @Test
    void luzLigadaNotificaTermostato() {
        luz.ligar();
        assertTrue(termostato.isAtivo());
    }

    @Test
    void luzDesligadaDesativaTermostato() {
        luz.ligar();
        luz.desligar();
        assertFalse(termostato.isAtivo());
    }

    @Test
    void luzNaoNotificaASiMesma() {
        luz.ligar();
        assertEquals(0, luz.getNotificacoesRecebidas());
    }

    // -- Termostato --------------------------------------------------------

    @Test
    void termostatoAjustaTemperatura() {
        termostato.ajustarTemperatura(25.0);
        assertEquals(25.0, termostato.getTemperatura(), 0.001);
    }

    @Test
    void ajusteDeTemperaturaNotificaOutrosDispositivos() {
        termostato.ajustarTemperatura(18.0);
        assertTrue(sensor.getNotificacoesRecebidas() > 0);
        assertTrue(luz.getNotificacoesRecebidas() > 0);
    }

    // -- Historico ---------------------------------------------------------

    @Test
    void hubRegistraEventoNoHistorico() {
        sensor.detectarMovimento();
        assertFalse(hub.getHistorico().isEmpty());
    }

    @Test
    void historicoContemEventoCorreto() {
        sensor.detectarMovimento();
        assertTrue(hub.getHistorico().get(0).contains("MOVIMENTO_DETECTADO"));
    }

    @Test
    void multiplosEventosSaoRegistradosNoHistorico() {
        sensor.detectarMovimento();
        luz.ligar();
        termostato.ajustarTemperatura(20.0);
        assertEquals(3, hub.getHistorico().size());
    }
}