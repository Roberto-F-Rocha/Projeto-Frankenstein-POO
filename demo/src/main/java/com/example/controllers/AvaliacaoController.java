package com.example.controllers;

import com.example.models.AvaliacaoModel;
import com.example.view.AvaliacaoView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AvaliacaoController {
    private AvaliacaoModel avaliacaoModel;
    private AvaliacaoView avaliacaoView;

    public AvaliacaoController(AvaliacaoModel avaliacaoModel, AvaliacaoView avaliacaoView) {
        this.avaliacaoModel = avaliacaoModel;
        this.avaliacaoView = avaliacaoView;
    }

    public void iniciar() {
        try {
            // Criando uma instância de ConexaoController
            ConexaoController conexaoController = new ConexaoController();

            // Obtendo a conexão
            Connection conexao = conexaoController.conectar();

            Scanner scanner = new Scanner(System.in, "CP850");
            int opcao;

            // Loop para exibir o menu e processar as opções do usuário
            while (true) {
                avaliacaoView.exibirMenu(); // Exibir o menu

                // Capturar a escolha do usuário
                opcao = avaliacaoView.obterOpcao(scanner);

                // Processar a opção do usuário
                switch (opcao) {
                    case 1:
                        avaliacaoView.exibirMensagem("Adicionar nova avaliação:");
                        System.out.println("\n");
                        avaliacaoModel.adicionarAvaliacao(conexao, scanner);
                        break;
                    case 2:
                        avaliacaoView.exibirMensagem("\nVisualizando informações da avaliação:");
                        avaliacaoModel.listarAvaliacao(conexao, scanner);
                        break;
                    case 3:
                        avaliacaoView.exibirMensagem("\nEditando informações da avaliação:");
                        avaliacaoModel.editarAvaliacao(conexao, scanner);
                        break;
                    case 4:
                        avaliacaoView.exibirMensagem("\nListando todas as avaliações:");
                        avaliacaoModel.listarAvaliacoes(conexao);
                        break;
                    case 5:
                        avaliacaoView.exibirMensagem("\nRemovendo a avaliação:");
                        avaliacaoModel.removerAvaliacao(conexao, scanner);
                        break;
                    case 6:
                        avaliacaoView.exibirMensagem("Saindo do menu de avaliação...");
                        return;
                    default:
                        avaliacaoView.exibirMensagem("Opção inválida. Por favor, escolha uma opção válida.");
                        break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao conectar ao banco de dados: " + ex.getMessage());
        }
    }
}
