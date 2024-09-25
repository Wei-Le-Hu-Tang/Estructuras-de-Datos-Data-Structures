package mx.unam.ciencias.edd.proyecto1;	
import mx.unam.ciencias.edd.Lista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Los métodos en la clase TextoALista nos permitirán convertir un archivo de texto
 * en una lista de objetos de la clase {@link Linea}.
 */

public class TextoALista{

    /**
     * Convierte un archivo de texto en una Lista de Lineas, cuando el programa es
     * corrido de la forma:
     * $ java -jar target/proyecto1.jar <ruta del archivo>
     * @param ruta ruta del archivo de texto.
     * @return la Lista de Lineas del texto.
     * 
     * Si @param ruta es inválido, imprime la excepción correspondiente y
     * @return null.
     */
    public static Lista<Linea> ruta(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            
            String linea = br.readLine();
            Lista<Linea> textote = new Lista<Linea>();

            while (linea != null) {
                textote.agrega(new Linea(linea));
                linea = br.readLine();
            }
            return textote;

        } catch (IOException e) {
            System.err.println("Error de ruta: " + e.getMessage());
            return null;
        }
    }

    /**
     * Convierte un archivo de texto en una Lista de Lineas, cuando el programa es
     * corrido con la entrada estándar:
     * $ cat <ruta del archivo> | java -jar target/proyecto1.jar
     * @return la Lista de Lineas del texto.
     * 
     * Si la ruta del archivo es inválido, imprime la excepción correspondiente y
     * @return null.
     */
    public static Lista<Linea> estandar() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String linea = br.readLine();
            Lista<Linea> textote = new Lista<Linea>();

            while (linea != null) {
                textote.agrega(new Linea(linea));
                linea = br.readLine();
            }
            return textote;

        } catch (IOException e) {
            System.err.println("Error de entrada: " + e.getMessage());
            return null;
        }
    }
}
