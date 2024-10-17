package drr.aluradesafio.conversormonedas.dominio;

public class Consultas {
    private final int consultasDisponibles;
    private final int consultasRealizadas;

    public Consultas(RConsultas consultasR) {
        this.consultasDisponibles = consultasR.requests_remaining();
        this.consultasRealizadas = consultasR.refresh_day_of_month();
    }

    public int getConsultasDisponibles() {
        return consultasDisponibles;
    }

    public int getConsultasRealizadas() {
        return consultasRealizadas;
    }

    @Override
    public String toString() {
        return "Consultas disponibles: " + consultasDisponibles;
    }
}
