package com.example.controllers;

import com.example.models.CompraModel;
import com.example.view.CompraView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class CompraController {
    private CompraModel compraModel;
    private CompraView compraView;

    public CompraController(CompraModel compraModel, CompraView compraView) {
        this.compraModel = compraModel;
        this.compraView = compraView;
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
                compraView.exibirMenu(); // Exibir o menu

                // Capturar a escolha do usuário
                opcao = compraView.obterOpcao(scanner);

                // Processar a opção do usuário
                switch (opcao) {
                    case 1:
                        compraView.exibirMensagem("Adicionar nova compra:");
                        System.out.println("\n");
                        compraModel.adicionarCompra(conexao, scanner);
                        break;
                    case 2:
                        compraView.exibirMensagem("\nVisualizando informações da compra:");
                        compraModel.visualizarCompras(conexao, scanner);
                        break;
                    case 3:
                        compraView.exibirMensagem("\nEditando informações da compra:");
                        compraModel.editarCompra(conexao, scanner);
                        break;
                    case 4:
                        compraView.exibirMensagem("\nListando todas as compras:");
                        compraModel.listarCompras(conexao);
                        break;
                    case 5:
                        compraView.exibirMensagem("\nRemovendo a compra:");
                        compraModel.removerCompra(conexao, scanner);
                        break;
                    case 6:
                        compraView.exibirMensagem("Saindo do menu de compras...");
                        return;
                    default:
                        compraView.exibirMensagem("Opção inválida. Por favor, escolha uma opção válida.");
                        break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao conectar ao banco de dados: " + ex.getMessage());
        }
    }
}

