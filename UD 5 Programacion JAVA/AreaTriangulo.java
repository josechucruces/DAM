package com.ejemplo.AreaTriangulo;
// importamos la libreria java.io y todas sus clases para usar PrintWriter
import java.io.*;
import java.util.Scanner;

//Creamos la clase AreaTriangulo
public class AreaTriangulo
{
	//Creamos el main
    public static void main(String[] args)
    {
    	//Creamos el try para luego manejar las excepciones
    	// En el lo que hacemos es que pruebe a crear el archivo dos.java si hay algun problema mandara la excepcion
    	
    	// En caso de un Pc seria asi el try :
    	try (FileWriter fichero = new FileWriter("C:/EjerciciosStreams/dos.java")) {
    	
    	// En caso de mac seria esta version del try con esa direccion del archivo
    	//try (FileWriter fichero = new FileWriter("/Users/josechuair/EjerciciosStreams/dos.java")) {
           
    		// Declaración de variables
            float base = 0;
            float altura = 0;
            float area = 0;
            
            // Creamos el objeto scanner para leer los datos de la base y altura en las siguientes lineas de codigo
            Scanner scanner = new Scanner(System.in);
            //Ponemos por pantalla lo que le pedimos que nos escriba el usuario del problema
	        System.out.println("Dame la base del área de un triángulo");
	        //Guardamos en base el dato que nos escribe el usuario
            base = scanner.nextFloat();
            //Ponemos por pantalla lo que le pedimos que nos escriba el usuario del problema, en este caso la altura del triangulo
            System.out.println("Dame la altura del área de un triángulo");
            //Guardamos en altura el dato que escribe el usuario
            altura = scanner.nextFloat();
	        // Cerramos scanner, ya no lo necesitamos
	        scanner.close();
            
            // Cálculo del área del triángulo con los datos de base y altura que el usuario ha dado
            area = (base * altura) / 2;

            // Usamos PrintWriter para escribir el resultado en el archivo
            PrintWriter pw = new PrintWriter(fichero);
            pw.println("Área del triángulo: " + area);
            System.out.println("El área es: "+ area);
            // Sacamos en pantalla que hemos guardado en dos.java el tamaño del area
	        System.out.println("Y el tamaño del área ha sido guardada en dos.java");
	        //Manejamos la excepcion y sacamos por pantalla que hay un error 
            }
 			catch (Exception e) {
	        System.out.println("Error en el programa.");
        }
    }
}
