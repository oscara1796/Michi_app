/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import com.google.gson.Gson;
import com.mycompany.michi_app.Cat;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author oscar
 */
public class catDAOimpl implements catDAO{

    @Override
    public String getCat() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/images/search")
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            String elJson = response.body().string();
            
            // Cut first and last brackets
            
            elJson = elJson.substring(1, elJson.length()-1);
            
            // Create object Gson 
            
           
            return elJson;
            
        } catch (IOException ex) {
            //Logger.getLogger(catDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }

    @Override
    public String markFavCat(Cat cat) {
        try {
            OkHttpClient client = new OkHttpClient();
          MediaType mediaType = MediaType.parse("application/json");
          RequestBody body = RequestBody.create(mediaType, "{\n    \"image_id\":\""+cat.getId()+"\"\n}");
          Request request = new Request.Builder()
            .url("https://api.thecatapi.com/v1/favourites")
            .method("POST", body)
            .addHeader("Content-Type", "application/json")
            .addHeader("x-api-key", cat.getApiKey())
            .build();
          Response response = client.newCall(request).execute();
          return response.body().string();
        } catch (Exception e) {
        }
        return "";
    }

    @Override
    public String showFavCats() {
        try {
            
            OkHttpClient client = new OkHttpClient();
          Request request = new Request.Builder()
            .url("https://api.thecatapi.com/v1/favourites")
            .method("GET", null)
            .addHeader("x-api-key", Cat.getApiKey())
            .build();
          Response response = client.newCall(request).execute();
          
          String elJson = response.body().string();
            
          
              return elJson;
        } catch (Exception e) {
        }
        return "";
    }

    @Override
    public String deleteFav(FavCat cat) {
        try {
            OkHttpClient client = new OkHttpClient();
          MediaType mediaType = MediaType.parse("text/plain");
          RequestBody body = RequestBody.create(mediaType, "");
          Request request = new Request.Builder()
            .url("https://api.thecatapi.com/v1/favourites/" + cat.getId())
            .method("DELETE", body)
            .addHeader("x-api-key", cat.getApi_key())
            .build();
          Response response = client.newCall(request).execute();
          String elJson = response.body().string();
          return elJson;
        } catch (Exception e) {
        }
        return "";
    }
    
}
