Estructuras de Datos
====================

Proyecto 1 : Ordenador Lexicográfico por lineas.
------------------------------------------------

# Función

Este programa ordena las lineas de un texto dada su ruta:

    $ java -jar target/proyecto1.jar /ruta/cualquiera/archivo.txt

o bien con la entrada estándar:

    $ cat /ruta/cualquiera/archivo.txt | java -jar target/proyecto1.jar

En caso de querer adjuntar varios textos, sus rutas son separadas por un espacio:

    $ java -jar target/proyecto1.jar /ruta/cualquiera/archivo.txt /ruta/cualquiera/otro.txt

# Banderas

* Si el usuario pasa la bandera -r, el programa imprime las líneas en orden inverso.

Ejemplo:

    $ java -jar target/proyecto1.jar /ruta/cualquiera/archivo.txt -r

* Si el usuario pasa la bandera -o seguida de una ruta, el programa guarda el texto en
el archivo que se encuentre en esa ruta. Esta operación es destructiva.

Ejemplo:

    $ java -jar target/proyecto1.jar /ruta/cualquiera/archivo.txt -o /ruta/cualquiera/reemplazado.txt

* Las banderas -r y -o deben aparecer a lo más una vez. De lo contrario, se regresa un error.

# Orden

El orden en el que se ingresan los argumentos no importa, sólo para la bandera -o, pues la ruta que aparezca inmediatamente después será el archivo de texto a reemplazar.

Ejemplo: es lo mismo poner
    
    $ java -jar target/proyecto1.jar /ruta/cualquiera/archivo.txt /ruta/cualquiera/otro.txt -r -o /ruta/cualquiera/reemplazado.txt
a ingresar
    
    $ java -jar target/proyecto1.jar -o /ruta/cualquiera/reemplazado.txt /ruta/cualquiera/otro.txt -r /ruta/cualquiera/archivo.txt
