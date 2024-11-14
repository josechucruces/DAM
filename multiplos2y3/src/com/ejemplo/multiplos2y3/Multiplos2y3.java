package com.ejemplo.multiplos2y3;

public class Multiplos2y3 {

    public static void main(String[] args) {

        int num = 1; 
        int cuentanum = 0;

        for (int i = 0; i < 100; i++) {
            if (num % 2 == 0) {  
                System.out.print(num + " ");
                cuentanum++; 
            } else if (num % 3 == 0) {  
                System.out.print(num + " ");
                cuentanum++; 
            }
            num++;  
        }
        System.out.print("\nCantidad de numeros mostrados : ");
        System.out.print(cuentanum); 
    }
}

