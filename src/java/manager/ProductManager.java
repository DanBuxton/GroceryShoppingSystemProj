/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.ProductDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductManager
{
    public ProductDTO[] getProducts()
    {
        ProductDTO[] res = new ProductDTO[countProducts()];
        
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PERSON"))
        {
            ResultSet rs = stmt.executeQuery();
            
            for (int i = 0; i < res.length && rs.next(); i++)
            {
                ProductDTO p = new ProductDTO(rs.getInt("PRODUCT_ID"), rs.getString("PRODUCT_NAME"), rs.getFloat("USERNAME"));
//                u.setId(rs.getInt("PERSON_ID"));
                res[i] = p;
            }
        }
        catch (Exception e)
        {
            res = null;
            Logger.getLogger(UserManager.class.getName()).log(Level.WARNING, e.getMessage(), e);
        }
        
        return res;
    }
    
    private int countProducts()
    {
        int res = -1;
        
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(PERSON_ID) FROM PERSON"))
        {
            ResultSet rs = stmt.executeQuery();
            
            res = rs.getInt("1");
        }
        catch (Exception e)
        {
            Logger.getLogger(UserManager.class.getName()).log(Level.WARNING, e.getMessage(), e);
        }
        
        return res;
    }
}
