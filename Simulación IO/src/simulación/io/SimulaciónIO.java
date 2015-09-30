/*
 * Clase central encargada de dirigir la comunicacion entre la interfaz 
   implementada, y la clase ControladorEventos. Redirige datos de entrada y 
   salida entre ambos. 
 */
package simulación.io;

/**
 *
 * @author Ulises
 */
public class SimulaciónIO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        new VentadaPrincipal().setVisible(true);
    }
}
