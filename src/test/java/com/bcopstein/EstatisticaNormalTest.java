package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstatisticaNormalTest {
    private IEventoRepository eventoRepository;
    private EstatisticaNormal estatisticaNormal;

    private IEventoRepository eventoRepositoryImpar;
    private EstatisticaNormal estatisticaNormalImpar;

    @BeforeEach
    public void setUp() {
        eventoRepository = mock(IEventoRepository.class);
        eventoRepositoryImpar = mock(IEventoRepository.class);
        estatisticaNormalImpar = new EstatisticaNormal(eventoRepositoryImpar);
        estatisticaNormal = new EstatisticaNormal(eventoRepository);
        List<Evento> eventos = Arrays.asList(
            new Evento(1, "Corrida 1 5K", 10, 5, 2023, 5000, 1, 25, 30),
            new Evento(1, "Corrida 2 5K", 10, 5, 2023, 5000, 1, 45, 24),
            new Evento(1, "Corrida 3 5K", 10, 5, 2023, 5000, 2, 55, 43),
            new Evento(1, "Corrida 4 5K", 10, 5, 2023, 5000, 0, 57, 30),
            new Evento(1, "Corrida 5 5K", 10, 5, 2023, 5000, 0, 36, 57),
            new Evento(2, "Corrida 10K", 15, 5, 2023, 10000, 0, 49, 45)
        );

        List<Evento> eventos2 = Arrays.asList(
            new Evento(1, "Corrida 1 5K", 10, 5, 2023, 5000, 1, 25, 30),
            new Evento(1, "Corrida 2 5K", 10, 5, 2023, 5000, 1, 45, 24),
            new Evento(1, "Corrida 3 5K", 10, 5, 2023, 5000, 2, 55, 43),
            new Evento(1, "Corrida 4 5K", 10, 5, 2023, 5000, 0, 57, 30),
            new Evento(1, "Corrida 5 5K", 10, 5, 2023, 5000, 0, 36, 57),
            new Evento(2, "Corrida 10K", 15, 5, 2023, 10000, 0, 49, 45),
            new Evento(3, "Corrida 5K", 15, 5, 2023, 5000, 0, 49, 45)
        );

        when(eventoRepository.todos()).thenReturn(eventos);
        when(eventoRepositoryImpar.todos()).thenReturn(eventos2);
    }

    @Test
    public void testCalculaEstatisticas() {

        EstatisticasDTO estatisticas = estatisticaNormal.calculaEstatisticas(5000);

        assertEquals(5532.8, estatisticas.getMedia(), 0.01);
        assertEquals(5130.0, estatisticas.getMediana(), 0.01);
        assertEquals(2871.34, estatisticas.getDesvioPadrao(), 0.01);
    }
    
    @Test
    public void testCalculaEstatisticasImpar() {

        EstatisticasDTO estatisticas = estatisticaNormalImpar.calculaEstatisticas(5000);

        assertEquals(5108.17, estatisticas.getMedia(), 0.01);
        assertEquals(4290.0, estatisticas.getMediana(), 0.01);
        assertEquals(2787.84, estatisticas.getDesvioPadrao(), 0.01);
    }
}
