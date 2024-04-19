package com.example.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AvaliacaoModel {

    public void adicionarAvaliacao(Connection conexao, Scanner scanner) {
        try {
            // Solicitar os dados da avaliação via Scanner
            System.out.println("Digite o ID do usuário:");
            int idUsuario = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            System.out.println("Digite a nota da avaliação:");
            int nota = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            System.out.println("Digite o comentário da avaliação:");
            String comentario = scanner.nextLine();

            // Obter a data atual para a avaliação
            Date dataAvaliacao = new Date(System.currentTimeMillis());

            // Preparar a declaração SQL para inserir uma nova avaliação na tabela
            String sql = "INSERT INTO avaliacao (idUsuario, dataAvaliacao, notaAvaliacao, descricaoAvaliacao) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);

            // Definir os valores dos parâmetros da declaração SQL com os dados da avaliação
            statement.setInt(1, idUsuario);
            statement.setDate(2, dataAvaliacao);
            statement.setInt(3, nota);
            statement.setString(4, comentario);

            // Executar a declaração SQL para inserir a avaliação
            statement.executeUpdate();

            System.out.println("Avaliação adicionada com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao adicionar a avaliação: " + ex.getMessage());
        }
    }

    public void removerAvaliacao(Connection conexao, Scanner scanner) {
        try {
            // Solicitar o ID da avaliação a ser removida
            System.out.println("Digite o ID da avaliação a ser removida:");
            int idAvaliacao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            // Preparar a declaração SQL para excluir a avaliação da tabela
            String sql = "DELETE FROM avaliacao WHERE idAvaliacao = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);

            // Definir o valor do parâmetro da declaração SQL com o ID da avaliação
            statement.setInt(1, idAvaliacao);

            // Executar a declaração SQL para excluir a avaliação
            statement.executeUpdate();

            System.out.println("Avaliação removida com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao remover a avaliação: " + ex.getMessage());
        }
    }

    public void listarAvaliacao(Connection conexao, Scanner scanner) {
        try {
            // Solicitar o ID do usuário para buscar as avaliações
            System.out.println("Digite o ID do usuário para visualizar as avaliações:");
            int idUsuario = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após o próximoInt()
    
            // Preparar a declaração SQL para selecionar as avaliações associadas ao ID do usuário fornecido
            String sql = "SELECT a.idAvaliacao, a.dataAvaliacao, a.notaAvaliacao, a.descricaoAvaliacao, u.nome AS nomeUsuario " +
                         "FROM avaliacao a " +
                         "INNER JOIN usuario u ON a.idUsuario = u.id " +
                         "WHERE a.idUsuario = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, idUsuario);
    
            // Executar a declaração SQL e obter o resultado
            ResultSet resultSet = statement.executeQuery();
    
            // Exibir informações de todas as avaliações associadas ao ID do usuário
            while (resultSet.next()) {
                System.out.println("ID da Avaliação: " + resultSet.getInt("idAvaliacao"));
                System.out.println("Nome do Usuário: " + resultSet.getString("nomeUsuario"));
                System.out.println("Data da Avaliação: " + resultSet.getDate("dataAvaliacao"));
                System.out.println("Nota: " + resultSet.getInt("notaAvaliacao"));
                System.out.println("Comentário: " + resultSet.getString("descricaoAvaliacao"));
                System.out.println("----------------------");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao listar as avaliações: " + ex.getMessage());
        }
    }    

    public void editarAvaliacao(Connection conexao, Scanner scanner) {
        try {
            // Solicitar o ID da avaliação a ser editada
            System.out.println("Digite o ID da avaliação a ser editada:");
            int idAvaliacao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            // Solicitar as novas informações da avaliação via teclado
            System.out.println("Digite a nova nota da avaliação:");
            int novaNota = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            System.out.println("Digite o novo comentário da avaliação:");
            String novoComentario = scanner.nextLine();

            // Preparar a declaração SQL para atualizar as informações da avaliação
            String sql = "UPDATE avaliacao SET notaAvaliacao = ?, descricaoAvaliacao = ? WHERE idAvaliacao = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);

            // Definir os valores dos parâmetros da declaração SQL com as novas informações da avaliação
            statement.setInt(1, novaNota);
            statement.setString(2, novoComentario);
            statement.setInt(3, idAvaliacao);

            // Executar a declaração SQL para atualizar as informações da avaliação
            statement.executeUpdate();

            System.out.println("Informações da avaliação atualizadas com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao editar a avaliação: " + ex.getMessage());
        }
    }

    public void listarAvaliacoes(Connection conexao) {
        try {
            // Preparar a declaração SQL para selecionar todas as avaliações
            String sql = "SELECT a.idAvaliacao, u.nome, a.dataAvaliacao, a.notaAvaliacao, a.descricaoAvaliacao " +
                         "FROM avaliacao a " +
                         "INNER JOIN usuario u ON a.idUsuario = u.id";
            PreparedStatement statement = conexao.prepareStatement(sql);
    
            // Executar a declaração SQL e obter o resultado
            ResultSet resultSet = statement.executeQuery();
    
            // Exibir as informações de todas as avaliações
            while (resultSet.next()) {
                System.out.println("ID da Avaliação: " + resultSet.getInt("idAvaliacao"));
                System.out.println("Nome do Usuário: " + resultSet.getString("nome"));
                System.out.println("Data da Avaliação: " + resultSet.getDate("dataAvaliacao"));
                System.out.println("Nota: " + resultSet.getInt("notaAvaliacao"));
                System.out.println("Comentário: " + resultSet.getString("descricaoAvaliacao"));
                System.out.println("----------------------");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao exibir as avaliações: " + ex.getMessage());
        }
    }
    
    
}

