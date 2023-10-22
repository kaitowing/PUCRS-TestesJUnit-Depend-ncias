package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstatisticaDesconsideraTest {
    private IEventoRepository eventoRepository;
    private EstatisticaDesconsidera estatisticaDesconsidera;

    private IEventoRepository eventoRepositoryImpar;
    private EstatisticaDesconsidera estatisticaDesconsideraImpar;

    @BeforeEach
    public void setUp() {
        eventoRepository = mock(IEventoRepository.class);
        eventoRepositoryImpar = mock(IEventoRepository.class);
        estatisticaDesconsideraImpar = new EstatisticaDesconsidera(eventoRepositoryImpar);
        estatisticaDesconsidera = new EstatisticaDesconsidera(eventoRepository);
        List<Evento> eventos = Arrays.asList(
            new Evento(1, "Corrida 1 5K", 10, 5, 2023, 5000, 1, 25, 30),
            new Evento(1, "Corrida 2 5K", 10, 5, 2023, 5000, 1, 45, 24),
            new Evento(1, "Corrida 3 5K", 10, 5, 2023, 5000, 2, 55, 43),
            new Evento(1, "Corrida 4 5K", 10, 5, 2023, 5000, 0, 57, 30),
            new Evento(1, "Corrida 5 5K", 10, 5, 2023, 5000, 0, 36, 57),
            new Evento(2, "Corrida 10K", 15, 5, 2023, 10000, 0, 49, 45)
        );

        List<Evento> eventosImpar = Arrays.asList(
            new Evento(1, "Corrida 1 5K", 10, 5, 2023, 5000, 1, 25, 30),
            new Evento(2, "Corrida 2 5K", 10, 5, 2023, 5000, 1, 45, 24),
            new Evento(3, "Corrida 3 5K", 10, 5, 2023, 5000, 2, 55, 43),
            new Evento(4, "Corrida 4 5K", 10, 5, 2023, 5000, 0, 57, 30),
            new Evento(5, "Corrida 5 5K", 10, 5, 2023, 5000, 0, 36, 57),
            new Evento(6, "Corrida 10K", 15, 5, 2023, 10000, 0, 49, 45),
            new Evento(7, "Corrida 10K", 15, 5, 2023, 5000, 0, 49, 45)
        );

        when(eventoRepository.todos()).thenReturn(eventos);
        when(eventoRepositoryImpar.todos()).thenReturn(eventosImpar);
    }

    @Test
    public void testCalculaEstatisticas() {

        EstatisticasDTO estatisticas = estatisticaDesconsidera.calculaEstatisticas(5000);

        assertEquals(6772.33, estatisticas.getMedia(),0.01);
        assertEquals(6324.0, estatisticas.getMediana(),0.01);
        assertEquals(2913.01, estatisticas.getDesvioPadrao(),0.01);
    }

    @Test
    public void testCalculaEstatisticasImpar() {

        EstatisticasDTO estatisticas = estatisticaDesconsideraImpar.calculaEstatisticas(5000);

        assertEquals(5633.5, estatisticas.getMedia(),0.01);
        assertEquals(4887.0, estatisticas.getMediana(),0.01);
        assertEquals(3202.35, estatisticas.getDesvioPadrao(),0.01);
    }
}
