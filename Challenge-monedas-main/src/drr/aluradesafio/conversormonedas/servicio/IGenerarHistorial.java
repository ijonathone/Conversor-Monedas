package drr.aluradesafio.conversormonedas.servicio;

import drr.aluradesafio.conversormonedas.dominio.Convertidor;

public interface IGenerarHistorial {
    public void generarHistorial(Convertidor convertidor, double montoCambio);
}
