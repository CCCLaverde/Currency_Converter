package laverde;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner lectura = new Scanner(System.in);
        System.out.println("*******************************************");
        System.out.println("    BIENVENIDO AL CONVERSOR DE MONEDAS  \n");
        // Obtener la moneda de origen
        String monedaOrigen = obtenerMonedaOrigen(lectura);
        // Obtener la cantidad a convertir
        System.out.print("Ingrese la cantidad de " + monedaOrigen + " a convertir: ");
        double cantidad = lectura.nextDouble();
        // Obtener las tasas de conversión
        Currency currency = Connection.obtenerTasasConversion();
        if (currency != null) {
            // Obtener la tasa de conversión para convertir a dólares
            Double conversion = currency.getConversionRates().get(monedaOrigen);
            if (conversion != null) {
                // Realizar la conversión a dólares
                double resultado = cantidad / conversion;
                System.out.println(cantidad + " " + monedaOrigen + " equivale a "
                        + resultado + " DÓLARES ESTADOUNIDENSES");
            } else {
                System.out.println("No se encontró la tasa de conversión para la moneda de origen seleccionada");
            }
        } else {
            System.out.println("No se pudo obtener las tasas de conversión.");
        }
        System.out.println("********************************************");
        System.out.println("GRACIAS POR UTILIZAR LA APLICACIÓN");
        System.out.println("********************************************");
    }
    private static String obtenerMonedaOrigen(Scanner lectura) {
        System.out.println("Elija el tipo de moneda que desea convertir:");
        Map<String, String> opciones = obtenerOpciones();
        imprimirOpciones(opciones);
        String monedaOrigen = lectura.next().toUpperCase();
        while (!opciones.containsKey(monedaOrigen)) {
            System.out.println("Moneda no válida. Por favor, elija una moneda de la lista.");
            imprimirOpciones(opciones);
            monedaOrigen = lectura.next().toUpperCase();
        }
        return monedaOrigen;
    }
    private static Map<String, String> obtenerOpciones() {
        Map<String, String> opciones = new HashMap<>();
        opciones.put("ARG", "PESO ARGENTINO");
        opciones.put("BRL", "REAL BRASILEÑO");
        opciones.put("COP", "PESO COLOMBIANO");
        opciones.put("EUR", "EURO");
        opciones.put("MXN", "PESO MEXICANO");
        return opciones;
    }
    private static void imprimirOpciones(Map<String, String> opciones) {
        for (Map.Entry<String, String> entry : opciones.entrySet()) {
            System.out.println(entry.getKey() + ") " + entry.getValue());
        }
    }
}
