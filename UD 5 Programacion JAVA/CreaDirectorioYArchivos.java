package com.ejemplo.CreaDirectorioYArchivos;

//Importamos la librería java.io para trabajar con archivos y directorios, 
//utilizando las clases File, FileWriter.

import java.io.*;

//Definimos la clase CreaDirectorioYArchivos, para la creación de un directorio y archivos dentro de él.

public class CreaDirectorioYArchivos {
 // Método main donde se ejecutará el programa.
public static void main(String[] args) {

     // Definimos la ruta del directorio que se creará si no existe. 
	 // La direccion en un Pc seria asi :
     String directorio = "C:/EjerciciosStreams";
	 
     // Este String en caso de un mac
     // String directorio = "/Users/josechuair/EjerciciosStreams";
    
     // Creamos un objeto File con la ruta especificada para representar el directorio.
     File dir = new File(directorio);

     // Miramos si el directorio no existe.
     if (!dir.exists()) {
         // Si no existe, lo creamos usando mkdir().
         dir.mkdir();
         // Mostramos un mensaje que dice que el directorio ha sido creado.
         System.out.println("Directorio creado");
     }

     // Creamos los String con las rutas de los archivos que vamos a crear dentro del directorio.
     // Esto serian las rutas en un String para Pc :
     String ruta = "C:/EjerciciosStreams/uno.java"; 
     String ruta2 = "C:/EjerciciosStreams/dos.java";
     // Estas en caso de mac
     // String ruta = "/Users/josechuair/EjerciciosStreams/uno.java";
     // String ruta2 = "/Users/josechuair/EjerciciosStreams/dos.java";

     // Creamos objetos File para luego crear los archivos en las rutas que tenemos.
     File archivo = new File(ruta);
     File archivo2 = new File(ruta2);

   
	 // Creamos el archivo uno.java.

	// Verificamos si el archivo ya existe antes de crearlo
    if (!archivo.exists()) {
    	// Ponemos el try para que intente crear el archivo y si no puede gestione la excepcion
    	try {
	// Paso 1: Crear FileWriter
    	FileWriter fileWriter1 = new FileWriter(archivo);
	// Paso 2: Cerramos fileWriter1 
    	fileWriter1.close(); 
     	//Mostramos en pantalla que hemos creado el archivo
        System.out.println("Archivo uno.java creado");
    	}
    	//Aqui ponemos la gestion de la excepción 
    	catch (IOException e) {
        System.out.println("Error al crear el archivo uno.java: " + e.getMessage());
        }
     }

     // Creamos el archivo dos.java.

        // Verificamos si el archivo ya existe antes de crearlo
    if (!archivo2.exists()) { 
    	//Lanzamos el try para que lo intente y si no puede ser gestione la excepcion
    	try {
        // Paso 1: Crear FileWriter
          FileWriter fileWriter2 = new FileWriter(archivo2); 
         // Paso 2: Cerramos fileWriter2 
          fileWriter2.close();
         //Mostramos en pantalla que hemos creado el archivo dos.java
         System.out.println("Archivo dos.java creado");
    	}
    	//Aqui ponemos la gestion de la excepción que lanzara el mensaje de error
    	catch (IOException e) {
            System.out.println("Error al crear el archivo dos.java: " + e.getMessage());
    	}
    }
 }
}