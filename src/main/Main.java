package main;
import database.ConexaoBanco;
import service.SistemaTarefas;
import java.util.Scanner;
import java.util.InputMismatchException;
public class Main {
    public static void menu(Scanner sc ){
        SistemaTarefas sistema = new SistemaTarefas();
        int opcao =0;
        while (true) {
            try{
                System.out.println("1- Adicionar tarefa");
                System.out.println("2- Listar tarefas");
                System.out.println("3- Marcar como concluido");
                System.out.println("4- Remover tarefa");
                System.out.println("5- Sair");
                opcao = sc.nextInt();
                switch (opcao) {
                    case 1:
                        sistema.adicionarTarefa(sc);
                        
                        break;
                        case 2:
                            sistema.listarTarefa();
                            break;
                            case 3:
                                sistema.concluirTarefa(sc);
                                break;
                                case 4 :
                                    sistema.removerTarefa(sc);
                                    break;
                                    case 5:
                                        System.out.println("Encerrando");
                                        return;
                
                    default:
                        System.out.println("opçao invalida");
                        break;
                }
            }catch ( InputMismatchException e){
                System.out.println("Digite um numero valido");
                sc.nextLine();
            }
            
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConexaoBanco.conectar();
        ConexaoBanco.criarTabela();
        menu(sc);
        sc.close();
    }
    
}
