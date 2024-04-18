package com.example;

import com.example.controllers.AnimalEstimacaoController;
import com.example.controllers.ClienteController;
import com.example.models.AnimalEstimacaoModel;
import com.example.models.ClienteModel;
import com.example.view.AnimalEstimacaoView;
import com.example.view.ClienteView;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteModel clienteModel = new ClienteModel();
        ClienteView clienteView = new ClienteView();
        ClienteController clienteController = new ClienteController(clienteModel, clienteView);
        
        AnimalEstimacaoModel animalModel = new AnimalEstimacaoModel();
        AnimalEstimacaoView animalView = new AnimalEstimacaoView();
        AnimalEstimacaoController animalController = new AnimalEstimacaoController(animalModel, animalView);

        int opcao;

        do {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Operações do Cliente");
            System.out.println("2. Operações de Animais de Estimação");
            System.out.println("3. Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\nMenu Cliente:");
                    clienteController.iniciar();
                    break;
                case 2:
                    System.out.println("\nMenu Animais de Estimação:");
                    animalController.iniciar();
                    break;
                case 3:
                    System.out.println("Saindo do programa. Até mais!");
                    scanner.close();
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        } while (opcao != 3);

    }
}
