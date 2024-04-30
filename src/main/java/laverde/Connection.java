package laverde;
import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Connection {

    public static Currency obtenerTasasConversion() {
        try {
            Dotenv dotenv = Dotenv.configure().load();
            String apiKey = dotenv.get("API_KEY");

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD"))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            return new Gson().fromJson(json, Currency.class);
        } catch (Exception e) {
            System.out.println("Error al obtener las tasas de conversión: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Currency currency = obtenerTasasConversion();
        if (currency != null) {
            System.out.println("Tasas de conversión disponibles:");
            System.out.println(currency);
        }
    }
}