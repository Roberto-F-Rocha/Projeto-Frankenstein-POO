package com.example.view;

import java.util.Scanner;

public class ServicoView {
    public void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Adicionar novo serviço");
        System.out.println("2. Visualizar informações do serviço");
        System.out.println("3. Editar informações do serviço");
        System.out.println("4. Listar todos os serviços");
        System.out.println("5. Remover serviço");
        System.out.println("6. Sair");
    }

    public int obterOpcao(Scanner scanner) {
        System.out.print("Escolha: ");
        return scanner.nextInt();
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
