package com.example.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ServicoModel {

    public void adicionarServico(Connection conexao, Scanner scanner) {
        try {
            // Solicitar os dados do serviço via Scanner
            System.out.println("Digite o ID do cliente:");
            int idCliente = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            
            System.out.println("Digite o tipo de serviço:");
            String tipoServico = scanner.nextLine();
    
            // Instanciar o modelo de fatura para adicionar a fatura relacionada ao serviço
            FaturaModel faturaModel = new FaturaModel();
            faturaModel.adicionarFatura(conexao, tipoServico, idCliente, scanner);
    
            // Preparar a declaração SQL para inserir um novo serviço na tabela
            String sql = "INSERT INTO servico (idClienteServico, tipoServico) VALUES (?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);
    
            // Definir os valores dos parâmetros da declaração SQL com os dados do serviço
            statement.setInt(1, idCliente);
            statement.setString(2, tipoServico);
    
            // Executar a declaração SQL para inserir o serviço
            statement.executeUpdate();
    
            System.out.println("Serviço adicionado com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao adicionar o serviço: " + ex.getMessage());
        }
    }

    public void removerServico(Connection conexao, Scanner scanner) {
        try {
            // Solicitar o ID do serviço a ser removido
            System.out.println("Digite o ID do serviço a ser removido:");
            int idServico = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            
            // Preparar a declaração SQL para excluir o serviço da tabela
            String sql = "DELETE FROM servico WHERE idServico = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
    
            // Definir o valor do parâmetro da declaração SQL com o ID do serviço
            statement.setInt(1, idServico);
    
            // Executar a declaração SQL para excluir o serviço
            statement.executeUpdate();
    
            System.out.println("Serviço removido com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao remover o serviço: " + ex.getMessage());
        }
    }

    public void visualizarServico(Connection conexao, Scanner scanner) {
        try {
            // Solicitar ao usuário o ID do cliente para buscar os serviços
            System.out.println("Digite o ID do cliente para visualizar os serviços:");
            int idCliente = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após o próximoInt()
    
            // Preparar a declaração SQL para selecionar os serviços associados ao ID do cliente fornecido
            String sql = "SELECT s.idServico, u.nome AS nomeCliente, s.tipoServico " +
                         "FROM servico s " +
                         "INNER JOIN usuario u ON s.idClienteServico = u.id " +
                         "WHERE s.idClienteServico = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, idCliente);
    
            // Executar a declaração SQL e obter o resultado
            ResultSet resultSet = statement.executeQuery();
    
            // Exibir informações de todos os serviços associados ao ID do cliente
            while (resultSet.next()) {
                System.out.println("ID do Serviço: " + resultSet.getInt("idServico"));
                System.out.println("Nome do Cliente: " + resultSet.getString("nomeCliente"));
                System.out.println("Tipo de Serviço: " + resultSet.getString("tipoServico"));
                System.out.println("----------------------");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao visualizar os serviços: " + ex.getMessage());
        }
    }    

    public void editarServico(Connection conexao, Scanner scanner) {
        try {
            // Solicitar o ID do serviço a ser editado
            System.out.println("Digite o ID do serviço a ser editado:");
            int idServico = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            
            // Solicitar as novas informações do serviço via teclado
            System.out.println("Digite o novo tipo de serviço:");
            String novoTipoServico = scanner.nextLine();
    
            // Preparar a declaração SQL para atualizar as informações do serviço
            String sql = "UPDATE servico SET tipoServico = ? WHERE idServico = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
    
            // Definir os valores dos parâmetros da declaração SQL com as novas informações do serviço
            statement.setString(1, novoTipoServico);
            statement.setInt(2, idServico);
    
            // Executar a declaração SQL para atualizar as informações do serviço
            statement.executeUpdate();
    
            System.out.println("Informações do serviço atualizadas com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao editar o serviço: " + ex.getMessage());
        }
    }

    public void listarServicos(Connection conexao) {
        try {
            // Preparar a declaração SQL para selecionar todos os serviços com o nome do cliente
            String sql = "SELECT s.idServico, u.nome AS nomeCliente, s.tipoServico " +
                         "FROM servico s " +
                         "INNER JOIN usuario u ON s.idClienteServico = u.id";
            PreparedStatement statement = conexao.prepareStatement(sql);
    
            // Executar a declaração SQL e obter o resultado
            ResultSet resultSet = statement.executeQuery();
    
            // Exibir informações de todos os serviços com o nome do cliente
            while (resultSet.next()) {
                System.out.println("ID do Serviço: " + resultSet.getInt("idServico"));
                System.out.println("Nome do Cliente: " + resultSet.getString("nomeCliente"));
                System.out.println("Tipo de Serviço: " + resultSet.getString("tipoServico"));
                System.out.println("----------------------");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao listar os serviços: " + ex.getMessage());
        }
    }
    
}
