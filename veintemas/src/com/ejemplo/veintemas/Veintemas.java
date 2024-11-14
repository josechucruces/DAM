package com.ejemplo.veintemas;

import java.util.Scanner;

public class Veintemas {

    public static void main(String[] args) {
        int num = -2; 
        
        if (num < 0) { 
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingresa un número: ");
            num = sc.nextInt();
            sc.close();
        }
        
        for (int i = 0; i < 20; i++) { 
            System.out.print(num + " "); 
            num++; 
        }
    }
}
