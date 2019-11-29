/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.classes;

import java.sql.Connection;
import javax.swing.JFrame;
import loja.dao.Conexao;
import loja.gui.FrmMenu;

/**
 *
 * @author antonio
 */
public class LojaVirtual {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FrmMenu form = new FrmMenu();
        form.setExtendedState(JFrame.MAXIMIZED_BOTH);
        form.setVisible(true);
    }
    
}
