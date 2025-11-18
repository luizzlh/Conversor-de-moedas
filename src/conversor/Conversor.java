package conversor;

import java.util.Map;

public class Conversor {
    // Corresponde ao campo "result" do JSON
    private String result;

    // Corresponde ao campo "base_code" do JSON (ex: "USD")
    private String base_code;

    // Corresponde ao campo "conversion_rates" do JSON
    // Onde a chave é o código da moeda (ex: "BRL") e o valor é a taxa (ex: 5.0)
    private Map<String, Double> conversion_rates;

    // Getters
    public String getResult() {
        return result;
    }

    public String getBaseCode() {
        return base_code;
    }

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }
}