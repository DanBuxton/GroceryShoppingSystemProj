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

    public boolean addProduct(ProductDTO p)
    {
        boolean result = false;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE) VALUES (?, ?)"))
        {
            stmt.setString(1, p.getName());
            stmt.setDouble(2, p.getPrice());

            result = stmt.executeUpdate() == 1;
        }
        catch (Exception e)
        {
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return result;
    }

    public ProductDTO getProduct(long id)
    {
        ProductDTO res = null;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PRODUCT WHERE PRODUCT_ID = ?"))
        {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                res = new ProductDTO(rs.getInt("PRODUCT_ID"), rs.getString("PRODUCT_NAME"), rs.getFloat("PRODUCT_PRICE"));
            }
        }
        catch (Exception e)
        {
            res = null;
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return res;
    }

    public ProductDTO getProduct(String name)
    {
        ProductDTO res = null;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PRODUCT WHERE PRODUCT_NAME = ?"))
        {
            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                ProductDTO p = new ProductDTO(rs.getInt("PRODUCT_ID"), rs.getString("PRODUCT_NAME"), rs.getFloat("PRODUCT_PRICE"));
            }
        }
        catch (Exception e)
        {
            res = null;
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return res;
    }

    public ProductDTO[] getProducts()
    {
        ProductDTO[] res = new ProductDTO[countProducts()];

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PERSON"))
        {
            ResultSet rs = stmt.executeQuery();

            for (int i = 0; i < res.length && rs.next(); i++)
            {
                ProductDTO p = new ProductDTO(rs.getInt("PRODUCT_ID"), rs.getString("PRODUCT_NAME"), rs.getFloat("PRODUCT_ID"));
//                u.setId(rs.getInt("PERSON_ID"));
                res[i] = p;
            }
        }
        catch (Exception e)
        {
            res = null;
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return res;
    }

    public boolean updateProduct(ProductDTO p)
    {
        boolean res = false;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("UPDATE PRODUCT SET PRODUCT_NAME = ?, PRODUCT_PRICE = ? WHERE PRODUCT_ID = ?"))
        {
            stmt.setString(1, p.getName());
            stmt.setDouble(2, p.getPrice());
            stmt.setLong(3, p.getId());

            res = stmt.executeUpdate() == 1;
        }
        catch (Exception e)
        {
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return res;
    }

    public boolean removeProduct(long id)
    {
        boolean res = false;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("DELETE FROM PRODUCT WHERE PRODUCT_ID = ?"))
        {
            stmt.setLong(1, id);
            
            res = stmt.executeUpdate() == 1;
        }
        catch (Exception e)
        {
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return res;
    }

    private static int countProducts()
    {
        int res = -1;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(PRODUCT_ID) FROM PRODUCT"))
        {
            ResultSet rs = stmt.executeQuery();

            res = rs.getInt("1");
        }
        catch (Exception e)
        {
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return res;
    }
}
