package com.bcopstein;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

public class ServicoEstatistcaTest {
    private IEventoRepository eventoRepository;
    private ICalculoEstatistica calculoEstatistica;
    private ServicoEstatistica servicoEstatistica;

    @BeforeEach
    public void setUp() {
        eventoRepository = mock(IEventoRepository.class);
        calculoEstatistica = mock(ICalculoEstatistica.class);
        servicoEstatistica = new ServicoEstatistica(eventoRepository, calculoEstatistica);
        List<Evento> eventos = Arrays.asList(
                new Evento(1, "Corrida 1 5K", 10, 5, 2022, 5000, 1, 25, 30),
                new Evento(1, "Corrida 2 5K", 10, 5, 2023, 5000, 1, 45, 24),
                new Evento(1, "Corrida 3 5K", 10, 5, 2022, 5000, 2, 55, 43),
                new Evento(1, "Corrida 4 5K", 10, 5, 2023, 5000, 0, 57, 30),
                new Evento(1, "Corrida 5 5K", 10, 5, 2022, 5000, 0, 36, 57),
                new Evento(2, "Corrida 10K", 15, 5, 2023, 10000, 0, 49, 45));
        when(eventoRepository.todos()).thenReturn(eventos);
        when(calculoEstatistica.calculaEstatisticas(5000)).thenReturn(new EstatisticasDTO(1, 25, 30));

    }

    @Test
    public void calculaEstatisticasTest() {
        PerformanceDTO estatisticas = servicoEstatistica.calculaAumentoPerformance(5000, 2023);
        assertEquals("Corrida 2 5K", estatisticas.getProva1());
        assertEquals("Corrida 4 5K", estatisticas.getProva2());
        assertEquals(2874.0, estatisticas.getReducao(), 0.01);
    }
}
