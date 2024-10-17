package drr.aluradesafio.conversormonedas.servicio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import drr.aluradesafio.conversormonedas.dominio.Convertidor;
import drr.aluradesafio.conversormonedas.dominio.Historial;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerarHistorial implements IGenerarHistorial {
    private static final String HISTORIAL_FILE = "C:/Users/Jonathan Alvarado/Documents/Primeros programas con java/Pruebas/Challenge-monedas-main/recursos/historial_convertidor.json";
    @Override
    public void generarHistorial(Convertidor convertidor, double montoCambio) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try
        {
            List<Historial> historiales = historialConsultas();
            Historial nuevaConversion = new Historial(convertidor, LocalDateTime.now().toString(), montoCambio);
            historiales.add(nuevaConversion);

            FileWriter historialEscritura = new FileWriter(HISTORIAL_FILE);
            historialEscritura.write(gson.toJson(historiales));
            historialEscritura.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarHistorial() {
        try {
            List<Historial> historiales = historialConsultas();

            Collections.sort(historiales, (h1, h2) ->
                    h2.getFechaLocalDateTime().compareTo(h1.getFechaLocalDateTime())
                    );

            int registrosMostrar = Math.min(historiales.size(), 30);
            List<Historial> ultimosRegsitros = historiales.subList(historiales.size() - registrosMostrar, historiales.size());

            System.out.println("|---------------------------|---------------------------|---------------------------|");
            System.out.printf("| %-25s | %-25s | %-25s |\n", "Fecha", "De", "A");
            System.out.println("|---------------------------|---------------------------|---------------------------|");
            for (Historial historial : ultimosRegsitros) {
                System.out.printf("| %-25s | %25s | %25s |\n", historial.getFechaStringFormato(), historial.getMontoCambioFormato() + " [" + historial.getMonedaBase() + "]", historial.getMonedaTotalFormato() + " [" + historial.getMonedaCambio() + "]");
            }
            System.out.println("|---------------------------|---------------------------|---------------------------|");

            System.out.println("Presiona Enter para continuar...");
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Historial> historialConsultas() throws IOException{
        Gson gson = new Gson();
        List<Historial> historiales = new ArrayList<>();

        try(FileReader fileReader = new FileReader(HISTORIAL_FILE)) {
            Type ListType = new TypeToken<ArrayList<Historial>>() {}.getType();
            historiales = gson.fromJson(fileReader, ListType);

            if (historiales == null) {
                historiales = new ArrayList<>();
            }
        } catch (IOException ex) {
            historiales = new ArrayList<>();
        }
        return historiales;
    };
}
