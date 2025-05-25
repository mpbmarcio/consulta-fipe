# 🚗 Sistema de Consulta de Veículos FIPE

Este projeto é um sistema de consulta de veículos que permite obter informações sobre **marcas, modelos e anos de veículos** com base na **API FIPE**. O usuário pode interagir via terminal para consultar diferentes categorias de veículos e visualizar detalhes dos preços de mercado.

## 📌 Funcionalidades

- **Consulta de veículos**: Permite pesquisar carros, caminhões e motos.
- **Listagem dinâmica**: O sistema exibe todas as marcas e modelos disponíveis.
- **Exibição detalhada**: Mostra valores e referência FIPE para cada veículo.
- **Loop contínuo**: O usuário pode realizar múltiplas buscas sem reiniciar o programa.
- **Validação de entrada**: Proteção contra entradas inválidas, garantindo melhor interação do usuário.
- **Interface textual otimizada**: Uso de tabelas e formatação para melhor leitura.

## 🛠️ Tecnologias Utilizadas

- **Java 11+** – Linguagem de programação usada no sistema.
- **IntelliJ IDEA** – Ambiente de desenvolvimento recomendado.
- **API FIPE** – Fonte dos dados consultados sobre veículos.
- **Jackson (Manipulação de JSON)** – Biblioteca utilizada para converter objetos Java em JSON e vice-versa, facilitando a integração com APIs e processamento de dados.
- **Scanner** – Entrada de dados interativa pelo terminal.
- **Maven** – Gerenciador de dependências para facilitar o desenvolvimento.
- **Listas (ArrayList)** – Estrutura de dados utilizada para armazenar e manipular os resultados da API.
- **Consumo de API** – Comunicação com serviços web para buscar informações sobre marcas e modelos de veículos.
- **Expressões Lambda e Streams** – Aplicadas para validar e filtrar valores corretamente.

---

## 🚀 Como Executar

```sh
git clone https://github.com/mpbmarcio/consulta-fipe.git

cd src/test/java/br/com/mpb/fipe
javac FipeApplication.java
java FipeApplication
```

## 🔗 API utilizada
Os dados são obtidos da API FIPE disponível em:
[parallelum.com.br/fipe/api/v1](https://parallelum.com.br/fipe/api/v1)

## 📜 Créditos
Este projeto foi desenvolvido com apoio do programa **Alura One**.
