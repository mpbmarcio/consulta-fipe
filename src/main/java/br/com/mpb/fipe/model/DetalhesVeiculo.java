package br.com.mpb.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetalhesVeiculo {
    @JsonAlias("Valor") private String valor;
    @JsonAlias("Marca") private String marca;
    @JsonAlias("Modelo") private String modelo;
    @JsonAlias("AnoModelo") private int anoModelo;
    @JsonAlias("Combustivel") private String combustivel;
    @JsonAlias("CodigoFipe") private String codigoFipe;
    @JsonAlias("MesReferencia") private String mesReferencia;
    @JsonAlias("SiglaCombustivel") private String siglaCombustivel;

    public String getValor() {
        return valor;
    }
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public int getAnoModelo() {
        return anoModelo;
    }
    public String getCombustivel() {
        return combustivel;
    }
    public String getCodigoFipe() {
        return codigoFipe;
    }
    public String getMesReferencia() {
        return mesReferencia;
    }
    public String getSiglaCombustivel() {
        return siglaCombustivel;
    }

    @Override
    public String toString() {
        return "{ "
                + "Valor: " + valor + ", "
                + "Marca: " + marca + ", "
                + "Modelo: " + modelo + ", "
                + "AnoModelo: " + anoModelo + ", "
                + "Combus: " + combustivel + ", "
                + "CodFipe: " + codigoFipe + ", "
                + "MesRef: " + mesReferencia + ", "
                + "Combus: " + siglaCombustivel + " "
                + "}";
    }


}