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
    String tipoVeiculo = "";
    int modelo;
    int veiculo;

    public void exibeMenu() {
        int opcao;

        do {
            desenharJanela();
            seta();
            opcao = leitura.nextInt();

            if (opcao == 4) {
                System.out.println("Obrigado por usar nossos sistemas!");
                System.out.println("Apoio: Alura One");
                break;
            }

            String[] veiculos = {"carros", "caminhoes", "motos"};
            tipoVeiculo = (opcao >= 1 && opcao <= 3) ? veiculos[opcao - 1] : "";

            json = consumoApi.obterDados(ENDERECO + "/" + tipoVeiculo + "/marcas");
            List<DadosMarca> dadosMarca = converteDados.obterDadosArray(json, DadosMarca.class);
            exibeMarcas(dadosMarca);

            seta();
            modelo = leitura.nextInt();
            json = consumoApi.obterDados(ENDERECO + "/" + tipoVeiculo + "/marcas/" + modelo + "/modelos");
            ModelosWrapper wrapper = converteDados.obterDados(json, ModelosWrapper.class);
            List<DadosModelo> dadosModeloList = wrapper.getModelos();
            exibeModelos(dadosModeloList);

            seta();
            veiculo = leitura.nextInt();
            json = consumoApi.obterDados(ENDERECO + "/" + tipoVeiculo + "/marcas/" + modelo + "/modelos/" + veiculo + "/anos");

            List<AnosVeiculo> anosVeiculos = converteDados.obterDadosArray(json, AnosVeiculo.class);
            exibeVeiculos(anosVeiculos);

            System.out.println("\nDeseja realizar outra operação?");

        } while (opcao != 4);
    }

    public static void desenharJanela() {
        int largura = 50;

        String[] mensagens = {
                "Escolha a opção:",
                "1 - Carro   ",
                "2 - Caminhão",
                "3 - Moto    ",
                "4 - Sair    "
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
        int maxWidth = 20; // Limite de caracteres para cada campo

        // Ajuste dinâmico baseado no maior campo, com cortes automáticos
        String formatoLinha = "| %-"+maxWidth+"s | %-"+maxWidth+"s | %-"+maxWidth+"s | %-"+maxWidth+"s | %-"+maxWidth+"s | %-"+maxWidth+"s | %-"+maxWidth+"s |\n";

        System.out.println("+-" + "-".repeat(maxWidth) + "-+-" + "-".repeat(maxWidth) + "-+-" +
                "-".repeat(maxWidth) + "-+-" + "-".repeat(maxWidth) + "-+-" +
                "-".repeat(maxWidth) + "-+-" + "-".repeat(maxWidth) + "-+-" +
                "-".repeat(maxWidth) + "-+");

        System.out.printf(formatoLinha, "Valor", "Marca", "Modelo", "AnoModelo", "Combustível", "CodFipe", "MesRef");

        System.out.println("+-" + "-".repeat(maxWidth) + "-+-" + "-".repeat(maxWidth) + "-+-" +
                "-".repeat(maxWidth) + "-+-" + "-".repeat(maxWidth) + "-+-" +
                "-".repeat(maxWidth) + "-+-" + "-".repeat(maxWidth) + "-+-" +
                "-".repeat(maxWidth) + "-+");

        for (AnosVeiculo anos : lista) {
            DetalhesVeiculo detalhes = converteDados.obterDados(
                    consumoApi.obterDados(ENDERECO + "/" + tipoVeiculo + "/marcas/" + modelo +
                            "/modelos/" + veiculo + "/anos/" + anos.getCodigo()),
                    DetalhesVeiculo.class
            );

            System.out.printf(formatoLinha,
                    detalhes.getValor(),
                    detalhes.getMarca().length() > maxWidth ? detalhes.getMarca().substring(0, maxWidth - 3) + "..." : detalhes.getMarca(),
                    detalhes.getModelo().length() > maxWidth ? detalhes.getModelo().substring(0, maxWidth - 3) + "..." : detalhes.getModelo(),
                    detalhes.getAnoModelo(),
                    detalhes.getCombustivel(),
                    detalhes.getCodigoFipe(),
                    detalhes.getMesReferencia()
            );
        }

        System.out.println("+-" + "-".repeat(maxWidth) + "-+-" + "-".repeat(maxWidth) + "-+-" +
                "-".repeat(maxWidth) + "-+-" + "-".repeat(maxWidth) + "-+-" +
                "-".repeat(maxWidth) + "-+-" + "-".repeat(maxWidth) + "-+-" +
                "-".repeat(maxWidth) + "-+");
    }

    public void seta() {
        String RED = "\u001B[31m";
        String RESET = "\u001B[0m";
        System.out.print(RED + "Digite o código ---> " + RESET);
    }
}
