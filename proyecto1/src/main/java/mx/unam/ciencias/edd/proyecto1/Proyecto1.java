package mx.unam.ciencias.edd.proyecto1;

import mx.unam.ciencias.edd.Lista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/* Execusión del Proyecto */

public class Proyecto1 {
    public static void main(String[] args) {
        /* Recibida o no la bandera -r */
        boolean r = false;
        /* Recibida o no la bandera -o */
        boolean o = false;
        /* Lista de lineas de texto */
        Lista<Linea> textote = new Lista<Linea>();
        /* Ruta del archivo para sobreescribir tras recibir la bandera -o */
        String ruta = "";

        /* Caso de la entrada estándar */
        if (args.length == 0) {
            for (Linea linea : TextoALista.estandar()) {
                textote.agregaFinal(linea);
            }
        /* Caso de la entrada múltiple */
        } else {
            for (int i = 0; i < args.length; i++) {
                /* Se revisan todos los argumentos */
                switch (args[i]) {
                    case "-r":
                        /* Si la bandera -r aparece más de una vez, se imprime un error y se reinicia*/
                        if (r) {
                            System.err.println("Error: -r sólo puede aparecer una vez");
                            return;}
                        r = true;
                        break;
                    
                    case "-o":
                        /* Si la bandera -o aparece más de una vez, se imprime un error y se reinicia*/
                        if (o) {
                            System.err.println("Error: -r sólo puede aparecer una vez");
                            return;}
                        o = true;
                        /* Guarda la ruta del archivo a reemplazar (argumento seguido de la bandera -o, si es que hay) */
                        try {
                            ruta = args[i+1];
                        } catch (Exception e) {
                            System.err.println("Error: no hay ruta después de -o");
                            return;
                        }
                        break;

                    default:
                        /* Agregar todos los archivos al textote, excepto el que siga de -o*/
                        if (args[i] != ruta)
                            for (Linea linea : TextoALista.ruta(args[i]))
                                textote.agregaFinal(linea);
                    break;
                }
            }
        }

        /* Si se introdujo la bandera -r, el texto se ordena de forma inversa */
        if (r) textote = OrdenadorLexicografico.ordenaReverso(textote);
        else textote = OrdenadorLexicografico.ordena(textote);

        /* Si se introdujo la bandera -o, el archivo en ruta es reemplazado por el nuevo texto ordenado */
        if (o) {
            try (BufferedReader txt = new BufferedReader(new FileReader(ruta))) {
                File txtViejo = new File(ruta);
                txtViejo.delete();
                File txtNuevo = new File(ruta);

                FileWriter txtEscribe = new FileWriter(txtNuevo);
                for (Linea linea : textote){
                    txtEscribe.write(linea.getOriginal());
                    txtEscribe.write("\n");
                }
                txtEscribe.close();
            } catch (IOException e) {
                System.err.println("Error en ruta para sobreescribir: " + e.getMessage());
                return;
            }
        }

        /* Se imprime el texto final ordenado */
        for (Linea linea : textote)
            System.out.println(linea.getOriginal());
    }
}
