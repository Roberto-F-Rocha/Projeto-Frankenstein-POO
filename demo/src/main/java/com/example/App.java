package com.example;

import com.example.controllers.AnimalEstimacaoController;
import com.example.controllers.ClienteController;
import com.example.controllers.ServicoController;
import com.example.controllers.FaturaController;
import com.example.controllers.ProdutoController;
import com.example.controllers.AvaliacaoController;
import com.example.controllers.CompraController; // Import adicionado
import com.example.models.AnimalEstimacaoModel;
import com.example.models.ClienteModel;
import com.example.models.ServicoModel;
import com.example.models.FaturaModel;
import com.example.models.ProdutoModel;
import com.example.models.AvaliacaoModel;
import com.example.models.CompraModel; // Import adicionado
import com.example.view.AnimalEstimacaoView;
import com.example.view.ClienteView;
import com.example.view.ServicoView;
import com.example.view.FaturaView;
import com.example.view.ProdutoView;
import com.example.view.AvaliacaoView;
import com.example.view.CompraView; // Import adicionado

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "CP850");
        ClienteModel clienteModel = new ClienteModel();
        ClienteView clienteView = new ClienteView();
        ClienteController clienteController = new ClienteController(clienteModel, clienteView);

        AnimalEstimacaoModel animalModel = new AnimalEstimacaoModel();
        AnimalEstimacaoView animalView = new AnimalEstimacaoView();
        AnimalEstimacaoController animalController = new AnimalEstimacaoController(animalModel, animalView);

        ServicoModel servicoModel = new ServicoModel();
        ServicoView servicoView = new ServicoView();
        ServicoController servicoController = new ServicoController(servicoModel, servicoView);

        FaturaModel faturaModel = new FaturaModel();
        FaturaView faturaView = new FaturaView();
        FaturaController faturaController = new FaturaController(faturaModel, faturaView);

        ProdutoModel produtoModel = new ProdutoModel();
        ProdutoView produtoView = new ProdutoView();
        ProdutoController produtoController = new ProdutoController(produtoModel, produtoView);

        AvaliacaoModel avaliacaoModel = new AvaliacaoModel();
        AvaliacaoView avaliacaoView = new AvaliacaoView();
        AvaliacaoController avaliacaoController = new AvaliacaoController(avaliacaoModel, avaliacaoView);

        CompraModel compraModel = new CompraModel(); // Instância do CompraModel
        CompraView compraView = new CompraView(); // Instância do CompraView
        CompraController compraController = new CompraController(compraModel, compraView); // Instância do CompraController

        int opcao;

        do {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Operações do Cliente");
            System.out.println("2. Operações de Animais de Estimação");
            System.out.println("3. Operações de Serviço");
            System.out.println("4. Operações de Fatura");
            System.out.println("5. Operações de Produto");
            System.out.println("6. Operações de Avaliação");
            System.out.println("7. Operações de Compra"); // Nova opção adicionada
            System.out.println("8. Sair"); // Ajuste para a nova opção
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
                    System.out.println("\nMenu Serviço:");
                    servicoController.iniciar();
                    break;
                case 4:
                    System.out.println("\nMenu Fatura:");
                    faturaController.iniciar();
                    break;
                case 5:
                    System.out.println("\nMenu Produto:");
                    produtoController.iniciar();
                    break;
                case 6:
                    System.out.println("\nMenu Avaliação:");
                    avaliacaoController.iniciar();
                    break;
                case 7:
                    System.out.println("\nMenu Compra:"); // Mensagem adicionada
                    compraController.iniciar(); // Chamada para iniciar o menu de Compra
                    break;
                case 8:
                    System.out.println("Saindo do programa. Até mais!");
                    scanner.close();
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        } while (opcao != 8); // Condição alterada para 8
    }
}
