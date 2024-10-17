package drr.aluradesafio.conversormonedas.servicio;

import drr.aluradesafio.conversormonedas.dominio.Consultas;
import drr.aluradesafio.conversormonedas.dominio.Convertidor;
import drr.aluradesafio.conversormonedas.dominio.Opcion;

public interface IConvertirMoneda {
    Convertidor convertirMoneda(Opcion opcion, double montoCambio, String apiKey);

    Consultas numeroConsultas(String apiKey);
}
