package com.ejemplo.give3;

import java.util.Scanner;

public class give3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Dame el primer número: ");
        int numero1 = sc.nextInt();
        
        System.out.print("Dame el segundo número: ");
        int numero2 = sc.nextInt();
        
        System.out.print("Dame el tercer número: ");
        int numero3 = sc.nextInt();
        
        String palabra;
        
        while (true) {
            System.out.print("Escribe una palabra exacta (ascendente o descendente): ");
            palabra = sc.next();
            
            if (palabra.equalsIgnoreCase("ascendente")) {
                System.out.println("Has elegido 'ascendente'.");
                ordenarYMostrar(numero1, numero2, numero3, palabra);
                break;  
            } else if (palabra.equalsIgnoreCase("descendente")) {
                System.out.println("Has elegido 'descendente'.");
                ordenarYMostrar(numero1, numero2, numero3, palabra);
                break;  
            } else {
                System.out.println("No respondes bien, por favor escribe 'ascendente' o 'descendente'.");
            }
        }
        
        sc.close();
    }
    
    public static void ordenarYMostrar(int num1, int num2, int num3, String palabra) {
        int menor, medio, mayor;
        
        if (num1 <= num2 && num1 <= num3) {
            menor = num1;
            if (num2 <= num3) {
                medio = num2;
                mayor = num3;
            } else {
                medio = num3;
                mayor = num2;
            }
        } else if (num2 <= num1 && num2 <= num3) {
            menor = num2;
            if (num1 <= num3) {
                medio = num1;
                mayor = num3;
            } else {
                medio = num3;
                mayor = num1;
            }
        } else {
            menor = num3;
            if (num1 <= num2) {
                medio = num1;
                mayor = num2;
            } else {
                medio = num2;
                mayor = num1;
            }
        }

        if (palabra.equalsIgnoreCase("ascendente")) {
            System.out.println(menor);
            System.out.println(medio);
            System.out.println(mayor);
        } else if (palabra.equalsIgnoreCase("descendente")) {
            System.out.println(mayor);
            System.out.println(medio);
            System.out.println(menor);
        }
    }
}