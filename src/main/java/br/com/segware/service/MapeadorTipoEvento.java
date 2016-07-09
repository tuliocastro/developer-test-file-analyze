package br.com.segware.service;

import br.com.segware.Tipo;
import br.com.segware.model.RegistroRelatorio;

import java.util.*;

/**
 * Created by tulio on 09/07/16.
 */
public class MapeadorTipoEvento {

    private Map<Tipo, List<String>> mapaTipoEventos;

    public MapeadorTipoEvento(List<RegistroRelatorio> relatorios) {

        mapaTipoEventos = new HashMap<>();

        for (RegistroRelatorio registro : relatorios) {

            Tipo key = registro.getTipoEvento();

            List<String> eventos = mapaTipoEventos.get(key);

            if (eventos == null) {

                eventos = new ArrayList<String>();
                mapaTipoEventos.put(key, eventos);

            }

            eventos.add(registro.getCodigoEvento());

        }
    }

    private Map<Tipo, Integer> agruparTiposPorQuantidadeEventos() {


        Map<Tipo, Integer> mapaGrupo = new HashMap<>();

        for (Tipo key : mapaTipoEventos.keySet()) {

            List<String> eventos = mapaTipoEventos.get(key);

            mapaGrupo.put(key, eventos.size());

        }

        return mapaGrupo;

    }

    public List<Tipo> getListaTipoOrdenadaDecrescente() {

        Map<Tipo, Integer> mapa = agruparTiposPorQuantidadeEventos();

        List<Map.Entry<Tipo, Integer>> mapaOrdenado = new ArrayList<>(mapa.entrySet());
        Collections.sort(mapaOrdenado, new OrdenadorPorValor<Tipo, Integer>());

        List<Tipo> listaTiposOrdenada = new ArrayList<>();

        for (Map.Entry<Tipo, Integer> entry : mapaOrdenado) {

            listaTiposOrdenada.add(entry.getKey());

        }

        return listaTiposOrdenada;
    }


}

class OrdenadorPorValor<K, V extends Comparable<V>> implements Comparator<Map.Entry<K, V>> {

    public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
        return o2.getValue().compareTo(o1.getValue());
    }

}
