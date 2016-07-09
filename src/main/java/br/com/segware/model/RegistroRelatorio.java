package br.com.segware.model;

import br.com.segware.Tipo;
import br.com.segware.util.DateUtil;
import org.joda.time.DateTime;

/**
 * Created by tulio on 07/07/16.
 */
public class RegistroRelatorio {

    private Integer codigoSequencial;

    private String codigoCliente;

    private String codigoEvento;

    private Tipo tipoEvento;

    private DateTime dataInicio;

    private DateTime dataFim;

    private String codigoAtendente;

    public RegistroRelatorio() {

    }

    public RegistroRelatorio(String[] row) {

        Integer colIndex = 0;

        this.codigoSequencial = Integer.parseInt(row[colIndex++]);
        this.codigoCliente = row[colIndex++];
        this.codigoEvento = row[colIndex++];
        this.tipoEvento = Tipo.valueOf(row[colIndex++]);
        this.dataInicio = DateUtil.format(row[colIndex++]);
        this.dataFim = DateUtil.format(row[colIndex++]);
        this.codigoAtendente = row[colIndex++];

    }

    public Integer getCodigoSequencial() {
        return codigoSequencial;
    }

    public void setCodigoSequencial(Integer codigoSequencial) {
        this.codigoSequencial = codigoSequencial;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public Tipo getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(Tipo tipoEvento) {
        this.tipoEvento = tipoEvento;
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

    public String getCodigoAtendente() {
        return codigoAtendente;
    }

    public void setCodigoAtendente(String codigoAtendente) {
        this.codigoAtendente = codigoAtendente;
    }
}
