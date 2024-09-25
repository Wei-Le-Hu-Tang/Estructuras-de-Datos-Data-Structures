package mx.unam.ciencias.edd;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas no aceptan a <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Coleccion<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        private T elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(T elemento) {
            // Aquí va su código.
            this.elemento = elemento;
        }
    }

    /* Clase interna privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nuevo iterador. */
        private Iterador() {
            // Aquí va su código.
            start();
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            // Aquí va su código.
            return siguiente != null;
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
            // Aquí va su código.
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
            T n = siguiente.elemento;
            anterior = siguiente;
            siguiente = siguiente.siguiente;
            return n;
            }
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
            // Aquí va su código.
            return anterior != null;
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            // Aquí va su código.
            if (hasPrevious() == false) throw new NoSuchElementException();
            T n = anterior.elemento;
            siguiente = anterior;
            anterior = anterior.anterior;
            return n;
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
            // Aquí va su código.
            siguiente = cabeza;
            anterior = null;
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
            // Aquí va su código.
            anterior = rabo;
            siguiente = null;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista. El método es idéntico a {@link
     * #getElementos}.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        // Aquí va su código.
        return longitud;
    }

    /**
     * Regresa el número elementos en la lista. El método es idéntico a {@link
     * #getLongitud}.
     * @return el número elementos en la lista.
     */
    @Override public int getElementos() {
        // Aquí va su código.
        return getLongitud();
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacia() {
        // Aquí va su código.
        return longitud == 0;
    }

    /**
     * Agrega un elemento a la lista. Si la lista no tiene elementos, el
     * elemento a agregar será el primero y último. El método es idéntico a
     * {@link #agregaFinal}.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void agrega(T elemento) {
        // Aquí va su código.
        agregaFinal(elemento);
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        // Aquí va su código.
        if (elemento == null) throw new IllegalArgumentException();
        
        Nodo n = new Nodo(elemento);
        if (esVacia()){
            cabeza = n;
            rabo = n;
        } else {
            rabo.siguiente = n;
            n.anterior = rabo;
            rabo = n;
        }
        longitud++;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        // Aquí va su código.
        if (elemento == null) throw new IllegalArgumentException();
        
        Nodo n = new Nodo(elemento);
        if (esVacia()){
            cabeza = n;
            rabo = n;
        } else {
            n.siguiente = cabeza;
            cabeza.anterior = n;
            cabeza = n;
        }
        longitud++;
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
        // Aquí va su código.
        if (elemento == null) throw new IllegalArgumentException();

        if (i <= 0){
            agregaInicio(elemento);
        } else {
            if (i >= longitud){
                agregaFinal(elemento);
            } else {
                //Nodo n = buscaNodo(get(i));
                
                Nodo n = new Nodo(elemento);
                int k = 0;
                Nodo aux = cabeza;
                while (k < i) {
                    aux = aux.siguiente;
                    k++;
                }
                aux.anterior.siguiente = n;
                n.anterior = aux.anterior;
                aux.anterior = n;
                n.siguiente = aux;
                longitud++;
            }
        }
    }

    // Método auxiliar
    // regresa el primer nodo con cierto elemento e
    private Nodo buscaNodo(T e) { 
        Nodo n = cabeza;
        if (e == null) return null;
        while (n != null) {
            if (n.elemento.equals(e)) return n;
            n = n.siguiente;      
        }
        return null;
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        // Aquí va su código.
        Nodo m = buscaNodo(elemento);
        if (m == null){
            return;
        }
        else {
            if (longitud == 1) {
                limpia();
            } else {
                if (m.equals(cabeza)){
                    eliminaPrimero();
                } else if (m.equals(rabo)){
                    eliminaUltimo();
                } else {
                    m.anterior.siguiente = m.siguiente;
                    m.siguiente.anterior = m.anterior;
                    longitud--;
                }
            }
        }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        // Aquí va su código.
        if (esVacia()) {
            throw new NoSuchElementException();
        } else {
            T prim = cabeza.elemento;
            if (longitud == 1) {
                limpia();
            } else {
                cabeza = cabeza.siguiente;
                cabeza.anterior = null;
                longitud--;
            }
            return prim;
        }
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        // Aquí va su código.
        if (esVacia()) {
            throw new NoSuchElementException();
        } else {
            T ulti = rabo.elemento;
            if (longitud == 1) {
                limpia();
            } else {
            rabo = rabo.anterior;
            rabo.siguiente = null;
            longitud--;
            }
            return ulti;
        }
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        // Aquí va su código.
        return buscaNodo(elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        // Aquí va su código.
        Lista<T> l = new Lista<>();
        
        if (longitud != 0) {
            Nodo aux = rabo;

            int k = 0;
            while (k < longitud) {
                l.agregaFinal(aux.elemento);
                aux = aux.anterior;
                k++;
            }
        }

        return l;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        // Aquí va su código.
        Lista<T> l = new Lista<>();
        
        if (longitud != 0) {
            Nodo aux = cabeza;

            while (aux != null) {
                l.agregaFinal(aux.elemento);
                aux = aux.siguiente;
            }
        }
        
        return l;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    @Override public void limpia() {
        // Aquí va su código.
        cabeza = null;
        rabo = null;
        longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        // Aquí va su código.
        if (esVacia()) {
            throw new NoSuchElementException();
        } else {
            return cabeza.elemento;
        }
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        // Aquí va su código.
        if (esVacia()) {
            throw new NoSuchElementException();
        } else {
            return rabo.elemento;
        }
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
        // Aquí va su código.
        if (i < 0 || i >= longitud) throw new ExcepcionIndiceInvalido();
        
        int n = 0;
        Nodo aux = cabeza;
        while (n != i) {
            aux = aux.siguiente;
            n++;
        }
        return aux.elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        // Aquí va su código.
        if (esVacia()) {
            return -1;
        } else {
            Nodo aux = cabeza;
            int i = 0;
            while (i < longitud && !aux.elemento.equals(elemento)) {
                aux = aux.siguiente;
                i++;
            }
            if (i == longitud) {
                return -1;
            } else {
                return i;
            }
        }
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        // Aquí va su código.
        if (esVacia()) {
            return "[]";
        } else {
            Nodo aux = cabeza;
            
            String listr = "[" + aux.elemento.toString();
            while (aux.siguiente != null) {
                aux =  aux.siguiente;
                listr = listr + ", " + aux.elemento.toString();
            }
            listr = listr + "]";

            return listr;
        }
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
        // Aquí va su código.
        if (longitud != lista.getLongitud()) {
            return false;
        } else if (esVacia()) {
            return true;
        } else {
            int i = 0;
            Nodo aux1 = cabeza;
            Nodo aux2 = lista.cabeza;
            while (i != longitud) {
                if (!aux1.elemento.equals(aux2.elemento)) return false;
                aux1 = aux1.siguiente;
                aux2 = aux2.siguiente;
                i++;
            }
        }
            return true;
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }

    /**
     * Regresa una copia de la lista, pero ordenada. Para poder hacer el
     * ordenamiento, el método necesita una instancia de {@link Comparator} para
     * poder comparar los elementos de la lista.
     * @param comparador el comparador que la lista usará para hacer el
     *                   ordenamiento.
     * @return una copia de la lista, pero ordenada.
     */
    public Lista<T> mergeSort(Comparator<T> comparador) {
        // Aquí va su código.
        return mergeaux(this, comparador);
    }

    private Lista<T> mergeaux(Lista<T> l2, Comparator<T> comparador) {
        if (l2.getLongitud() < 2) return l2;
        
        Lista<T> l1 = new Lista<>();

        int m = l2.getLongitud()/2;
        for (int i = 0; i < m; i++){
            l1.agregaFinal(l2.getPrimero());
            l2.eliminaPrimero();
        }
        return mezcla(mergeaux(l1,comparador),mergeaux(l2,comparador),comparador);
    }

    private Lista<T> mezcla(Lista<T> l1, Lista<T> l2, Comparator<T> c) {
        Lista<T> lista = new Lista<T>();
        while (l1.cabeza != null && l2.cabeza != null) {
            if (c.compare(l1.getPrimero(),l2.getPrimero()) < 0){
                lista.agregaFinal(l1.getPrimero());
                l1.eliminaPrimero();
            } else {
                lista.agregaFinal(l2.getPrimero());
                l2.eliminaPrimero();
            }
        }
        while (l1.cabeza != null) {
            lista.agregaFinal(l1.getPrimero());
            l1.eliminaPrimero();
        }
        while (l2.cabeza != null) {
            lista.agregaFinal(l2.getPrimero());
            l2.eliminaPrimero();
        }
        return lista;
    }

    /**
     * Regresa una copia de la lista recibida, pero ordenada. La lista recibida
     * tiene que contener nada más elementos que implementan la interfaz {@link
     * Comparable}.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista que se ordenará.
     * @return una copia de la lista recibida, pero ordenada.
     */
    public static <T extends Comparable<T>>
    Lista<T> mergeSort(Lista<T> lista) {
        return lista.mergeSort((a, b) -> a.compareTo(b));
    }

    /**
     * Busca un elemento en la lista ordenada, usando el comparador recibido. El
     * método supone que la lista está ordenada usando el mismo comparador.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador con el que la lista está ordenada.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean busquedaLineal(T elemento, Comparator<T> comparador) {
        // Aquí va su código.
        return linealaux(this,elemento,comparador);
    }

    private boolean linealaux(Lista<T> l, T elemento, Comparator<T> comparador) {
        if (l.esVacia()) return false;
        Nodo aux = l.cabeza;
        while (aux != null) {
            if (comparador.compare(elemento,aux.elemento) < 0) return false;
            if (comparador.compare(elemento,aux.elemento) == 0) return true;
            aux = aux.siguiente;
        }
        return false;
    }

    /**
     * Busca un elemento en una lista ordenada. La lista recibida tiene que
     * contener nada más elementos que implementan la interfaz {@link
     * Comparable}, y se da por hecho que está ordenada.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista donde se buscará.
     * @param elemento el elemento a buscar.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public static <T extends Comparable<T>>
    boolean busquedaLineal(Lista<T> lista, T elemento) {
        return lista.busquedaLineal(elemento, (a, b) -> a.compareTo(b));
    }
}
