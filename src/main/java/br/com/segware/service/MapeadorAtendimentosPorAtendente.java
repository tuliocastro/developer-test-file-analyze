package br.com.segware.service;

import br.com.segware.model.Atendimento;
import br.com.segware.model.RegistroRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tulio on 09/07/16.
 */
public class MapeadorAtendimentosPorAtendente {


    private Map<String, List<Atendimento>> mapaAtendimento;

    public MapeadorAtendimentosPorAtendente(List<RegistroRelatorio> relatorios) {

        mapaAtendimento = new HashMap<>();

        for (RegistroRelatorio registro : relatorios) {

            String key = registro.getCodigoAtendente();

            List<Atendimento> eventos = mapaAtendimento.get(key);

            if (eventos == null) {

                eventos = new ArrayList<Atendimento>();
                mapaAtendimento.put(key, eventos);

            }

            Atendimento atendimento = new Atendimento(registro);
            eventos.add(atendimento);

        }
    }

    private Long getSomaTotalAtendimento(String codAtendente) {

        List<Atendimento> atendimentos = mapaAtendimento.get(codAtendente);

        Long total = 0L;

        for (Atendimento atendimento : atendimentos) {
            total += atendimento.getTempoAtendimentoSegundos();
        }

        return total;

    }

    private Long getMediaAtendimento(String codAtendente) {

        List<Atendimento> atendimentos = mapaAtendimento.get(codAtendente);

        Long somaAtendimentos = getSomaTotalAtendimento(codAtendente);

        return somaAtendimentos / atendimentos.size();

    }

    public Map<String, Long> getMapaAgrupado() {

        Map<String, Long> mapaGrupo = new HashMap<>();

        for (String key : mapaAtendimento.keySet()) {

            Long mediaAtendimento = getMediaAtendimento(key);

            mapaGrupo.put(key, mediaAtendimento);

        }

        return mapaGrupo;

    }


}
