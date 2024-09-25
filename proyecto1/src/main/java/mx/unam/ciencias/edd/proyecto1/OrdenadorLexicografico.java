package mx.unam.ciencias.edd.proyecto1;

import mx.unam.ciencias.edd.Lista;

/**
 * Los métodos en la clase OrdenadorLexicográfico nos permitirán, como dice su nombre,
 * ordenar de manera lexicográfica una {@link Lista} de objetos de la clase {@link Linea},
 * al derecho o al revés, omitiendo los espacios, acentos, y caracteres especiales.
 * 
 * En ambos casos utilizando el algoritmo mergeSort(), para tener una complejidad de O(n log n).
 */

public class OrdenadorLexicografico {

    /**
     * Ordena una lista de lineas de texto utilizando mergeSort, y comparando
     * las lineas de texto normalizadas.
     * @param textote la lista de lineas de texto.
     * @return la lista de lineas de texto ordenada de manera lexicográfica.
     */
    public static Lista<Linea> ordena(Lista<Linea> textote) {
        return textote.mergeSort((t1,t2)-> {
            String t1Norm = t1.getNormalizado();
            String t2Norm = t2.getNormalizado();
            return t1Norm.compareTo(t2Norm);
        });
    }

    /**
     * Ordena una lista de lineas de texto utilizando mergeSort, en orden inverso,
     * y comparando las lineas de texto normalizadas.
     * Esto se logra mediante el intercambio de orden de parámetros en el método
     * compareTo().
     * @param textote la lista de lineas de texto
     * @return la lista de lineas de texto ordenada de manera lexicográfica.
     */
    public static Lista<Linea> ordenaReverso(Lista<Linea> textote) {
        return textote.mergeSort((t1,t2)-> {
            String t1Norm = t1.getNormalizado();
            String t2Norm = t2.getNormalizado();
            return t2Norm.compareTo(t1Norm);
        });
    }

}
