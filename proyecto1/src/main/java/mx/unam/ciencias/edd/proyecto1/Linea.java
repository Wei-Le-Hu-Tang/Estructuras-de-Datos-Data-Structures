package mx.unam.ciencias.edd.proyecto1;

/**
 * Clase para lineas de texto.
 *
 * Esta clase nos permitirá tener una versión normalizada de las lineas
 * (sin ningun tipo de acentos, signos, caracteres especiales, o espacios)
 * para trabajar, sin perder la versión original.
 */

public class Linea {
    /* Texto original. */
    private String original;
    /* Texto normalizado. */
    private String normalizado;

    /** Construye un objeto de tipo Linea con un String (@param txt ) dado. **/
    public Linea(String txt) {
        this.original = txt;
        this.normalizado = txt.replaceAll("[^a-zA-Z]", "");
    }

    /**
     * Regresa el texto original
     * @return el texto original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * Regresa el texto normalizado
     * @return el texto normalizado
     */
    public String getNormalizado() {
        return normalizado;
    }
}