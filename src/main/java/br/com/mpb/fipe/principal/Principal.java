package br.com.mpb.fipe.principal;

import br.com.mpb.fipe.model.*;
import br.com.mpb.fipe.service.ConsumoApi;
import br.com.mpb.fipe.service.ConverteDados;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();
    private Scanner leitura = new Scanner(System.in);
    String json = "";
    int modelo;
    int veiculo;

    public void exibeMenu() {
        desenharJanela();
        int opcao = leitura.nextInt();

        switch (opcao) {
            case 1: {
                json = consumoApi.obterDados(ENDERECO + "/carros/marcas");
                List<DadosMarca> dadosMarca = converteDados.obterDadosArray(json, DadosMarca.class);
                exibeMarcas(dadosMarca);

                modelo = leitura.nextInt();
                json = consumoApi.obterDados(ENDERECO + "/carros/marcas/" + modelo + "/modelos");
                ModelosWrapper wrapper = converteDados.obterDados(json, ModelosWrapper.class);
                List<DadosModelo> dadosModeloList = wrapper.getModelos();
                exibeModelos(dadosModeloList);

                veiculo = leitura.nextInt();
                json = consumoApi.obterDados(ENDERECO + "/carros/marcas/" + modelo + "/modelos/" + veiculo + "/anos");
                List<AnosVeiculo> anosVeiculos = converteDados.obterDadosArray(json, AnosVeiculo.class);
                exibeVeiculos(anosVeiculos);
            }
        }

    }

    public static void desenharJanela() {
        int largura = 50; // Largura ajustada para acomodar os desenhos

        String[] mensagens = {
                "Escolha a opção:",
                "1 - Carro   ",
                "2 - Caminhão",
                "3 - Moto    "
        };

        System.out.println("+--------------------------------------------------+");
        System.out.println("|             Consulta Tabela Fipe                 |");
        System.out.println("+--------------------------------------------------+");

        for (String mensagem : mensagens) {
            int espacoLateral = (largura - mensagem.length()) / 2;
            System.out.printf("| %-" + (largura - 2) + "s |\n", " ".repeat(espacoLateral) + mensagem);
        }

        System.out.println("+--------------------------------------------------+");

        System.out.println("|      ______                                      |");
        System.out.println("|     /|_||_\\`.                   __o              |");
        System.out.println("|    (   _    _ _\\               |--|              |");
        System.out.println("|    =`-(_)--(_)-'               =(_)--(_)=        |");

        System.out.println("+--------------------------------------------------+");
    }

    public static void exibeMarcas(List<DadosMarca> lista) {
        System.out.println("+----------------+------------------+");
        System.out.println("| Código         | Nome             |");
        System.out.println("+----------------+------------------+");

        for (DadosMarca marca : lista) {
            System.out.printf("| %-14s | %-16s |\n", marca.getCodigo(), marca.getNome());
        }

        System.out.println("+----------------+------------------+");
    }

    public void exibeModelos(List<DadosModelo> lista) {
        System.out.println("+--------+----------------------------------------------------+");
        System.out.println("| Código | Nome                                               |");
        System.out.println("+--------+----------------------------------------------------+");

        for (DadosModelo modelo: lista) {
            System.out.printf("| %-6s | %-50s |\n", modelo.getCodigo(), modelo.getNome());
        }

        System.out.println("+--------+----------------------------------------------------+");
    }

    public void exibeVeiculos(List<AnosVeiculo> lista) {
        System.out.println("+--------------+--------------------+--------------------+------------+--------------+------------+---------------+");
        System.out.println("| Valor        | Marca              | Modelo             | AnoModelo  | Combustível  | CodFipe    | MesRef        |");
        System.out.println("+--------------+--------------------+--------------------+------------+--------------+------------+---------------+");

        for (AnosVeiculo anos : lista) {
            json = consumoApi.obterDados(ENDERECO + "/carros/marcas/" + modelo +
                    "/modelos/" + veiculo + "/anos/" + anos.getCodigo());

            DetalhesVeiculo detalhesVeiculos = converteDados.obterDados(json, DetalhesVeiculo.class);

            System.out.printf("| %-12s | %-18s | %-18s | %-10d | %-12s | %-10s | %-13s |\n",
                    detalhesVeiculos.getValor(),
                    detalhesVeiculos.getMarca(),
                    detalhesVeiculos.getModelo(),
                    detalhesVeiculos.getAnoModelo(),
                    detalhesVeiculos.getCombustivel(),
                    detalhesVeiculos.getCodigoFipe(),
                    detalhesVeiculos.getMesReferencia());
        }

        System.out.println("+--------------+--------------------+--------------------+------------+--------------+------------+---------------+");
    }
}
