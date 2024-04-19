package com.example.controllers;

import com.example.models.FaturaModel;
import com.example.view.FaturaView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class FaturaController {
    private FaturaModel faturaModel;
    private FaturaView faturaView;

    public FaturaController(FaturaModel faturaModel, FaturaView faturaView) {
        this.faturaModel = faturaModel;
        this.faturaView = faturaView;
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
                faturaView.exibirMenu(); // Exibir o menu

                // Capturar a escolha do usuário
                opcao = faturaView.obterOpcao(scanner);

                // Processar a opção do usuário
                switch (opcao) {
                    case 1:
                        faturaView.exibirMensagem("\nVisualizando informações da fatura:");
                        faturaModel.visualizarFatura(conexao, scanner);
                        break;
                    case 2:
                        faturaView.exibirMensagem("\nAtualizando status:");
                        faturaModel.atualizarStatusDePagamento(conexao, scanner);
                        break;
                    case 3:
                        faturaView.exibirMensagem("\nEditando informações da fatura:");
                        faturaModel.editarFatura(conexao, scanner);
                        break;
                    case 4:
                        faturaView.exibirMensagem("\nListando todas as faturas:");
                        faturaModel.listarFaturas(conexao);
                        break;
                    case 5:
                        faturaView.exibirMensagem("\nRemovendo a fatura:");
                        faturaModel.removerFatura(conexao, scanner);
                        break;
                    case 6:
                        faturaView.exibirMensagem("Saindo do menu de fatura...");
                        return;
                    default:
                        faturaView.exibirMensagem("Opção inválida. Por favor, escolha uma opção válida.");
                        break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao conectar ao banco de dados: " + ex.getMessage());
        }
    }
}
