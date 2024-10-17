package drr.aluradesafio.conversormonedas.dominio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Historial extends Convertidor{
    private final String fechaConsulta;
    private final double montoCambio;

    public Historial(Convertidor convertidor, String fechaConsulta, double montoCambio) {
        this.fechaConsulta = fechaConsulta;
        this.setFechaDeUltimaActualizacion(convertidor.getFechaDeUltimaActualizacion());
        this.setFechaDeProximaActualizacion(convertidor.getFechaDeProximaActualizacion());
        this.setMonedaBase(convertidor.getMonedaBase());
        this.setMonedaCambio(convertidor.getMonedaCambio());
        this.setMonedaValor(convertidor.getMonedaValor());
        this.montoCambio = montoCambio;
        this.setMonedaTotal(convertidor.getMonedaTotal());
    }

    public String getFechaConsulta() {
        return fechaConsulta;
    }

    public double getMontoCambio() {
        return montoCambio;
    }

    public LocalDateTime getFechaLocalDateTime(){
        return LocalDateTime.parse(fechaConsulta);
    }

    public String getFechaStringFormato(){
        LocalDateTime fecha = LocalDateTime.parse(fechaConsulta);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return fecha.format(formato);
    }

    public String getMontoCambioFormato(){
        return String.format("%,.2f", this.montoCambio);
    }

    public String getMonedaTotalFormato(){
        return String.format("%,.4f", this.getMonedaTotal());
    }
}
