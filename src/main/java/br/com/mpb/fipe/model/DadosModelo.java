package br.com.mpb.fipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosModelo {
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
        return "DadosModelo{" +
                "codigo='" + codigo + '\'' + "\n" +
                ", nome='" + nome + '\'' +
                '}';
    }
}