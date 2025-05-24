package br.com.mpb.fipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelosWrapper {
    private List<DadosModelo> modelos;

    public List<DadosModelo> getModelos() {
        return modelos;
    }

    public void setModelos(List<DadosModelo> modelos) {
        this.modelos = modelos;
    }

    @Override
    public String toString() {
        return "ModelosWrapper{" + "modelos=" + modelos + '}';
    }
}
