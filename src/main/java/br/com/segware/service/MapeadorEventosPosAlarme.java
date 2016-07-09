package br.com.segware.service;

import br.com.segware.Tipo;
import br.com.segware.model.RegistroRelatorio;
import org.joda.time.Minutes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tulio on 09/07/16.
 */
public class MapeadorEventosPosAlarme {

    private final Integer TEMPO_DESARME_MINUTOS = 5;

    private Set<Integer> codigosSequenciaisPosAlarme;

    public MapeadorEventosPosAlarme(List<RegistroRelatorio> relatorios) {

        codigosSequenciaisPosAlarme = new HashSet<>();

        for (int i = 0; i < relatorios.size(); i++) {

            RegistroRelatorio registro = relatorios.get(i);

            if (isAlarme(registro)) {

                List<RegistroRelatorio> subRelatorio = relatorios.subList(i, relatorios.size() - 1);

                for (RegistroRelatorio subRegistro : subRelatorio) {

                    if (isSameCliente(registro, subRegistro) &&
                            isDesarme(subRegistro) &&
                            isInTempoDesarme(registro, subRegistro)) {

                        codigosSequenciaisPosAlarme.add(subRegistro.getCodigoSequencial());

                    }
                }
            }
        }

    }

    private boolean isAlarme(RegistroRelatorio registro) {

        if (Tipo.ALARME.equals(registro.getTipoEvento())) {
            return true;
        }

        return false;
    }

    private boolean isDesarme(RegistroRelatorio registro) {

        if (Tipo.DESARME.equals(registro.getTipoEvento())) {
            return true;
        }

        return false;
    }

    private boolean isInTempoDesarme(RegistroRelatorio registro, RegistroRelatorio registroRelatorio) {

        return Minutes.minutesBetween(registro.getDataInicio(), registroRelatorio.getDataInicio()).getMinutes() <= TEMPO_DESARME_MINUTOS;
    }

    private boolean isSameCliente(RegistroRelatorio registro, RegistroRelatorio comparado) {
        return registro.getCodigoCliente().equals(comparado.getCodigoCliente());
    }

    public List<Integer> getEventosPosAlarme() {
        return new ArrayList<>(codigosSequenciaisPosAlarme);
    }
}
