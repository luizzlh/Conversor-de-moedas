## 🚀 Conversor de Moedas Java (API ExchangeRate-API)

Este projeto implementa um conversor de moedas simples via console, utilizando a linguagem Java, a biblioteca **Gson** para processamento JSON e a **ExchangeRate-API** para obter as taxas de câmbio em tempo real.

----

## 🛠️ Estrutura do Projeto

O projeto é composto por duas classes principais:

1.  **`Conversor.java`**:

      * **Função:** Modelo de dados (POJO) que espelha a estrutura JSON da resposta da API. É essencial para que o Gson consiga mapear o JSON para um objeto Java.
      * **Ferramentas:** **Gson** (via mapeamento de campos).

2.  **`ConversorDeMoedas.java`**:

      * **Função:** Contém a lógica principal, manipulação da API, conversão de dados e interação com o usuário.
      * **Ferramentas:** **HttpClient** (requisições), **Gson** (parsing JSON), **Scanner** (interação via console).

-----

## ⚙️ Funcionalidades

O programa permite realizar conversões entre Dólar Americano (**USD**) e as seguintes moedas latino-americanas, conforme especificado no desafio:

| Opção | Código | Moeda |
| :---: | :----: | :---: |
| **1** | ARS | Peso Argentino |
| **2** | BOB | Boliviano Boliviano |
| **3** | BRL | Real Brasileiro |
| **4** | CLP | Peso Chileno |
| **5** | COP | Peso Colombiano |

A opção **6** é um exemplo de conversão inversa ou entre moedas não baseadas em USD (ex: BRL para USD), demonstrando a lógica de conversão utilizando a taxa base.

-----

## ▶️ Como Executar

1.  Certifique-se de que a API Key foi configurada corretamente.
2.  Compile e execute a classe principal **`ConversorDeMoedas.java`**.
3.  O programa exibirá um menu no console. Digite o número da opção desejada e siga as instruções para inserir o valor a ser convertido.

### Exemplo de Uso (Console)

```
✨ Bem-vindo ao Conversor de Moedas! ✨

--- Menu de Conversões (Base USD) ---
1. USD para ARS (Peso Argentino)
2. USD para BOB (Boliviano Boliviano)
3. USD para BRL (Real Brasileiro)
4. USD para CLP (Peso Chileno)
5. USD para COP (Peso Colombiano)
6. Outra Conversão (Ex: BRL para USD)
7. Sair
------------------------------------
➡️ Digite sua opção (1-7): 3
💰 Digite o valor em USD para converter: 100
✅ O valor de **100.00 USD** é equivalente a **515.50 BRL** (Taxa: 5.1550)

➡️ Digite sua opção (1-7): 7
Obrigado por usar o conversor! Até mais. 👋
```
