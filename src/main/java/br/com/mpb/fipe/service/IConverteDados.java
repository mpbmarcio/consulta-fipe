package br.com.mpb.fipe.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
