/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author antonio
 */
public class Conexao {
    static Connection conexao = null;
    static String driver = "oracle.jdbc.driver.OracleDriver";
    static String servidor = "192.168.1.251";
    static String porta = "1521";
    static String banco = "xe";
    static String url = "jdbc:oracle:thin:@"+servidor+":"+porta+":"+banco;
    static String usuario = "Aluno16";
    static String senha = "Aluno16";
    //"jdbc:mysql://"+servidor+":"+porta+"/"+banco;
    
    public static Connection conectar()
    {
        try{
           Class.forName(driver);
           conexao = DriverManager.getConnection(url,usuario,senha);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            System.out.println("Erro:"+e.getMessage());
        }
        
        return conexao;
    }
            
}
