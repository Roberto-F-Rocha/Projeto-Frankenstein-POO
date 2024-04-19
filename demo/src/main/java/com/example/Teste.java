package com.example;

import java.util.Scanner;

public class Teste {

    public static void main(String[] args) {

        // read line
        System.out.println("Por favor, entre com os caracteres a serem lidos na próxima linha:");
        Scanner scanner = new Scanner(System.in,"CP850");

        // print line
        System.out.println("Caracteres lidos na linha anterior: " + scanner.nextLine());
        scanner.close();
    }
}
