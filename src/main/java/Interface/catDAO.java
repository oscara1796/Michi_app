/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import com.mycompany.michi_app.Cat;

/**
 *
 * @author oscar
 */
public interface catDAO {
    
    
    String getCat();
    
    String markFavCat(Cat cat);
    
    
    String showFavCats();
    
    String deleteFav(FavCat cat);
}
