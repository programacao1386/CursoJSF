/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import loja.entidades.Cidade;
import loja.interfaces.InterBanco;

/**
 *
 * @author antonio
 */
public class CidadeDao implements InterBanco{
    private Cidade cidade = new Cidade();
    
    public CidadeDao(Cidade cidade){
        this.cidade = cidade;
    }

    @Override
    public boolean insert() {
        
        String sql = "INSERT INTO loja_cidade "
                +"(nome, estado) VALUES (?,?)";
        try{
            Connection con = Conexao.conectar();
            PreparedStatement pst = 
                    con.prepareStatement(sql);
            pst.setString(1,cidade.getNome());
            pst.setString(2,cidade.getEstado());
            pst.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete() {
        //To change body of generated methods, choose Tools | Templates.
        String sql = "DELETE FROM LOJA_CIDADE "
                +"WHERE COD_CIDADE = ?";
        try{
            Connection con = Conexao.conectar();
            PreparedStatement ps = con.prepareStatement(sql);  
            ps.setInt(1,cidade.getCod_cidade());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update() {
         String sql = "update loja_cidade set "
                +"nome = ?, estado = ? "
                +"where cod_cidade = ?";
        try{
            Connection con = Conexao.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,cidade.getNome());
            ps.setString(2,cidade.getEstado());
            ps.setInt(3,cidade.getCod_cidade());
            ps.executeUpdate();
            return true;
            
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean findAll() {
       String sql = "select cidade, nome, estado "
                +"from loja_cidade "
                +"where cod_cidade = "
                +cidade.getCod_cidade();
        
        try{
            Connection con = Conexao.conectar();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next(); // movo o ponteiro
            
            if(rs.getRow() >0){
                cidade.setCod_cidade(rs.getInt("cod_cidade"));
                cidade.setNome(rs.getString("nome"));
                cidade.setEstado(rs.getString("estado"));
                return true;   
            }else{
                System.out.println("Sem resultados");
                return false;
            }
            
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
       
}
