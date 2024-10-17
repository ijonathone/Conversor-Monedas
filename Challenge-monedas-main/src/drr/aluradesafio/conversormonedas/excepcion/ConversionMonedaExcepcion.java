package drr.aluradesafio.conversormonedas.excepcion;

public class ConversionMonedaExcepcion extends RuntimeException {
    public ConversionMonedaExcepcion(String mensaje) {
        super(mensaje);
    }
}
