/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mycompany.michi_app.Cat;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author oscar
 */
public class CatService {

    public static void ShowPussy() throws IOException {
        
        try {
            catDAOimpl dao = new catDAOimpl();
            Gson gson = new Gson();
            Cat cat = gson.fromJson(dao.getCat(), Cat.class);
            
            Image image = null;
            
            URL url = new URL(cat.getUrl());
            
            image = ImageIO.read(url);
            
            ImageIcon icon = new ImageIcon(image);
            
            if (icon.getIconWidth() > 800) {
                Image catBackground = icon.getImage();
                Image modify = catBackground.getScaledInstance(800,600, Image.SCALE_SMOOTH);
                icon= new ImageIcon(modify);
            }
            
            String menu = "Opciones: \n " 
                    + "1. ver otra imagen de gato \n"
                    + "2. Favorito \n"
                    + "3. Volver \n";
            
            String[] botones = {"Ver otra imagen", "Favorito", "volver"};
            
            
            String id_gato = String.valueOf(cat.getId());
            String opcion = (String) JOptionPane.showInputDialog(null, menu, id_gato,JOptionPane.INFORMATION_MESSAGE, icon,botones,botones[0]);
            
            int sel= -1;
            
            for (int i = 0; i < botones.length; i++) {
                if (opcion.equals(botones[i])) {
                    sel=i;
                    break;
                }
            }
            
            switch(sel){
                case 0:
                    ShowPussy();
                   break;
                case 1:
                    favCaty(cat);
                    break;
                default:
                    break;
            }
        } catch (MalformedURLException ex) {
           // Logger.getLogger(CatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void favCaty(Cat cat) {
        catDAOimpl dao = new catDAOimpl();
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(dao.markFavCat(cat)).getAsJsonObject();
        String message = obj.get("message").getAsString();
        if (message.equals("SUCCESS")) {
            JOptionPane.showMessageDialog(null,"Se marco como fav");
        } else{
            JOptionPane.showMessageDialog(null,"Error, no se pudo marcar como fav");
        }
    }

    public static void ShowFav() {
        catDAOimpl dao = new catDAOimpl();
        Gson gson = new Gson();
        
        
        FavCat [] catArray = gson.fromJson(dao.showFavCats(), FavCat[].class);
          
            if (catArray.length > 0) {
            try {
                int min= 1;
                int max = catArray.length;
                int aletorio = (int) (Math.random()*((max-min)+1))+min;
                int index = aletorio-1;
                
                FavCat catFav = catArray[index];
                Image image = null;
                
                URL url = new URL(catFav.getImage().getUrl());
                
                image = ImageIO.read(url);
                
                ImageIcon icon = new ImageIcon(image);
                
                if (icon.getIconWidth() > 800) {
                    Image catBackground = icon.getImage();
                    Image modify = catBackground.getScaledInstance(800,600, Image.SCALE_SMOOTH);
                    icon= new ImageIcon(modify);
                }
                
                String menu = "Opciones: \n "
                        + "1. ver otra imagen de gato \n"
                        + "2. Eliminar fav \n"
                        + "3. Volver \n";
                
                String[] botones = {"Ver otra imagen", "Eliminar fav", "volver"};
                
                
                String id_gato = String.valueOf(catFav.getId());
                String opcion = (String) JOptionPane.showInputDialog(null, menu, id_gato,JOptionPane.INFORMATION_MESSAGE, icon,botones,botones[0]);
                
                int sel= -1;
                
                for (int i = 0; i < botones.length; i++) {
                    if (opcion.equals(botones[i])) {
                        sel=i;
                        break;
                    }
                }
                
                switch(sel){
                    case 0:
                        ShowFav();
                        break;
                    case 1:
                        delFav(catFav);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                
            }
        }
            
           
    }

    private static void delFav(FavCat catFav) {
       catDAOimpl dao = new catDAOimpl();
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(dao.deleteFav(catFav)).getAsJsonObject();
        String message = obj.get("message").getAsString();
        if (message.equals("SUCCESS")) {
            JOptionPane.showMessageDialog(null,"Se elimino de fav");
        } else{
            JOptionPane.showMessageDialog(null,"Error, no se pudo eliminar de  fav");
        }
    }
    
}
