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
import javax.swing.JOptionPane;
import loja.entidades.Categoria;
import loja.interfaces.InterBanco;

/**
 *
 * @author antonio
 */
public class CategoriaDao implements InterBanco{
    private Categoria cat;

    public CategoriaDao(Categoria cat) {
        this.cat = cat;
    }

    @Override
    public boolean insert() {
        String sql = "insert into loja_categoria "
                +"(nome) values "
                +"(?)";
        try{
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1,cat.getNome());
         ps.executeUpdate();
         return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public boolean update() {
        String sql = "update loja_categoria set "
                +"nome = ? "
                +"where cod_categoria = ?";
        try{
            Connection con = Conexao.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,cat.getNome());
            ps.setInt(2,cat.getCod_categoria());
            ps.executeUpdate();
            return true;
            
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete() {
        String sql = "DELETE FROM LOJA_CATEGORIA "
                +"WHERE COD_CATEGORIA = ?";
        try{
            Connection con = Conexao.conectar();
            PreparedStatement ps = con.prepareStatement(sql);  
            ps.setInt(1,cat.getCod_categoria());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean findAll() {
        String sql = "select cod_categoria, nome "
                +"from loja_categoria "
                +"where cod_categoria = "
                +cat.getCod_categoria();
        
        try{
            Connection con = Conexao.conectar();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next(); // movo o ponteiro
            
            if(rs.getRow() >0){
                cat.setCod_categoria(
                rs.getInt("cod_categoria"));
                cat.setNome(rs.getString("nome"));
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
