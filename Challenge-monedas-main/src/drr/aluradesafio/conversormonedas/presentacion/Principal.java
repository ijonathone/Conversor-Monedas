package drr.aluradesafio.conversormonedas.presentacion;

public class Principal {
    public static void main(String[] args) {
        boolean salir = false;
        String apiKey = "9b0c94b9372178d25086d0b0";  // Asignación directa de la API Key
        Menu menu = new Menu();

        while (!salir) {
            try {
                salir = menu.construirMenu(apiKey);
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }
    }


}