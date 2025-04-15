package com.ejemplo.LeeYCreaDatosAleatorio;

//Importamos java.io con todas sus clases para poder usar el writeBytes, BufferReader, RandomAccessFile clases que nos van a afacilitar mucho la creacion del programa
import java.io.*;
import java.util.*;

//Creamos la clase LeeYCreaDatosAleatorio
class LeeYCreaDatosAleatorio {
   //Creamos el main
   public static void main(String[] args) {
      try (
         // Abrir el archivo datos.txt para lectura
    		  
         // En caso de ser un Pc usamos con esta direccion el objeto que creamos :
         BufferedReader br = new BufferedReader(new FileReader("C:/EjerciciosStreams/datos.txt"));
    	 
    	 // Asi creamos el objeto br con la direccion para mac : 
    	 // BufferedReader br = new BufferedReader(new FileReader("/Users/josechuair/EjerciciosStreams/datos.txt"));
         
    	 // Crear el archivo de acceso aleatorio llamado datosAleatorio con rw para que se pueda leer y escribir en el
    	 
    	 // Asi creamos el objeto raf con la direccion para pc y que hace un acceso 
         RandomAccessFile raf = new RandomAccessFile("C:/EjerciciosStreams/datosAleatorio.txt", "rw")
    	 
         // Asi creamos el objeto raf para un mac con la direccion donde se creara el archivo datosAleatorio.txt con las lineas de codigo leidas y escritas de forma aleatoria
         // RandomAccessFile raf = new RandomAccessFile("/Users/josechuair/EjerciciosStreams/datosAleatorio.txt", "rw")
         
    	 ) 
      
      	 {
         // Creamoos la lista para almacenar las líneas del archivo
         List<String> lineas = new ArrayList<>();
         String linea;
         
         // Leer línea por línea y almacenarlas en la lista
         while ((linea = br.readLine()) != null) {
            lineas.add(linea);
         }

         // Mezclar las líneas en orden aleatorio
         Collections.shuffle(lineas);

         // Escribir las líneas en orden aleatorio
         for (String l : lineas) {
            raf.writeBytes(l + "\n");
         }
         
         // Mover el puntero del archivo al inicio antes de leer para que pueda mostranos luego el while las lineas de codigo del archivo nuevo creado
         raf.seek(0);
       //Mientras hay linea no vacia va leyendo linea y escribiendo cada linea en pantalla
         String lineaLeida;
         while ((lineaLeida = raf.readLine()) != null) {
             System.out.println(lineaLeida);
         }

         //Mostramos en pantalla que ya hemos copiado los datos de forma aleatoria en el archivo
         System.out.println("Datos copiados en orden aleatorio a datosAleatorio.txt.");
      } 
      //Manejo de excepciones 
      catch (IOException e) {
         System.out.println("Error en el programa.");
      }
   }
}