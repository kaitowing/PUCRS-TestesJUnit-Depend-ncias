package com.bcopstein;

import java.util.ArrayList;
import java.util.List;

public class EventoRepositoryMock implements IEventoRepository {
    private List<Evento> eventos = new ArrayList<>();

    @Override
    public List<Evento> todos() {
        return eventos;
    }

    @Override
    public boolean cadastra(Evento evento) {
        eventos.add(evento);
        return true;
    }
}
