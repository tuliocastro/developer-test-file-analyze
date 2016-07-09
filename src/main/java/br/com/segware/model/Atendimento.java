package br.com.segware.model;

import br.com.segware.model.RegistroRelatorio;
import org.joda.time.DateTime;
import org.joda.time.Seconds;

/**
 * Created by tulio on 09/07/16.
 */
public class Atendimento {

    private String atendente;

    private DateTime dataInicio;

    private DateTime dataFim;

    private Integer tempoAtendimentoSegundos;


    public Atendimento(RegistroRelatorio registro) {

        this.atendente = registro.getCodigoAtendente();
        this.dataInicio = registro.getDataInicio();
        this.dataFim = registro.getDataFim();
        this.tempoAtendimentoSegundos = Seconds.secondsBetween(this.dataInicio, this.dataFim).getSeconds();

    }

    public String getAtendente() {
        return atendente;
    }

    public void setAtendente(String atendente) {
        this.atendente = atendente;
    }

    public DateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(DateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public DateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(DateTime dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getTempoAtendimentoSegundos() {
        return tempoAtendimentoSegundos;
    }

    public void setTempoAtendimentoSegundos(Integer tempoAtendimentoSegundos) {
        this.tempoAtendimentoSegundos = tempoAtendimentoSegundos;
    }
}
