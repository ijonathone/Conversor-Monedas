package drr.aluradesafio.conversormonedas.dominio;

public class Opcion {
    private int id;
    private String descripcion;
    private String base;
    private String cambio;

    public Opcion(int id, String descripcion, String base, String cambio) {
        this.id = id;
        this.descripcion = descripcion;
        this.base = base;
        this.cambio = cambio;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getBase() {
        return base;
    }

    public String getCambio() {
        return cambio;
    }

    @Override
    public String toString() {
        return id + ") " + descripcion;
    }
}
