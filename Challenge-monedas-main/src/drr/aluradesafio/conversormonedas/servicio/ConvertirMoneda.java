package drr.aluradesafio.conversormonedas.servicio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import drr.aluradesafio.conversormonedas.dominio.*;
import drr.aluradesafio.conversormonedas.excepcion.ConversionMonedaExcepcion;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;

public class ConvertirMoneda implements IConvertirMoneda {

    @Override
    public Convertidor convertirMoneda(Opcion opcion, double montoCambio, String apiKey) {
        Convertidor respuesta = null;

        try {
            URI direccion = URI.create(
                    "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/"
                            + opcion.getBase() + "/" + opcion.getCambio() + "/" + montoCambio
            );

            // Crear HttpClient con timeout de conexión
            HttpClient httpClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(30))  // Timeout de conexión
                    .build();

            // Crear la solicitud con timeout de lectura
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccion)
                    .timeout(Duration.ofSeconds(30))  // Timeout de respuesta
                    .GET()
                    .build();

            // Enviar la solicitud
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            if (httpResponse.statusCode() == 200) {
                RConvertidor miRespuestaR = gson.fromJson(httpResponse.body(), RConvertidor.class);
                respuesta = new Convertidor(miRespuestaR);
            } else {
                throw new ConversionMonedaExcepcion(
                        "Error al realizar la consulta, código de respuesta: " + httpResponse.statusCode()
                );
            }
        } catch (HttpTimeoutException e) {
            throw new ConversionMonedaExcepcion("Timeout al convertir la moneda: " + e.getMessage());
        } catch (Exception ex) {
            throw new ConversionMonedaExcepcion("Error al convertir la moneda: " + ex.getMessage());
        }
        return respuesta;
    }

    @Override
    public Consultas numeroConsultas(String apiKey) {
        Consultas respuesta = null;

        try {
            URI direccionConsultas = URI.create(
                    "https://v6.exchangerate-api.com/v6/" + apiKey + "/quota/"
            );

            // Crear HttpClient con timeout de conexión
            HttpClient httpClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(30))  // Timeout de conexión
                    .build();

            // Crear la solicitud con timeout de lectura
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccionConsultas)
                    .timeout(Duration.ofSeconds(30))  // Timeout de respuesta
                    .GET()
                    .build();

            // Enviar la solicitud
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            if (httpResponse.statusCode() == 200) {
                RConsultas miConsultas = gson.fromJson(httpResponse.body(), RConsultas.class);
                respuesta = new Consultas(miConsultas);
            } else {
                throw new ConversionMonedaExcepcion(
                        "Error al realizar la consulta, código de respuesta: " + httpResponse.statusCode()
                );
            }
        } catch (HttpTimeoutException e) {
            throw new ConversionMonedaExcepcion("Timeout al traer el número de consultas: " + e.getMessage());
        } catch (Exception ex) {
            throw new ConversionMonedaExcepcion("Error al traer el número de consultas: " + ex.getMessage());
        }

        return respuesta;
    }
}