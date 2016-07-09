package br.com.segware;

import br.com.segware.model.RegistroRelatorio;
import br.com.segware.service.MapeadorAtendimentosPorAtendente;
import br.com.segware.service.MapeadorEventosPorCliente;
import br.com.segware.service.MapeadorEventosPosAlarme;
import br.com.segware.service.MapeadorTipoEvento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Created by tulio on 07/07/16.
 */
public class AnalisadorRelatorioImpl implements IAnalisadorRelatorio {

    private final String CSV_DELIMITADOR = ",";

    private List<RegistroRelatorio> relatorios;

    public AnalisadorRelatorioImpl() {
        this.relatorios = this.carregarRelatorio();
    }

    @Override
    public Map<String, Integer> getTotalEventosCliente() {

        MapeadorEventosPorCliente total = new MapeadorEventosPorCliente(relatorios);

        return total.getMapaAgrupado();

    }

    @Override
    public Map<String, Long> getTempoMedioAtendimentoAtendente() {

        MapeadorAtendimentosPorAtendente mapa = new MapeadorAtendimentosPorAtendente(relatorios);

        return mapa.getMapaAgrupado();
    }

    @Override
    public List<Tipo> getTiposOrdenadosNumerosEventosDecrescente() {

        MapeadorTipoEvento mapa = new MapeadorTipoEvento(relatorios);
        return mapa.getListaTipoOrdenadaDecrescente();

    }

    @Override
    public List<Integer> getCodigoSequencialEventosDesarmeAposAlarme() {

        MapeadorEventosPosAlarme mapa = new MapeadorEventosPosAlarme(relatorios);
        return mapa.getEventosPosAlarme();
    }

    /***
     * Retorna uma lista de tipo ordenada decrescente de acordo com o número de
     * atendimentos
     *
     * @param mapa
     * @return
     */
    private List<Tipo> retornaListaTipoDesc(Map<Tipo, Integer> mapa) {

        List<Tipo> retorno = new ArrayList<>();
        Map<Tipo, Integer> mapaOrdenado = new HashMap<>();
        mapaOrdenado = sortByValueDesc(mapa);

        for (Map.Entry<Tipo, Integer> entry : mapaOrdenado.entrySet()) {
            retorno.add(entry.getKey());
        }
        return retorno;
    }

    /***
     * Carrega as linhas do relatorios
     *
     * @return
     */
    private List<RegistroRelatorio> carregarRelatorio() {

        List<RegistroRelatorio> relatorio = new ArrayList<>();

        BufferedReader bufferedReader = null;
        try {

            bufferedReader = new BufferedReader(new FileReader("src/test/java/br/com/segware/relatorio.csv"));

            String line;

            while ((line = bufferedReader.readLine()) != null) {

                String[] row = line.split(CSV_DELIMITADOR);

                RegistroRelatorio registroRelatorio = new RegistroRelatorio(row);

                relatorio.add(registroRelatorio);
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return relatorio;
    }

    private <K, V extends Comparable<? super V>> Map<K, V> sortByValueDesc(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
