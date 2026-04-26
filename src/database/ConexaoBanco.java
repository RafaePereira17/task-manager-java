package database;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConexaoBanco {
    public static Connection conectar(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:tarefa.db");
            return conn;
        }catch(Exception e ){
            System.out.println("Erro ao conctar");
            e.printStackTrace();
            return null;
        }
    }
    public static void criarTabela(){
        Connection conn = conectar();
        try{
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS tarefas("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+"descricao TEXT,"+"status INTEGER"+");";
            stmt.execute(sql);
            stmt.close();
            System.out.println("tabela criada!");
            
        }catch(Exception e ){
            System.out.println("Erro ao criar tabela");
            e.printStackTrace();
        }
    }
    public static void adicionarTarefa(String descricao){
        Connection conn = conectar();
        try{
            String sql = "INSERT INTO tarefas(descricao,status) VALUES (?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, descricao);
            stmt.setInt(2,0);
            stmt.execute();

            stmt.close();
            conn.close();
            
        }catch ( Exception  e ){
            System.out.println("Erro em adicionar");
            e.printStackTrace();
        }
    }
    public static void listarTarefa(){
        Connection conn = conectar();
        try{
            String sql = "SELECT *FROM tarefas";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

           

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                int status = rs.getInt("status");
                int id = rs.getInt("id");
                String statusText;
                if (status==0) {
                    statusText="Pendente";

                    
                }else{
                    statusText="Concluido";
                }
               System.out.println("--------------------");
               System.out.println("ID: "+ id);
               System.out.println("Descrição: " + descricao);
               System.out.println("Status: " + statusText);
            }
            stmt.close();
            conn.close();
        }catch ( Exception e ){
            System.out.println("Erro ao listar");
            e.printStackTrace();
        }
    }
    public static void concluirTarefa(int id){
        Connection conn  = conectar();
        try{
            String sql = "UPDATE tarefas SET status =1 WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Tarefa atualizada");
                
            }else{
                System.out.println("Tarefa nao encontrada");
            }

            stmt.close();
            conn.close();

        }catch ( Exception e ){
            System.out.println("Erro em atualizar");
            e.printStackTrace();
        }
    }
    public static void removerTarefa(int id){
        Connection conn = conectar();
        try{
            String sql = "DELETE FROM tarefas WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();

           

            if (linhas > 0) {
                System.out.println("Tarefa removida");
                
            }else{
                System.out.println("Tarefa nao encontrada");
            }

            stmt.close();
            conn.close();

        }catch ( Exception e ){
            System.out.println("Erro em remover");
            e.printStackTrace();
        }
    }

    
    
}
