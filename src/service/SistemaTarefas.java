package service;
import database.ConexaoBanco;
import java.util.Scanner;


public class SistemaTarefas {
    
    public void adicionarTarefa(Scanner sc ){
        sc.nextLine();
        System.out.println("Digite a descriçao");
        String descricao=sc.nextLine();

        ConexaoBanco.adicionarTarefa(descricao);
    }
    public void listarTarefa(){
        ConexaoBanco.listarTarefa();
    }
    public void concluirTarefa(Scanner sc){
        
        System.out.println("Digite o ID");
        int id = sc.nextInt();
        sc.nextLine();

        ConexaoBanco.concluirTarefa(id);
    }
    public void removerTarefa(Scanner sc ){
        sc.nextLine();
        System.out.println("Digite o ID");
        int id = sc.nextInt();

        ConexaoBanco.removerTarefa(id);
    }
}
