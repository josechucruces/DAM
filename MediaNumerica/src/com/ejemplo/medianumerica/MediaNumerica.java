package com.ejemplo.medianumerica;

//Programa Java para leer algunos valores usando la clase Scanner

//e imprimir su media

import java.util.Scanner;

public class ScannerDemo2

{

public static void main(String[] args)

{

//Declarar un objeto e inicializar con

//el objeto de entrada estándar predefinido

Scanner sc = new Scanner(System.in);

//Inicializar la suma y el recuento

//de los elementos de entrada

int sum = 0, count = 0;

//Compruebe si un valor int está disponible

while (sc.hasNextInt())

{

//Lee un valor int

int num = sc.nextInt();

sum += num;

count++;

}

int mean = sum / count;

System.out.println("La media es: " + mean);

}

}