package com.ejemplo.ScannerDemo2;

import java.util.Scanner;

public class ScannerDemo2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sum = 0, count = 0;

        while (sc.hasNextInt()) {
            int num = sc.nextInt();
            sum += num;
            count++;
        }

        if (count > 0) {
            int mean = sum / count;
            System.out.println("La media es: " + mean);
        } else {
            System.out.println("No se ingresaron valores enteros.");
        }
    }
}