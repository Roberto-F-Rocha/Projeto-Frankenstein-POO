package com.example.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProdutoModel {

    public void adicionarProduto(Connection conexao, Scanner scanner) {
        try {
            // Solicitar os dados do produto via Scanner
            scanner.nextLine();
            System.out.println("Digite o nome do produto:");
            String nomeProduto = scanner.nextLine(); // Capturar o nome do produto
    
            System.out.println("Digite a descrição do produto:");
            String descricaoProduto = scanner.nextLine();
    
            System.out.println("Digite o preço do produto:");
            float precoProduto = scanner.nextFloat();
            scanner.nextLine(); // Consumir a quebra de linha
            
            System.out.println("Digite a quantidade de itens do produto:");
            int quantidadeItens = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
    
            // Preparar a declaração SQL para inserir um novo produto na tabela
            String sql = "INSERT INTO produto (nomeProduto, descricaoProduto, precoProduto, quantidadeItens) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);
    
            // Definir os valores dos parâmetros da declaração SQL com os dados do produto
            statement.setString(1, nomeProduto);
            statement.setString(2, descricaoProduto);
            statement.setFloat(3, precoProduto);
            statement.setInt(4, quantidadeItens);
    
            // Executar a declaração SQL para inserir o produto
            statement.executeUpdate();
    
            System.out.println("Produto adicionado com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao adicionar o produto: " + ex.getMessage());
        }
    }
    
    public void removerProduto(Connection conexao, Scanner scanner) {
        try {
            // Solicitar o ID do produto a ser removido
            System.out.println("Digite o ID do produto a ser removido:");
            int idProduto = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            // Preparar a declaração SQL para excluir o produto da tabela
            String sql = "DELETE FROM produto WHERE idProduto = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);

            // Definir o valor do parâmetro da declaração SQL com o ID do produto
            statement.setInt(1, idProduto);

            // Executar a declaração SQL para excluir o produto
            statement.executeUpdate();

            System.out.println("Produto removido com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao remover o produto: " + ex.getMessage());
        }
    }

    public void visualizarProduto(Connection conexao, Scanner scanner) {
        try {
            // Solicitar o ID do produto a ser visualizado
            System.out.println("Digite o ID do produto a ser visualizado:");
            int idProduto = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            // Preparar a declaração SQL para selecionar informações do produto
            String sql = "SELECT * FROM produto WHERE idProduto = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);

            // Definir o valor do parâmetro da declaração SQL com o ID do produto
            statement.setInt(1, idProduto);

            // Executar a declaração SQL e obter o resultado
            ResultSet resultSet = statement.executeQuery();

            // Exibir as informações do produto
            if (resultSet.next()) {
                System.out.println("ID do Produto: " + resultSet.getInt("idProduto"));
                System.out.println("Nome: " + resultSet.getString("nomeProduto"));
                System.out.println("Descrição: " + resultSet.getString("descricaoProduto"));
                System.out.println("Preço: " + resultSet.getFloat("precoProduto"));
                System.out.println("Quantidade de Itens: " + resultSet.getInt("quantidadeItens"));
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao visualizar o produto: " + ex.getMessage());
        }
    }

    public void editarProduto(Connection conexao, Scanner scanner) {
        try {
            // Solicitar o ID do produto a ser editado
            System.out.println("Digite o ID do produto a ser editado:");
            int idProduto = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
    
            // Solicitar as novas informações do produto via teclado
            System.out.println("Digite o novo nome do produto:");
            String novoNome = scanner.nextLine();
    
            System.out.println("Digite a nova descrição do produto:");
            String novaDescricao = scanner.nextLine();
    
            System.out.println("Digite o novo preço do produto:");
            float novoPreco = scanner.nextFloat();
            scanner.nextLine(); // Consumir a quebra de linha
            
            System.out.println("Digite a nova quantidade de itens do produto:");
            int novaQuantidade = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
    
            // Preparar a declaração SQL para atualizar as informações do produto
            String sql = "UPDATE produto SET nomeProduto = ?, descricaoProduto = ?, precoProduto = ?, QuantidadeDeItens = ? WHERE idProduto = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
    
            // Definir os valores dos parâmetros da declaração SQL com as novas informações do produto
            statement.setString(1, novoNome);
            statement.setString(2, novaDescricao);
            statement.setFloat(3, novoPreco);
            statement.setInt(4, novaQuantidade);
            statement.setInt(5, idProduto);
    
            // Executar a declaração SQL para atualizar as informações do produto
            statement.executeUpdate();
    
            System.out.println("Informações do produto atualizadas com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao editar o produto: " + ex.getMessage());
        }
    }    

    public void listarProdutos(Connection conexao) {
        try {
            // Preparar a declaração SQL para selecionar todos os produtos
            String sql = "SELECT * FROM produto";
            PreparedStatement statement = conexao.prepareStatement(sql);

            // Executar a declaração SQL e obter o resultado
            ResultSet resultSet = statement.executeQuery();

            // Exibir informações de todos os produtos
            while (resultSet.next()) {
                System.out.println("ID do Produto: " + resultSet.getInt("idProduto"));
                System.out.println("Nome: " + resultSet.getString("nomeProduto"));
                System.out.println("Descrição: " + resultSet.getString("descricaoProduto"));
                System.out.println("Preço: " + resultSet.getFloat("precoProduto"));
                System.out.println("Quantidade de Itens: " + resultSet.getInt("quantidadeItens"));
                System.out.println("----------------------");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao listar os produtos: " + ex.getMessage());
        }
    }
}

