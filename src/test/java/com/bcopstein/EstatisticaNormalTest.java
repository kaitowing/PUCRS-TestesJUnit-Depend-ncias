package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EstatisticaNormalTest {
    private IEventoRepository eventoRepository;
    private EstatisticaNormal estatisticaNormal;

    @BeforeAll
    public void setUp() {
        eventoRepository = new EventoRepositoryMock(); // Usando o dublê
        estatisticaNormal = new EstatisticaNormal(eventoRepository);
        Evento evento1 = new Evento(1, "Corrida 5K", 10, 5, 2023, 5000, 0, 25, 30);
        Evento evento2 = new Evento(1, "Corrida 5K", 10, 5, 2023, 5000, 0, 45, 24);
        Evento evento3 = new Evento(1, "Corrida 5K", 10, 5, 2023, 5000, 0, 55, 43);
        Evento evento4 = new Evento(1, "Corrida 5K", 10, 5, 2023, 5000, 0, 57, 30);
        Evento evento5 = new Evento(1, "Corrida 5K", 10, 5, 2023, 5000, 0, 36, 57);
        Evento evento6 = new Evento(2, "Corrida 10K", 15, 5, 2023, 10000, 0, 49, 45);
        

        eventoRepository.cadastra(evento1);
        eventoRepository.cadastra(evento2);
        eventoRepository.cadastra(evento3);
        eventoRepository.cadastra(evento4);
        eventoRepository.cadastra(evento5);
        eventoRepository.cadastra(evento6);
    }

    @Test
    public void testCalculaEstatisticas() {

        EstatisticasDTO estatisticas = estatisticaNormal.calculaEstatisticas(5000);

        // Realize as asserções para verificar se os resultados estão corretos
        assertEquals(1, estatisticas.getMedia());
        assertEquals(2, estatisticas.getMediana());
        assertEquals(3, estatisticas.getDesvioPadrao());
    }
}
