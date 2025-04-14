package com.example.appdereunioes.ui.evento;

import java.util.ArrayList;
import java.util.List;

public class EventoRepository {
    private static final List<Evento> eventos = new ArrayList<>();

    public static void adicionarEvento(Evento evento) {
        eventos.add(evento);
    }

    public static List<Evento> getEventos() {
        return eventos;
    }
}
