package com.ejemplo.LeeFichero;

// Importamos java.io y todas sus clases de esa libreria para poder usarlas, cmo FileReader o BufferedReader
import java.io.*;

//Creamos la clase LeeFichero
class LeeFichero {
	//Creamos el main de la clase
   public static void main(String[] arg) {

	  //Lanzamos el try para que intente hacer unas lineas de codigo y si no puede ser manejara la excepcion en el catch
      try 
      
      {
    	  // Creamos el objeto fr con la direccion donde esta el arhivo que queremos leer 
    	  
    	  // Esta creacion del obketo fr es para un pc :
    	  FileReader fr = new FileReader("C:/EjerciciosStreams/datos.txt");
    	  
    	  // Y esta verion es para el mac :
    	  // FileReader fr = new FileReader("/Users/josechuair/EjerciciosStreams/datos.txt");
    	  
    	  // Con BufferedReader crear el objeto br que usaremos despues para lee el fichero
    	  BufferedReader br = new BufferedReader(fr);
    	  // Declaramos el String linea para guardar ahi lo que ha leido del archivo datos.txt
    	  String linea;
    	  // Lectura del fichero
    	  //Mientras hay linea no vacia va leyendo linea y escribiendo cada linea en pantalla
    	  while ((linea = br.readLine()) != null) {
          System.out.println(linea);
    	  }
      //Cerramos los dos objetos creados anteriormente br y fr
	  br.close();
	  fr.close();
      }
      //Manejamos la excepcion
      catch (Exception e) {
         System.out.println("Error en el programa.");
      }
   }
}