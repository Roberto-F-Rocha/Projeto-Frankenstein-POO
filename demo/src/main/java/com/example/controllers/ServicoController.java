package com.example.controllers;

import com.example.models.ServicoModel;
import com.example.view.ServicoView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ServicoController {
    private ServicoModel servicoModel;
    private ServicoView servicoView;

    public ServicoController(ServicoModel servicoModel, ServicoView servicoView) {
        this.servicoModel = servicoModel;
        this.servicoView = servicoView;
    }

    public void iniciar() {
        try {
            // Criando uma instância de ConexaoController
            ConexaoController conexaoController = new ConexaoController();

            // Obtendo a conexão
            Connection conexao = conexaoController.conectar();

            Scanner scanner = new Scanner(System.in,"CP850");;
            int opcao;

            // Loop para exibir o menu e processar as opções do usuário
            while (true) {
                servicoView.exibirMenu(); // Exibir o menu

                // Capturar a escolha do usuário
                opcao = servicoView.obterOpcao(scanner);

                // Processar a opção do usuário
                switch (opcao) {
                    case 1:
                        servicoView.exibirMensagem("Adicionar novo serviço:");
                        System.out.println("\n");
                        servicoModel.adicionarServico(conexao, scanner);
                        break;
                    case 2:
                        servicoView.exibirMensagem("\nVisualizando informações do serviço:");
                        servicoModel.visualizarServico(conexao, scanner);
                        break;
                    case 3:
                        servicoView.exibirMensagem("\nEditando informações do serviço:");
                        servicoModel.editarServico(conexao, scanner);
                        break;
                    case 4:
                        servicoView.exibirMensagem("\nListando todos os serviços:");
                        servicoModel.listarServicos(conexao);
                        break;
                    case 5:
                        servicoView.exibirMensagem("\nRemovendo o serviço:");
                        servicoModel.removerServico(conexao, scanner);
                        break;
                    case 6:
                        servicoView.exibirMensagem("Saindo do menu de serviço...");
                        return;
                    default:
                        servicoView.exibirMensagem("Opção inválida. Por favor, escolha uma opção válida.");
                        break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao conectar ao banco de dados: " + ex.getMessage());
        }
    }
}
