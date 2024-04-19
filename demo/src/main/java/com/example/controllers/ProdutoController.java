package com.example.controllers;

import com.example.models.ProdutoModel;
import com.example.view.ProdutoView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ProdutoController {
    private ProdutoModel produtoModel;
    private ProdutoView produtoView;

    public ProdutoController(ProdutoModel produtoModel, ProdutoView produtoView) {
        this.produtoModel = produtoModel;
        this.produtoView = produtoView;
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
                produtoView.exibirMenu(); // Exibir o menu

                // Capturar a escolha do usuário
                opcao = produtoView.obterOpcao(scanner);

                // Processar a opção do usuário
                switch (opcao) {
                    case 1:
                        produtoView.exibirMensagem("Adicionar novo produto:");
                        System.out.println("\n");
                        produtoModel.adicionarProduto(conexao, scanner);
                        break;
                    case 2:
                        produtoView.exibirMensagem("\nVisualizando informações do produto:");
                        produtoModel.visualizarProduto(conexao, scanner);
                        break;
                    case 3:
                        produtoView.exibirMensagem("\nEditando informações do produto:");
                        produtoModel.editarProduto(conexao, scanner);
                        break;
                    case 4:
                        produtoView.exibirMensagem("\nListando todos os produtos:");
                        produtoModel.listarProdutos(conexao);
                        break;
                    case 5:
                        produtoView.exibirMensagem("\nRemovendo o produto:");
                        produtoModel.removerProduto(conexao, scanner);
                        break;
                    case 6:
                        produtoView.exibirMensagem("Saindo do menu produto...");
                        return;
                    default:
                        produtoView.exibirMensagem("Opção inválida. Por favor, escolha uma opção válida.");
                        break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao conectar ao banco de dados: " + ex.getMessage());
        }
    }
}

