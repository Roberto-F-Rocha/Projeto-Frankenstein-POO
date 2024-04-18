package com.example;

import com.example.controllers.AnimalEstimacaoController;
import com.example.controllers.ClienteController;
import com.example.controllers.ServicoController; // Import adicionado
import com.example.controllers.FaturaController; // Import adicionado
import com.example.models.AnimalEstimacaoModel;
import com.example.models.ClienteModel;
import com.example.models.ServicoModel; // Import adicionado
import com.example.models.FaturaModel; // Import adicionado
import com.example.view.AnimalEstimacaoView;
import com.example.view.ClienteView;
import com.example.view.ServicoView; // Import adicionado
import com.example.view.FaturaView; // Import adicionado

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in,"CP850");
        ClienteModel clienteModel = new ClienteModel();
        ClienteView clienteView = new ClienteView();
        ClienteController clienteController = new ClienteController(clienteModel, clienteView);
        
        AnimalEstimacaoModel animalModel = new AnimalEstimacaoModel();
        AnimalEstimacaoView animalView = new AnimalEstimacaoView();
        AnimalEstimacaoController animalController = new AnimalEstimacaoController(animalModel, animalView);

        ServicoModel servicoModel = new ServicoModel(); // Instância do ServicoModel
        ServicoView servicoView = new ServicoView(); // Instância do ServicoView
        ServicoController servicoController = new ServicoController(servicoModel, servicoView); // Instância do ServicoController

        FaturaModel faturaModel = new FaturaModel();
        FaturaView faturaView = new FaturaView();
        FaturaController faturaController = new FaturaController(faturaModel, faturaView); // Instância do FaturaModel

        int opcao;

        do {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Operações do Cliente");
            System.out.println("2. Operações de Animais de Estimação");
            System.out.println("3. Operações de Serviço"); // Nova opção adicionada
            System.out.println("4. Operações de Fatura"); // Nova opção adicionada
            System.out.println("5. Sair"); // Opção de sair movida para 4
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
                    System.out.println("\nMenu Serviço:"); // Mensagem adicionada
                    servicoController.iniciar(); // Chamada para iniciar o menu de Serviço
                    break;
                case 4:
                    System.out.println("\nMenu Fatura:"); // Mensagem adicionada
                    faturaController.iniciar(); // Chamada para iniciar o menu de Serviço
                    break;
                case 5:
                    System.out.println("Saindo do programa. Até mais!");
                    scanner.close();
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        } while (opcao != 5); // Condição alterada para 4
    }
}
