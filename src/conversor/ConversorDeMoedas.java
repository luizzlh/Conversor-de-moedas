package conversor;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ConversorDeMoedas {

    private static final String API_KEY = "ca2b5cb1fc000eb9847705db";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";
    private static final String[] MOEDAS_DESAFIO = {"ARS", "BOB", "BRL", "CLP", "COP"};
    private static final Gson GSON = new Gson();

    public static void main(String[] args) {
        Map<String, Double> taxasDeCambio = obterTaxasDeCambio();

        if (taxasDeCambio == null || taxasDeCambio.isEmpty()) {
            System.out.println("Não foi possível obter as taxas de câmbio. Verifique a chave da API e a conexão.");
            return;
        }

        iniciarConversor(taxasDeCambio);
    }

    private static Map<String, Double> obterTaxasDeCambio() {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .build();

        try {

            HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

            if (resposta.statusCode() != 200) {
                System.err.println("Erro na requisição! Status Code: " + resposta.statusCode());
                System.err.println("Corpo da Resposta: " + resposta.body());
                return null;
            }

            String jsonResponse = resposta.body();
            Conversor rateResponse = GSON.fromJson(jsonResponse, Conversor.class);

            if (!"success".equalsIgnoreCase(rateResponse.getResult())) {
                System.err.println("Erro retornado pela API: " + jsonResponse);
                return null;
            }

            return rateResponse.getConversionRates();

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao realizar a requisição HTTP: " + e.getMessage());
            return null;
        }
    }

    private static void iniciarConversor(Map<String, Double> taxas) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        System.out.println("Bem-vindo ao Conversor de Moedas!");

        while (opcao != 7) {
            exibirMenu();

            try {
                System.out.print("Digite sua opção (1-7): ");
                opcao = scanner.nextInt();

                if (opcao >= 1 && opcao <= 5) {
                    realizarConversao("USD", MOEDAS_DESAFIO[opcao - 1], taxas, scanner);
                } else if (opcao == 6) {
                    realizarConversao("BRL", "USD", taxas, scanner);
                } else if (opcao == 7) {
                    System.out.println("Obrigado por usar o conversor! Até mais.");
                } else {
                    System.out.println("Opção inválida. Por favor, escolha um número de 1 a 7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
                scanner.next();
                opcao = -1;
            }
        }
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--- Menu de Conversões (Base USD) ---");
        System.out.println("1. USD para ARS (Peso Argentino)");
        System.out.println("2. USD para BOB (Boliviano Boliviano)");
        System.out.println("3. USD para BRL (Real Brasileiro)");
        System.out.println("4. USD para CLP (Peso Chileno)");
        System.out.println("5. USD para COP (Peso Colombiano)");
        System.out.println("6. Outra Conversão (Ex: BRL para USD)");
        System.out.println("7. Sair");
        System.out.println("------------------------------------");
    }

    private static void realizarConversao(String deMoeda, String paraMoeda, Map<String, Double> taxas, Scanner scanner) {
        Double taxaPara = taxas.get(paraMoeda);
        Double taxaDe = taxas.get(deMoeda);

        if (taxaPara == null || taxaDe == null) {
            System.out.println("⚠Taxa de câmbio não encontrada para " + paraMoeda + " ou " + deMoeda + ".");
            return;
        }

        try {
            System.out.print("Digite o valor em " + deMoeda + " para converter: ");
            double valorEntrada = scanner.nextDouble();

            double taxaConversao;

            if (deMoeda.equals("USD")) {
                taxaConversao = taxaPara;
            } else {
                taxaConversao = taxaPara / taxaDe;
            }

            double valorConvertido = valorEntrada * taxaConversao;

            System.out.printf("O valor de **%.2f %s** é equivalente a **%.2f %s** (Taxa: %.4f)\n",
                    valorEntrada, deMoeda, valorConvertido, paraMoeda, taxaConversao);
        } catch (InputMismatchException e) {
            System.out.println("Entrada de valor inválida. Tente novamente.");
            scanner.next();
        }
    }
}