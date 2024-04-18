package com.example.controllers;

import com.example.models.ClienteModel;
import com.example.view.ClienteView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ClienteController {
    private ClienteModel clienteModel;
    private ClienteView clienteView;

    public ClienteController(ClienteModel clienteModel, ClienteView clienteView) {
        this.clienteModel = clienteModel;
        this.clienteView = clienteView;
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
                clienteView.exibirMenu(); // Exibir o menu

                // Capturar a escolha do usuário
                opcao = clienteView.obterOpcao(scanner);

                // Processar a opção do usuário
                switch (opcao) {
                    case 1:
                        clienteView.exibirMensagem("Adicionar novo cliente:");
                        System.out.println("\n");
                        clienteModel.inserirCliente(conexao, scanner);
                        break;
                    case 2:
                        clienteView.exibirMensagem("\nVisualizando informações do cliente:");
                        clienteModel.visualizarCliente(conexao, scanner);
                        break;
                    case 3:
                        clienteView.exibirMensagem("\nEditando informações do cliente:");
                        clienteModel.editarCliente(conexao, scanner);
                        break;
                    case 4:
                        clienteView.exibirMensagem("\nListando todos os clientes:");
                        clienteModel.listarClientes(conexao);
                        break;
                    case 5:
                        clienteView.exibirMensagem("\nRemovendo o cliente:");
                        clienteModel.removerCliente(conexao, scanner);
                        break;
                    case 6:
                        clienteView.exibirMensagem("Saindo do menu cliente...");
                        return;
                    default:
                        clienteView.exibirMensagem("Opção inválida. Por favor, escolha uma opção válida.");
                        break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao conectar ao banco de dados: " + ex.getMessage());
        }
    }
}
