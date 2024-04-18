package com.example.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FaturaModel {

    public void adicionarFatura(Connection conexao, String TipoServico, int idClienteServico, Date dataFatura, float valorFatura,
            String metodoPagamento, String descricaoPagamento, boolean pagamentoFatura) {
        try {
            // Preparar a declaração SQL para inserir uma nova fatura na tabela
            String sql = "INSERT INTO fatura (idClienteServico, tipoServico, dataFatura, valorFatura, metodoPagamento, descricaoPagamento, pagamentoFatura) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);

            // Definir os valores dos parâmetros da declaração SQL com os dados da fatura
            statement.setInt(1, idClienteServico);
            statement.setString(2, TipoServico);
            statement.setDate(3, dataFatura);
            statement.setFloat(4, valorFatura);
            statement.setString(5, metodoPagamento);
            statement.setString(6, descricaoPagamento);
            statement.setBoolean(7, pagamentoFatura);

            // Executar a declaração SQL para inserir a fatura
            statement.executeUpdate();

            System.out.println("Fatura adicionada com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao adicionar a fatura: " + ex.getMessage());
        }
    }

    public void editarFatura(Connection conexao, Scanner scanner) {
        try {
            // Solicitar o ID da fatura a ser editada
            System.out.print("Digite o ID da fatura a ser editada: ");
            int idFatura = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer de entrada

            // Solicitar o novo valor da fatura
            System.out.print("Digite o novo valor da fatura: ");
            float novoValor = scanner.nextFloat();
            scanner.nextLine(); // Limpar o buffer de entrada

            // Solicitar o novo método de pagamento
            System.out.print("Digite o novo método de pagamento: ");
            String novoMetodoPagamento = scanner.nextLine();

            // Solicitar a nova descrição da fatura
            System.out.print("Digite a nova descrição da fatura: ");
            String novaDescricao = scanner.nextLine();

            // Solicitar se a fatura foi paga ou não
            String novoPagamento;
            do {
                System.out.print("A fatura foi paga? (Sim/Não): ");
                novoPagamento = scanner.nextLine().trim();
                if (!novoPagamento.equalsIgnoreCase("Sim") && !novoPagamento.equalsIgnoreCase("Não")) {
                    System.out.println("Entrada inválida. Por favor, digite 'Sim' ou 'Não'.");
                }
            } while (!novoPagamento.equalsIgnoreCase("Sim") && !novoPagamento.equalsIgnoreCase("Não"));

            // Preparar a declaração SQL para atualizar a fatura na tabela
            String sql = "UPDATE fatura SET valorFatura = ?, metodoPagamento = ?, descricaoPagamento = ?, pagamentoFatura = ? WHERE idFatura = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);

            // Definir os valores dos parâmetros da declaração SQL com os novos dados da fatura
            statement.setFloat(1, novoValor);
            statement.setString(2, novoMetodoPagamento);
            statement.setString(3, novaDescricao);
            statement.setString(4, novoPagamento); // Alterado para aceitar String
            statement.setInt(5, idFatura);

            // Executar a declaração SQL para atualizar a fatura
            statement.executeUpdate();

            System.out.println("Fatura atualizada com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao editar a fatura: " + ex.getMessage());
        }
    }

    public void removerFatura(Connection conexao, Scanner scanner) {
        try {

            // Solicitar o ID da fatura a ser removida
            System.out.print("Digite o ID da fatura a ser removida: ");
            int idFatura = scanner.nextInt();

            // Preparar a declaração SQL para remover a fatura da tabela
            String sql = "DELETE FROM fatura WHERE idFatura = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);

            // Definir o valor do parâmetro da declaração SQL com o ID da fatura
            statement.setInt(1, idFatura);

            // Executar a declaração SQL para remover a fatura
            statement.executeUpdate();

            System.out.println("Fatura removida com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao remover a fatura: " + ex.getMessage());
        }
    }

    public void visualizarFatura(Connection conexao, Scanner scanner) {
        try {
            // Solicitar o ID da fatura a ser visualizada
            System.out.print("Digite o ID da fatura a ser visualizada: ");
            int idFatura = scanner.nextInt();

            // Preparar a declaração SQL para selecionar a fatura na tabela
            String sql = "SELECT * FROM fatura WHERE idFatura = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);

            // Definir o valor do parâmetro da declaração SQL com o ID da fatura
            statement.setInt(1, idFatura);

            // Executar a declaração SQL e obter o resultado
            ResultSet resultSet = statement.executeQuery();

            // Exibir as informações da fatura
            if (resultSet.next()) {
                System.out.println("ID da Fatura: " + resultSet.getInt("idFatura"));
                System.out.println("ID do Cliente Serviço: " + resultSet.getInt("idClienteServico"));
                System.out.println("Método de Pagamento: " + resultSet.getString("tipoServico"));
                System.out.println("Data da Fatura: " + resultSet.getDate("dataFatura"));
                System.out.println("Valor da Fatura: " + resultSet.getFloat("valorFatura"));
                System.out.println("Método de Pagamento: " + resultSet.getString("metodoPagamento"));
                System.out.println("Descrição do Pagamento: " + resultSet.getString("descricaoPagamento"));
                System.out.println("Pagamento da Fatura: " + (resultSet.getBoolean("pagamentoFatura") ? "Sim" : "Não"));
            } else {
                System.out.println("Fatura não encontrada.");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao visualizar a fatura: " + ex.getMessage());
        }
    }

    public void listarFaturas(Connection conexao) {
        try {
            // Preparar a declaração SQL para selecionar todas as faturas na tabela
            String sql = "SELECT * FROM fatura";
            PreparedStatement statement = conexao.prepareStatement(sql);

            // Executar a declaração SQL e obter o resultado
            ResultSet resultSet = statement.executeQuery();

            // Exibir as informações de todas as faturas
            while (resultSet.next()) {
                System.out.println("ID da Fatura: " + resultSet.getInt("idFatura"));
                System.out.println("ID do Cliente Serviço: " + resultSet.getInt("idClienteServico"));
                System.out.println("Tipo de Serviço: " + resultSet.getString("tipoServico"));
                System.out.println("Método de Pagamento: " + resultSet.getString("metodoPagamento"));
                System.out.println("Data da Fatura: " + resultSet.getDate("dataFatura"));
                System.out.println("Valor da Fatura: " + resultSet.getFloat("valorFatura"));
                System.out.println("Método de Pagamento: " + resultSet.getString("metodoPagamento"));
                System.out.println("Descrição do Pagamento: " + resultSet.getString("descricaoPagamento"));
                System.out.println("Pagamento da Fatura: " + (resultSet.getBoolean("pagamentoFatura") ? "Sim" : "Não"));
                System.out.println("----------------------");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao listar as faturas: " + ex.getMessage());
        }
    }
}

