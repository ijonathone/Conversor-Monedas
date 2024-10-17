package drr.aluradesafio.conversormonedas.dominio;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Convertidor {
    private String respuesta;
    private String fechaDeUltimaActualizacion;
    private String fechaDeProximaActualizacion;
    private String monedaBase;
    private String monedaCambio;
    private double monedaValor;
    private double monedaTotal;

    public Convertidor() {
    }

    public Convertidor(RConvertidor convertidorApi) {
        this.respuesta = convertidorApi.result();
        LocalDateTime ultimaActualizacion = LocalDateTime.ofInstant(Instant.ofEpochSecond(convertidorApi.time_last_update_unix()), ZoneId.systemDefault());
        this.fechaDeUltimaActualizacion = ultimaActualizacion.toString();
        LocalDateTime proximaActualizacion = LocalDateTime.ofInstant(Instant.ofEpochSecond(convertidorApi.time_next_update_unix()), ZoneId.systemDefault());
        this.fechaDeProximaActualizacion = proximaActualizacion.toString();
        this.monedaBase = convertidorApi.base_code();
        this.monedaCambio = convertidorApi.target_code();
        this.monedaValor = convertidorApi.conversion_rate();
        this.monedaTotal = convertidorApi.conversion_result();
    }

    public String getFechaDeUltimaActualizacion() {
        return fechaDeUltimaActualizacion;
    }

    public void setFechaDeUltimaActualizacion(String fechaDeUltimaActualizacion) {
        this.fechaDeUltimaActualizacion = fechaDeUltimaActualizacion;
    }

    public String getFechaDeProximaActualizacion() {
        return fechaDeProximaActualizacion;
    }

    public void setFechaDeProximaActualizacion(String fechaDeProximaActualizacion) {
        this.fechaDeProximaActualizacion = fechaDeProximaActualizacion;
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public void setMonedaBase(String monedaBase) {
        this.monedaBase = monedaBase;
    }

    public String getMonedaCambio() {
        return monedaCambio;
    }

    public void setMonedaCambio(String monedaCambio) {
        this.monedaCambio = monedaCambio;
    }

    public double getMonedaValor() {
        return monedaValor;
    }

    public void setMonedaValor(double monedaValor) {
        this.monedaValor = monedaValor;
    }

    public double getMonedaTotal() {
        return monedaTotal;
    }

    public void setMonedaTotal(double monedaTotal) {
        this.monedaTotal = monedaTotal;
    }

    @Override
    public String toString() {
        return  " [" + monedaBase + "] corresponde al valor final de =>>> " + monedaTotal + " [" + monedaCambio + "]";
    }
}
