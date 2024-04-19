package com.example.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Date;
import java.time.LocalDate;

public class CompraModel {

    public void adicionarCompra(Connection conexao, Scanner scanner) {
        try {
            // Solicitar os dados da compra via Scanner
            System.out.println("Digite o ID do cliente:");
            int idCliente = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
    
            System.out.println("Digite a quantidade de itens a ser comprada:");
            int quantidadeItens = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
    
            System.out.println("Digite o nome do produto:");
            String nomeProduto = scanner.nextLine();
    
            // Obter a data atual do sistema
            Date dataCompra = Date.valueOf(LocalDate.now());
    
            // Verificar se o produto existe na tabela produto e obter seu preço e quantidade atual
            String sqlVerificarProduto = "SELECT precoProduto, quantidadeItens FROM produto WHERE nomeProduto = ?";
            PreparedStatement statementVerificarProduto = conexao.prepareStatement(sqlVerificarProduto);
            statementVerificarProduto.setString(1, nomeProduto);
            ResultSet resultSetProduto = statementVerificarProduto.executeQuery();
    
            // Verificar se o produto existe na tabela
            if (resultSetProduto.next()) {
                // Obter o preço e a quantidade atual do produto
                double precoProduto = resultSetProduto.getDouble("precoProduto");
                int quantidadeAtual = resultSetProduto.getInt("quantidadeItens");
    
                // Verificar se a quantidade desejada está disponível
                if (quantidadeAtual >= quantidadeItens) {
                    // Calcular o valor total da compra
                    double valorTotal = precoProduto * quantidadeItens;
                    System.out.println("\nValor total da compra: " + valorTotal);
    
                    // Solicitar a confirmação da compra
                    System.out.println("\nDeseja confirmar a compra?");
                    System.out.println("1. Sim");
                    System.out.println("2. Não");
                    System.out.print("Escolha uma opção: ");
                    int opcao = scanner.nextInt();
    
                    if (opcao == 1) {
                        // Atualizar a quantidade de itens disponíveis na tabela produto
                        int novaQuantidade = quantidadeAtual - quantidadeItens;
                        String sqlAtualizarQuantidade = "UPDATE produto SET quantidadeItens = ? WHERE nomeProduto = ?";
                        PreparedStatement statementAtualizarQuantidade = conexao.prepareStatement(sqlAtualizarQuantidade);
                        statementAtualizarQuantidade.setInt(1, novaQuantidade);
                        statementAtualizarQuantidade.setString(2, nomeProduto);
                        statementAtualizarQuantidade.executeUpdate();
    
                        // Adicionar a fatura relacionada à compra
                        FaturaModel faturaModel = new FaturaModel();
                        faturaModel.adicionarFaturaCompra(conexao, nomeProduto, idCliente, scanner, valorTotal);
    
                        // Preparar a declaração SQL para inserir uma nova compra na tabela
                        String sql = "INSERT INTO compra (idClienteCompra, nomeProduto, quantidadeItens, valorTotal, dataCompra) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement statement = conexao.prepareStatement(sql);
    
                        // Definir os valores dos parâmetros da declaração SQL com os dados da compra
                        statement.setInt(1, idCliente);
                        statement.setString(2, nomeProduto);
                        statement.setInt(3, quantidadeItens);
                        statement.setDouble(4, valorTotal);
                        statement.setDate(5, dataCompra); // Adicionando a data da compra
    
                        // Executar a declaração SQL para inserir a compra
                        statement.executeUpdate();
    
                        System.out.println("Compra adicionada com sucesso.");
                    } else if (opcao == 2) {
                        System.out.println("Compra cancelada.");
                    } else {
                        System.out.println("Opção inválida. A compra será cancelada.");
                    }
                } else {
                    System.out.println("Quantidade insuficiente de itens disponíveis.");
                }
            } else {
                System.out.println("O produto não existe na base de dados.");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao adicionar a compra: " + ex.getMessage());
        }
    }

    public void removerCompra(Connection conexao, Scanner scanner) {
        try {
            // Solicitar o ID da compra a ser removida
            System.out.println("Digite o ID da compra a ser removida:");
            int idCompra = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            
            // Preparar a declaração SQL para excluir a compra da tabela
            String sql = "DELETE FROM compra WHERE idCompra = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
    
            // Definir o valor do parâmetro da declaração SQL com o ID da compra
            statement.setInt(1, idCompra);
    
            // Executar a declaração SQL para excluir a compra
            statement.executeUpdate();
    
            System.out.println("Compra removida com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao remover a compra: " + ex.getMessage());
        }
    }

    public void visualizarCompras(Connection conexao, Scanner scanner) {
        try {
            // Solicitar ao usuário o ID do cliente para buscar as compras
            System.out.println("Digite o ID do cliente para visualizar as compras:");
            int idCliente = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após o próximoInt()
    
            // Preparar a declaração SQL para selecionar as compras associadas ao ID do cliente fornecido
            String sql = "SELECT c.idCompra, u.nome AS nomeCliente, c.nomeProduto, c.quantidadeItens, c.valorTotal, c.dataCompra " +
                         "FROM compra c " +
                         "INNER JOIN usuario u ON c.idClienteCompra = u.id " +
                         "WHERE c.idClienteCompra = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, idCliente);
    
            // Executar a declaração SQL e obter o resultado
            ResultSet resultSet = statement.executeQuery();
    
            // Exibir informações de todas as compras associadas ao ID do cliente
            while (resultSet.next()) {
                System.out.println("ID da Compra: " + resultSet.getInt("idCompra"));
                System.out.println("Nome do Cliente: " + resultSet.getString("nomeCliente"));
                System.out.println("Nome do Produto: " + resultSet.getString("nomeProduto"));
                System.out.println("Quantidade de Itens: " + resultSet.getInt("quantidadeItens"));
                System.out.println("Valor Total: " + resultSet.getDouble("valorTotal"));
                System.out.println("Data da Compra: " + resultSet.getDate("dataCompra"));
                System.out.println("----------------------");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao visualizar as compras: " + ex.getMessage());
        }
    }
    
    

    public void editarCompra(Connection conexao, Scanner scanner) {
        try {
            // Solicitar o ID da compra a ser editada
            System.out.println("Digite o ID da compra a ser editada:");
            int idCompra = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            
            // Solicitar as novas informações da compra via teclado
            System.out.println("Digite o novo nome do produto:");
            String novoNomeProduto = scanner.nextLine();
    
            // Preparar a declaração SQL para atualizar as informações da compra
            String sql = "UPDATE compra SET nomeProduto = ? WHERE idCompra = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
    
            // Definir os valores dos parâmetros da declaração SQL com as novas informações da compra
            statement.setString(1, novoNomeProduto);
            statement.setInt(2, idCompra);
    
            // Executar a declaração SQL para atualizar as informações da compra
            statement.executeUpdate();
    
            System.out.println("Informações da compra atualizadas com sucesso.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao editar a compra: " + ex.getMessage());
        }
    }

    public void listarCompras(Connection conexao) {
        try {
            // Preparar a declaração SQL para selecionar todas as compras com o nome do cliente
            String sql = "SELECT c.idCompra, u.nome AS nomeCliente, c.nomeProduto, c.quantidadeItens, c.valorTotal, c.dataCompra " +
                         "FROM compra c " +
                         "INNER JOIN usuario u ON c.idClienteCompra = u.id";
            PreparedStatement statement = conexao.prepareStatement(sql);
    
            // Executar a declaração SQL e obter o resultado
            ResultSet resultSet = statement.executeQuery();
    
            // Exibir informações de todas as compras com o nome do cliente
            while (resultSet.next()) {
                System.out.println("ID da Compra: " + resultSet.getInt("idCompra"));
                System.out.println("Nome do Cliente: " + resultSet.getString("nomeCliente"));
                System.out.println("Nome do Produto: " + resultSet.getString("nomeProduto"));
                System.out.println("Quantidade de Itens: " + resultSet.getInt("quantidadeItens"));
                System.out.println("Valor Total: " + resultSet.getDouble("valorTotal"));
                System.out.println("Data da Compra: " + resultSet.getDate("dataCompra"));
                System.out.println("----------------------");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao listar as compras: " + ex.getMessage());
        }
    }
      
    
}
