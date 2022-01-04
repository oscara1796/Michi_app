/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.michi_app;

import Interface.CatService;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author oscar
 */
public class Inicio {
    
    public static void main(String[] args) {
        
        String [] botones = {" 1. Ver gatitos ", "2. ver favoritos "," 3. salir "};
        int opcion_menu= -1;
         final ImageIcon icon = new ImageIcon("cat.png");
         Image image = icon.getImage();
         
        
        do{
            
            
            String input = (String) JOptionPane.showInputDialog(null, "Gatitos java", "Menu principal", JOptionPane.INFORMATION_MESSAGE, icon, botones,botones[0]
            );
            
            for (int i = 0; i < botones.length; i++) {
                if (input.equals(botones[i])) {
                    opcion_menu = i;
                    break;
                }
            }
            
            if(opcion_menu == 0){
                try {
                    CatService.ShowPussy();
                } catch (IOException ex) {
                   // Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (opcion_menu == 1){
                System.out.println("Hello");
                 CatService.ShowFav();
            }
                
            
        } while(opcion_menu != 2);
                
    }
    
}
