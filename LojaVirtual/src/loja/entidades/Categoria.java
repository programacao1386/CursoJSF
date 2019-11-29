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
public class Categoria {
    private int cod_categoria;
    private String nome;

    public int getCod_categoria() {
        return cod_categoria;
    }

    public void setCod_categoria(int cod_categoria) {
        this.cod_categoria = cod_categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Este construtor é usado somente no método
     * carregaCombo(). Ele permite exibir o código
     * e o nome da categoria na caixa de combinação.
     * @param cod
     * @param nome 
     */
    public Categoria(int cod, String nome){
        this.nome = nome;
        this.cod_categoria = cod;
    }
    /**
     * Este construtor é utilizado nas tela de 
     * cadastro, ou quando um objeto precisa
     * ser iinicializado sem valores.
     */
    public Categoria(){
        
    }
    
    public String toString(){
        return this.cod_categoria+
                " - "+this.nome;
    }
    
    /**
     * Este método inicializa as opções de 
     * uma caixa de combinação, carregando 
     * os dados de uma tabela caso a 
     * conexao com o Oracle seja bem sucedida.
     * @param combo
     * @param cod 
     */
    public static void carregaCombo(JComboBox combo, int cod) throws SQLException{
        String sql = "select cod_categoria, nome "
                +"from loja_categoria order by nome";
        try{
            Connection con = Conexao.conectar();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            //Representa a categoria selecionada
            Categoria categoria = null;
            
            //Configurar combo
            combo.removeAllItems();//limpa a combo
            combo.addItem("Selecione uma categoria");
            
            while(rs.next()){
                Categoria cat = 
                        new Categoria(rs.getInt
                        ("cod_categoria")
                        ,rs.getString("nome"));
                if(cod == rs.getInt("cod_categoria")){
                    categoria = cat;
                }
                combo.addItem(cat);//termina de montar a combo
            }
            
            if(categoria !=null){
                combo.setSelectedItem(categoria);
            }
            
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        
    }
    
   
    
}
