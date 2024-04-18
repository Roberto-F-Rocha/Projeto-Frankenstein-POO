package com.example.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ClienteModel {
    
    public void inserirCliente(Connection conexao, Scanner scanner) {
        try {
            // Solicitar os dados do cliente via Scanner
            System.out.println("Digite o ID do cliente:");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            
            System.out.println("Digite o nome do cliente:");
            String nome = scanner.nextLine();
    
            System.out.println("Digite o email do cliente:");
            String email = scanner.nextLine();
    
            System.out.println("Digite o telefone do cliente:");
            String telefone = scanner.nextLine();
    
            System.out.println("Digite o endereço do cliente:");
            String endereco = scanner.nextLine();
    
            System.out.println("Digite a senha do cliente:");
            String senha = scanner.nextLine();

            // Preparar a declaração SQL para inserir um novo usuário na tabela
            String sql = "INSERT INTO usuario (id, nome, email, telefone, endereco, senha) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);

            // Definir os valores dos parâmetros da declaração SQL com os dados do cliente
            statement.setInt(1, id);
            statement.setString(2, nome);
            statement.setString(3, email);
            statement.setString(4, telefone);
            statement.setString(5, endereco);
            statement.setString(6, senha);

            // Executar a declaração SQL para inserir o usuário
            statement.executeUpdate();

            System.out.println("Usuário inserido com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao inserir o usuário no banco de dados: " + ex.getMessage());
        }
    }

    // Método para remover um cliente
    public void removerCliente(Connection conexao, Scanner scanner) {
        try {

            // Solicita o ID do cliente a ser removido
            System.out.println("Digite o ID do cliente a ser removido:");
            int id = scanner.nextInt();

            // Preparar a declaração SQL para excluir o cliente da tabela
            String sql = "DELETE FROM usuario WHERE id = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);

            // Definir o valor do parâmetro da declaração SQL com o id do cliente
            statement.setInt(1, id);

            // Executar a declaração SQL para excluir o cliente
            statement.executeUpdate();

            System.out.println("Cliente removido com sucesso.");

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao remover o cliente: " + ex.getMessage());
        }
    }

    // Método para visualizar informações de um cliente
    public void visualizarCliente(Connection conexao, Scanner scanner) {
        try {
            // Solicita o ID do cliente a ser visualizado
            System.out.println("Digite o ID do cliente a ser visualizado:");

            // Verifica se há um próximo token antes de chamar nextInt()
            if (scanner.hasNextInt()) {
                int id = scanner.nextInt();

                // Preparar a declaração SQL para selecionar informações do cliente
                String sql = "SELECT * FROM usuario WHERE id = ?";
                PreparedStatement statement = conexao.prepareStatement(sql);

                // Definir o valor do parâmetro da declaração SQL com o id do cliente
                statement.setInt(1, id);

                // Executar a declaração SQL e obter o resultado
                ResultSet resultSet = statement.executeQuery();

                // Exibir as informações do cliente
                if (resultSet.next()) {
                    System.out.println("ID do Cliente: " + resultSet.getInt("id"));
                    System.out.println("Nome: " + resultSet.getString("nome"));
                    System.out.println("Email: " + resultSet.getString("email"));
                    System.out.println("Telefone: " + resultSet.getString("telefone"));
                    System.out.println("Endereço: " + resultSet.getString("endereco"));
                } else {
                    System.out.println("Cliente não encontrado.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira um ID de cliente válido.");
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao visualizar o cliente: " + ex.getMessage());
        }
    }

    // Método para editar informações de um cliente
    public void editarCliente(Connection conexao, Scanner scanner) {
        try {

            // Solicita o ID do cliente a ser editado
            System.out.println("Digite o ID do cliente a ser editado:");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            // Solicita as novas informações do cliente via teclado
            System.out.println("Digite o novo nome do cliente:");
            String novoNome = scanner.nextLine();

            System.out.println("Digite o novo email do cliente:");
            String novoEmail = scanner.nextLine();

            System.out.println("Digite o novo telefone do cliente:");
            String novoTelefone = scanner.nextLine();

            System.out.println("Digite o novo endereço do cliente:");
            String novoEndereco = scanner.nextLine();

            // Preparar a declaração SQL para atualizar as informações do cliente
            String sql = "UPDATE usuario SET nome = ?, email = ?, telefone = ?, endereco = ? WHERE id = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);

            // Definir os valores dos parâmetros da declaração SQL com as novas informações do cliente
            statement.setString(1, novoNome);
            statement.setString(2, novoEmail);
            statement.setString(3, novoTelefone);
            statement.setString(4, novoEndereco);
            statement.setInt(5, id);

            // Executar a declaração SQL para atualizar as informações do cliente
            statement.executeUpdate();

            System.out.println("Informações do cliente atualizadas com sucesso.");

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao editar o cliente: " + ex.getMessage());
        }
    }

    // Método para listar todos os clientes
    public void listarClientes(Connection conexao) {
        try {
            // Preparar a declaração SQL para selecionar todos os clientes
            String sql = "SELECT * FROM usuario";
            PreparedStatement statement = conexao.prepareStatement(sql);

            // Executar a declaração SQL e obter o resultado
            ResultSet resultSet = statement.executeQuery();

            // Exibir informações de todos os clientes
            while (resultSet.next()) {
                System.out.println("ID do Cliente: " + resultSet.getInt("id"));
                System.out.println("Nome: " + resultSet.getString("nome"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Telefone: " + resultSet.getString("telefone"));
                System.out.println("Endereço: " + resultSet.getString("endereco"));
                System.out.println("----------------------");
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao listar os clientes: " + ex.getMessage());
        }
    }
}
