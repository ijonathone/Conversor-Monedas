package drr.aluradesafio.conversormonedas.presentacion;

import drr.aluradesafio.conversormonedas.dominio.Consultas;
import drr.aluradesafio.conversormonedas.dominio.Convertidor;
import drr.aluradesafio.conversormonedas.dominio.Opcion;
import drr.aluradesafio.conversormonedas.servicio.ConvertirMoneda;
import drr.aluradesafio.conversormonedas.servicio.GenerarHistorial;
import drr.aluradesafio.conversormonedas.servicio.IConvertirMoneda;

import javax.sound.midi.SysexMessage;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProcesarSeleccion {
    public void manejarSeleccion(Opcion opcionSeleccionada, String apiKey){
        IConvertirMoneda convertirMoneda = new ConvertirMoneda();
        ejecutarConversion(opcionSeleccionada, convertirMoneda, apiKey);
    }

    private static void ejecutarConversion(Opcion opcion, IConvertirMoneda convertirMoneda, String apiKey) {
        Scanner consola = new Scanner(System.in);

        System.out.print("Ingrese el valor que se va a convertir: ");
        try{
            double montoAConvertir = consola.nextDouble();
            if (montoAConvertir <= 0) {
                System.out.println("El valor a convertir debe ser un número positivo.");
                return;
            }
            Convertidor convertidor = convertirMoneda.convertirMoneda(opcion, montoAConvertir, apiKey);
            GenerarHistorial generarHistorial = new GenerarHistorial();
            generarHistorial.generarHistorial(convertidor, montoAConvertir);
            Consultas consultas = convertirMoneda.numeroConsultas(apiKey);

            System.out.println("Operación seleccionada: " + opcion.getDescripcion());

            imprimirResultado(convertidor, montoAConvertir, consultas);
        } catch (InputMismatchException ex){
            System.out.println("Valor ingresado no es valido, por favor vuelva a intentar.");
        } catch (Exception e){
            System.out.println("Error al convertir moneda: " + e);
        }
    }

    private static void imprimirResultado(Convertidor convertidor, double montoAConvertir, Consultas consultas) throws Exception{
        System.out.println("|--------------------------------|--------------------------------|");
        System.out.printf("| %-30s | %-30s |\n", "Valor ingresado en: [" + convertidor.getMonedaBase() + "]", "Valor final en: [" + convertidor.getMonedaCambio() + "]");
        System.out.println("|--------------------------------|--------------------------------|");
        System.out.printf("| %30s | %30s |\n", montoAConvertir, convertidor.getMonedaTotal());
        System.out.println("|--------------------------------|--------------------------------|");
        System.out.printf("| %-63s |\n", ">>>>>>>>>> " + consultas);
        System.out.println("|-----------------------------------------------------------------|");

        System.out.println("Presiona Enter para continuar...");
        System.in.read();
    }
}
