package drr.aluradesafio.conversormonedas.presentacion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import drr.aluradesafio.conversormonedas.dominio.Opcion;
import drr.aluradesafio.conversormonedas.servicio.GenerarHistorial;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    public boolean construirMenu(String apiKey){
        Scanner consola = new Scanner(System.in);
        boolean salir = false;
        try{
            // Llamamos al metodo que cargara las opciones desde un archivo JSON.
            List<Opcion> opciones = cargarOpciones();
            // Mostramos el mennu.
            mostrarMenu(opciones);

            System.out.print("Escriba una opción válida o 10 para salir: ");
            int opcionElegida = consola.nextInt();

            Opcion opcionSeleccionada = opciones.stream()
                    .filter(op -> op.getId() == opcionElegida)
                    .findFirst()
                    .orElse(null);

            if (opcionSeleccionada != null) {
                if (opcionSeleccionada.getId() == 10) {
                    System.out.println("¡Hasta pronto!");
                    salir = true;
                } else if (opcionSeleccionada.getId() == 9) {
                    GenerarHistorial generarHistorial = new GenerarHistorial();
                    generarHistorial.mostrarHistorial();
                } else {
                    ProcesarSeleccion seleccion = new ProcesarSeleccion();
                    seleccion.manejarSeleccion(opcionSeleccionada, apiKey);
                }
            } else {
                System.out.println("Opción inválida");
            }
        } catch (InputMismatchException ex){
            System.out.println("Opción inválida");
        } catch (Exception e) {
            System.out.println("Error al construir menu: " + e);
        }
        return salir;
    }

    private static List<Opcion> cargarOpciones() throws Exception {
        // Las opciones del menú serán cargadas desde un JSON.
        ClassLoader classLoader = Principal.class.getClassLoader();
        InputStreamReader lectura = new InputStreamReader(Objects.requireNonNull(classLoader.getResourceAsStream("drr/aluradesafio/conversormonedas/recursos/opciones.json")));

        Gson gson = new Gson();
        Type menuLista = new TypeToken<List<Opcion>>() {}.getType();
        List<Opcion> opciones = gson.fromJson(lectura, menuLista);
        lectura.close();
        return opciones;
    }

    private static void mostrarMenu(List<Opcion> opciones){
        System.out.println("**************************************************************************");
        System.out.println("*** Bienvenido(a) al Conversor de Monedas ***");
        //Creación del menú
        for (Opcion opcion : opciones) {
            System.out.println(opcion);
        }
        System.out.println("**************************************************************************");
        System.out.println();
    }
}
