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
import loja.entidades.Produto;
import loja.interfaces.InterBanco;

/**
 *
 * @author antonio
 */
public class ProdutoDao implements InterBanco{
    Produto prod = new Produto();
    
    public ProdutoDao(Produto prod){
        this.prod = prod;
    }
    
    @Override
    public boolean insert() {
         //To change body of generated methods, choose Tools | Templates.
         String sql = "insert into loja_produto "
                +"(nome, cod_categoria,estoque, preco) "
                +"values (?,?,?,?)";
        try{
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1,prod.getNome());
         ps.setInt(2,prod.getCod_categoria());
         ps.setDouble(3,prod.getEstoque());
         ps.setDouble(4,prod.getPreco());
         ps.executeUpdate();
         return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete() {
         //To change body of generated methods, choose Tools | Templates.
          String sql = "DELETE FROM LOJA_PRODUTO "
                +"WHERE COD_PRODUTO = ?";
        try{
            Connection con = Conexao.conectar();
            PreparedStatement ps = con.prepareStatement(sql);  
            ps.setInt(1,prod.getCod_produto());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update() {
         //To change body of generated methods, choose Tools | Templates.
         String sql = "update loja_produto set "
                +"nome = ?, "
                +"cod_categoria = ?, "
                +"estoque = ?,  "
                +"preco = ? "
                +"where cod_produto = ?";
        try{
            Connection con = Conexao.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,prod.getNome());
            ps.setInt(2,prod.getCod_categoria());
            ps.setDouble(3,prod.getEstoque());
            ps.setDouble(4,prod.getPreco());
            ps.setInt(5,prod.getCod_produto());
            ps.executeUpdate();
            return true;
            
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean findAll() {
        //To change body of generated methods, choose Tools | Templates.
        String sql = "select cod_produto, nome, "
                +"cod_categoria, estoque, preco "
                +"from loja_produto "
                +"where cod_produto = "
                +prod.getCod_produto();
        
        try{
            Connection con = Conexao.conectar();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next(); // movo o ponteiro
            
            if(rs.getRow() >0){
                prod.setCod_produto(rs.getInt("cod_produto"));
                prod.setNome(rs.getString("nome"));
                prod.setCod_categoria(rs.getInt("cod_categoria"));
                prod.setEstoque(rs.getDouble("estoque"));
                prod.setPreco(rs.getDouble("preco"));
                
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
