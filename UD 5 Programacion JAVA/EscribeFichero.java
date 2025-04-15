package com.ejemplo.EscribeFichero;

//Importamos la librería java.io para trabajar con archivos y directorios, 
//utilizando las clases File para manejarnos en los directorios, FileWriter para crear archivos y Printwriter para escribir en el .

import java.io.*;

//Creamos la clase EscribeFichero que hara las cosas que nos pide el ejercicio
public class EscribeFichero {
//Creamos el main que lo ejecutara
 public static void main(String[] args) {

     // Cremos un string con la ruta del archivo
     
	 // En caso de ser un Pc la ruta seria asi :
	 String ruta = "C:/EjerciciosStreams/uno.java";
	
	 // En caso de ser un mac :
	 // String ruta = "/Users/josechuair/EjerciciosStreams/uno.java";
     
	 //Creamos el objeto archivo con la ruta designada
     File archivo = new File(ruta);
     //Creamos un try para gestionar que compruebe si existe o no el archivo que crearemos
     try {
         // Comprobar si el archivo existe
         if (archivo.exists()) {
             System.out.println("El archivo ya existe.");
         } else {
             // Crear el archivo
             if (archivo.createNewFile()) {
	// Mostramos en pantalla que hemos creado el archivo
                 System.out.println("Archivo creado.");
             } else {
	// Mostramos que no se puede crear el archivo
                 System.out.println("No se pudo crear el archivo.");
	  // Y salimos por no haber podido crearlo
                 return;
             }
         }

            // Abrir el archivo en modo escritura y escribir los números del 0 al 10
            FileWriter fw = new FileWriter(archivo, true); 
	// Usamos PrintWriter para escribir de manera cómoda
	PrintWriter pw = new PrintWriter(fw);         

	// Ponemos dentro del archivo los numeros del 1 al 10
             for (int i = 0; i <= 10; i++) {
                 pw.println(i);
             }
	// Mostramos en pantalla que ya estan escritos los numeros en el archivo
             System.out.println("Números escritos en el archivo.");
         
	// Cerramos el PrintWriter y el FileWriter
	pw.close();
	fw.close();
	//Manejo de excepciones
     } catch (IOException e) {
         System.out.println("Ha habido un error en el programa");
     }
 }
}