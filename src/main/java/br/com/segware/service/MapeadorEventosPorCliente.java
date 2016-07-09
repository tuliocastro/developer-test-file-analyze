package br.com.segware.service;

import br.com.segware.model.RegistroRelatorio;

import java.util.*;

/**
 * Created by tulio on 09/07/16.
 */
public class MapeadorEventosPorCliente {

    private Map<String, List<String>> mapaClienteEventos;

    public MapeadorEventosPorCliente(List<RegistroRelatorio> relatorios) {

        mapaClienteEventos = new HashMap<>();

        for (RegistroRelatorio registro : relatorios) {

            String key = registro.getCodigoCliente();

            List<String> eventos = mapaClienteEventos.get(key);

            if (eventos == null) {

                eventos = new ArrayList<String>();
                mapaClienteEventos.put(key, eventos);

            }

            eventos.add(registro.getCodigoEvento());

        }
    }


    public Map<String, Integer> getMapaAgrupado() {

        Map<String, Integer> mapaGrupo = new HashMap<>();

        for (String key : mapaClienteEventos.keySet()) {

            mapaGrupo.put(key, mapaClienteEventos.get(key).size());

        }

        return mapaGrupo;

    }
}
