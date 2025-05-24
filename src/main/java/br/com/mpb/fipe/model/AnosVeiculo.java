package br.com.mpb.fipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnosVeiculo {
    private String codigo;
    private String nome;

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "{ \"codigo\": \"" + codigo + "\", \"nome\": \"" + nome + "\" }";
    }
}