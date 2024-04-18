package com.example.controllers;

import com.example.models.AnimalEstimacaoModel;
import com.example.view.AnimalEstimacaoView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AnimalEstimacaoController {
    private AnimalEstimacaoModel animalModel;
    private AnimalEstimacaoView animalView;

    public AnimalEstimacaoController(AnimalEstimacaoModel animalModel, AnimalEstimacaoView animalView) {
        this.animalModel = animalModel;
        this.animalView = animalView;
    }

    public void iniciar() {
        try {
            // Criando uma instância de ConexaoController
            ConexaoController conexaoController = new ConexaoController();

            // Obtendo a conexão
            Connection conexao = conexaoController.conectar();

            Scanner scanner = new Scanner(System.in,"CP850");
            int opcao;

            // Loop para exibir o menu e processar as opções do usuário
            while (true) {
                animalView.exibirMenu(); // Exibir o menu

                // Capturar a escolha do usuário
                opcao = animalView.obterOpcao(scanner);

                // Processar a opção do usuário
                switch (opcao) {
                    case 1:
                        System.out.println("\n");
                        animalModel.adicionarAnimal(conexao, scanner);
                        break;
                    case 2:
                        animalView.exibirMensagem("\nVisualizando informações dos animais de estimação:");
                        animalModel.visualizarAnimais(conexao, scanner);
                        break;
                    case 3:
                        animalView.exibirMensagem("\nEditando informações de um animal de estimação:");
                        animalModel.editarAnimal(conexao, scanner);
                        break;
                    case 4:
                        animalView.exibirMensagem("\nListando todos os animais de estimação:");
                        animalModel.listarAnimais(conexao);
                        break;
                    case 5:
                        animalView.exibirMensagem("\nRemovendo um animal de estimação:");
                        animalModel.removerAnimal(conexao, scanner);
                        break;
                    case 6:
                        animalView.exibirMensagem("Saindo do menu de animais de estimação...");
                        return;
                    default:
                        animalView.exibirMensagem("Opção inválida. Por favor, escolha uma opção válida.");
                        break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao conectar ao banco de dados: " + ex.getMessage());
        }
    }
}
