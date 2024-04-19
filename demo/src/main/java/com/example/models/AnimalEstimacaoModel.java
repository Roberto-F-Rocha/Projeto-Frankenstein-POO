package com.example.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AnimalEstimacaoModel {
    
    public void adicionarAnimal(Connection conexao, Scanner scanner) {

        try {
            // Preparar a declaração SQL para selecionar apenas o nome e o id dos clientes
            String sql = "SELECT id, nome FROM usuario";
            PreparedStatement statement = conexao.prepareStatement(sql);
    
            // Executar a declaração SQL e obter o resultado
            ResultSet resultSet = statement.executeQuery();
    
            // Exibir informações de todos os clientes
            System.out.println("Lista de clientes Disponiveis.");
            while (resultSet.next()) {
                System.out.println("ID do Cliente: " + resultSet.getInt("id"));
                System.out.println("Nome: " + resultSet.getString("nome"));
                System.out.println("----------------------");
                
            }
            System.out.println("\n");
    
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao listar os clientes: " + ex.getMessage());
        }
    
        try {
            // Solicitar os dados do animal de estimação via Scanner
            System.out.println("Adicionar novo animal de estimação:");
            System.out.println("Digite o ID do dono:");
            int idDono = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            
            System.out.println("Digite o nome do animal:");
            String nomePet = scanner.nextLine();
    
            System.out.println("Digite a raça do animal:");
            String racaPet = scanner.nextLine();
    
            System.out.println("Digite a idade do animal:");
            String idadePet = scanner.nextLine();
    
            System.out.println("Digite o tamanho do animal:");
            float tamanhoPet = scanner.nextFloat();
            scanner.nextLine(); // Consumir a quebra de linha
    
            System.out.println("Digite o peso do animal:");
            float pesoPet = scanner.nextFloat();
            scanner.nextLine(); // Consumir a quebra de linha
    
            // Preparar a declaração SQL para inserir um novo animal de estimação na tabela
            String sql = "INSERT INTO animaldeestimacao (idDono, nomePet, racaPet, idadePet, tamanhoPet, pesoPet) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);
    
            // Definir os valores dos parâmetros da declaração SQL com os dados do animal de estimação
            statement.setInt(1, idDono);
            statement.setString(2, nomePet);
            statement.setString(3, racaPet);
            statement.setString(4, idadePet);
            statement.setFloat(5, tamanhoPet);
            statement.setFloat(6, pesoPet);
    
            // Executar a declaração SQL para inserir o animal de estimação
            statement.executeUpdate();
    
            System.out.println("Animal de estimação adicionado com sucesso.");
            System.out.println("\n");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao adicionar o animal de estimação: " + ex.getMessage());
        }
    }

    public void removerAnimal(Connection conexao, Scanner scanner) {
        try {
            // Solicitar o ID do animal de estimação a ser removido
            System.out.println("Digite o ID do animal de estimação a ser removido:");
            int idAnimal = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            
            // Preparar a declaração SQL para excluir o animal de estimação da tabela
            String sql = "DELETE FROM animaldeestimacao WHERE idAnimal = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
    
            // Definir o valor do parâmetro da declaração SQL com o ID do animal de estimação
            statement.setInt(1, idAnimal);
    
            // Executar a declaração SQL para excluir o animal de estimação
            statement.executeUpdate();
    
            System.out.println("Animal de estimação removido com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao remover o animal de estimação: " + ex.getMessage());
        }
    }

    public void visualizarAnimais(Connection conexao, Scanner scanner) {
        try {
            // Solicitar o ID do dono dos animais de estimação a serem visualizados
            System.out.println("Digite o ID do dono dos animais de estimação a serem visualizados:");
            int idDono = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            
            // Preparar a declaração SQL para selecionar o nome do dono
            String sqlDono = "SELECT nome FROM usuario WHERE id = ?";
            PreparedStatement statementDono = conexao.prepareStatement(sqlDono);
            statementDono.setInt(1, idDono);
            ResultSet resultSetDono = statementDono.executeQuery();
            
            // Verificar se o dono existe e exibir o nome
            if (resultSetDono.next()) {
                String nomeDono = resultSetDono.getString("nome");
                System.out.println("Animais de estimação do dono " + nomeDono + ":");
                
                // Preparar a declaração SQL para selecionar informações dos animais de estimação do dono
                String sql = "SELECT * FROM animaldeestimacao WHERE idDono = ?";
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setInt(1, idDono);
                ResultSet resultSet = statement.executeQuery();
        
                // Exibir as informações dos animais de estimação do dono
                while (resultSet.next()) {
                    System.out.println("ID do Animal: " + resultSet.getInt("idAnimal"));
                    System.out.println("ID do Dono: " + resultSet.getInt("idDono"));
                    System.out.println("Nome: " + resultSet.getString("nomePet"));
                    System.out.println("Raça: " + resultSet.getString("racaPet"));
                    System.out.println("Idade: " + resultSet.getString("idadePet"));
                    System.out.println("Tamanho: " + resultSet.getFloat("tamanhoPet"));
                    System.out.println("Peso: " + resultSet.getFloat("pesoPet"));
                    System.out.println("----------------------");
                }
            } else {
                System.out.println("Não foi possível encontrar o dono com o ID fornecido.");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao visualizar os animais de estimação: " + ex.getMessage());
        }
    }    

    public void editarAnimal(Connection conexao, Scanner scanner) {
        try {
            // Solicitar o ID do animal de estimação a ser editado
            System.out.println("Digite o ID do animal de estimação a ser editado:");
            int idAnimal = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            
            // Solicitar as novas informações do animal de estimação via teclado
            System.out.println("Digite o novo nome do animal:");
            String novoNome = scanner.nextLine();
    
            System.out.println("Digite a nova raça do animal:");
            String novaRaca = scanner.nextLine();
    
            System.out.println("Digite a nova idade do animal:");
            String novaIdade = scanner.nextLine();
    
            System.out.println("Digite o novo tamanho do animal:");
            float novoTamanho = scanner.nextFloat();
            scanner.nextLine(); // Consumir a quebra de linha
    
            System.out.println("Digite o novo peso do animal:");
            float novoPeso = scanner.nextFloat();
            scanner.nextLine(); // Consumir a quebra de linha
    
            // Preparar a declaração SQL para atualizar as informações do animal de estimação
            String sql = "UPDATE animaldeestimacao SET nomePet = ?, racaPet = ?, idadePet = ?, tamanhoPet = ?, pesoPet = ? WHERE idAnimal = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
    
            // Definir os valores dos parâmetros da declaração SQL com as novas informações do animal de estimação
            statement.setString(1, novoNome);
            statement.setString(2, novaRaca);
            statement.setString(3, novaIdade);
            statement.setFloat(4, novoTamanho);
            statement.setFloat(5, novoPeso);
            statement.setInt(6, idAnimal);
    
            // Executar a declaração SQL para atualizar as informações do animal de estimação
            statement.executeUpdate();
    
            System.out.println("Informações do animal de estimação atualizadas com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao editar o animal de estimação: " + ex.getMessage());
        }
    }

    public void listarAnimais(Connection conexao) {
        try {
            // Preparar a declaração SQL para selecionar todos os animais de estimação com o nome do dono
            String sql = "SELECT a.idAnimal, u.nome AS nomeDono, a.nomePet, a.racaPet, a.idadePet, a.tamanhoPet, a.pesoPet " +
                         "FROM animaldeestimacao a " +
                         "INNER JOIN usuario u ON a.idDono = u.id";
            PreparedStatement statement = conexao.prepareStatement(sql);
    
            // Executar a declaração SQL e obter o resultado
            ResultSet resultSet = statement.executeQuery();
    
            // Exibir informações de todos os animais de estimação
            while (resultSet.next()) {
                System.out.println("ID do Animal: " + resultSet.getInt("idAnimal"));
                System.out.println("Nome do Dono: " + resultSet.getString("nomeDono"));
                System.out.println("Nome: " + resultSet.getString("nomePet"));
                System.out.println("Raça: " + resultSet.getString("racaPet"));
                System.out.println("Idade: " + resultSet.getString("idadePet"));
                System.out.println("Tamanho: " + resultSet.getFloat("tamanhoPet"));
                System.out.println("Peso: " + resultSet.getFloat("pesoPet"));
                System.out.println("----------------------");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao listar os animais de estimação: " + ex.getMessage());
        }
    }
    
}



