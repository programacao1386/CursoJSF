/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import loja.dao.Conexao;

/**
 *
 * @author antonio
 */
public class Cidade {
    private int cod_cidade;
    private String nome;
    private String estado;

    public int getCod_cidade() {
        return cod_cidade;
    }

    public void setCod_cidade(int cod_cidade) {
        this.cod_cidade = cod_cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    //Construtor

    public Cidade() {
    }

    public Cidade(int cod_cidade, String nome) {
        this.cod_cidade = cod_cidade;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.cod_cidade+" - "+this.nome;
    }
    
    public static void carregaCombo(JComboBox combo, int cod) throws SQLException{
        String sql = "select cod_cidade, nome "
                +"from loja_cidade order by nome";
        try{
            Connection con = Conexao.conectar();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            //Representa a categoria selecionada
            Cidade cidade = null;
            
            //Configurar combo
            combo.removeAllItems();//limpa a combo
            combo.addItem("Selecione uma cidade");
            
            while(rs.next()){
                Cidade cid = 
                        new Cidade(rs.getInt
                        ("cod_cidade"),
                        rs.getString("nome"));
                if(cod == rs.getInt("cod_cidade")){
                    cidade = cid;
                }
                combo.addItem(cid);//termina de montar a combo
            }
            
            if(cidade !=null){
                combo.setSelectedItem(cidade);
            }
            
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        
    }
    
    
    
}
