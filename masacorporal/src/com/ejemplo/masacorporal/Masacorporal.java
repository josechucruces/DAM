package com.ejemplo.masacorporal;

import java.util.Scanner;

public class Masacorporal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int masa1 = 0;
        float altura1 = 0;

        while (true) {
            try {
                System.out.print("Dime tu peso: ");
                masa1 = sc.nextInt();
                System.out.print("Ahora dime tu altura , escribe la altura con coma, 1,70 por favor, con punto no ");
                System.out.print("\nEscribe la altura en metros (ejemplo: 1.75): ");
                altura1 = sc.nextFloat();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                sc.nextLine(); 
                continue;
            }

            if (masa1 >= 30 && masa1 <= 300 && altura1 > 1.30 && altura1 < 2.00) {
                System.out.println("Peso: " + masa1 + " kg, altura: " + altura1 + " metros");

                float imc = masa1 / (altura1 * altura1);

                if (imc < 18.5) {
                    System.out.println("Nivel de peso bajo");
                } else if (imc >= 18.5 && imc < 24.9) {
                    System.out.println("Nivel de peso normal");
                } else if (imc >= 25 && imc < 29.9) {
                    System.out.println("Tienes sobrepeso");
                } else if (imc >= 30) {
                    System.out.println("Tienes obesidad");
                }

                break; 
            } else {
                System.out.println("Los valores no están en el rango. Por favor, inténtalo de nuevo.");
            }
        }

        sc.close();
    }
}