package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstatisticaDesconsideraIntegracaoTest {
  private IEventoRepository eventoRep;
  private ServicoEstatistica servicoEstatistica;

  @BeforeEach
  public void setUp() {
    List<Evento> eventos = Arrays.asList(
            new Evento(1, "Corrida 1 5K", 10, 5, 2023, 5000, 1, 25, 30),
            new Evento(1, "Corrida 2 5K", 10, 5, 2023, 5000, 1, 45, 24),
            new Evento(1, "Corrida 3 5K", 10, 5, 2023, 5000, 2, 55, 43),
            new Evento(1, "Corrida 4 5K", 10, 5, 2023, 5000, 0, 57, 30),
            new Evento(1, "Corrida 5 5K", 10, 5, 2023, 5000, 0, 36, 57),
            new Evento(2, "Corrida 10K", 15, 5, 2023, 10000, 0, 49, 45)
        );
    eventoRep = mock(IEventoRepository.class);
    servicoEstatistica = new ServicoEstatistica(eventoRep, new EstatisticaDesconsidera(eventoRep));

    when(eventoRep.todos()).thenReturn(eventos);
  }

  @Test
  public void testCalculaEstatisticas() {
    EstatisticasDTO estatisticas = servicoEstatistica.calculaEstatisticas(5000);

    assertEquals(6772.33, estatisticas.getMedia(),0.01);
    assertEquals(6324.0, estatisticas.getMediana(),0.01);
    assertEquals(2913.01, estatisticas.getDesvioPadrao(),0.01);
  }
}
