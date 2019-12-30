/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.ProductDTO;
import dto.StockDTO;
import dto.StoreDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockManager
{
    public static ArrayList<StockDTO> getStoreStock(long storeId)
    {
        ArrayList<StockDTO> stock = new ArrayList<>();
        
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Stock WHERE Store_Id = ? JOIN Store WHERE Store(Id) = Stock(Store_Id)"))
        {
            stmt.setLong(1, storeId);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
//                stock.add(new StockDTO(storeId, rs.getLong("PRODUCT_ID"), rs.getInt("QTY")));
            }
        }
        catch (Exception e)
        {
            stock = null;
        }
        
        return stock;
    }
    
    public static ArrayList<StockDTO> getStock(long stockId)
    {
        ArrayList<StockDTO> stock = new ArrayList<>();
        
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT Stock.Stock_ID, Stock.Store_ID Store.Store_Name, Store.Name, Product.Product_ID, Product.Name, Product.Price FROM Stock INNER JOIN Store ON Store.StoreId = Stock.Store_Id INNER JOIN Product ON Product.Product_ID = Stock.Product_ID WHERE Store.Store_ID = ?"))
        {
            stmt.setLong(1, stockId);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
//                StockDTO item = new StockDTO(product, 0);
//                stock.add(new StockDTO(stockId, rs.getLong("PRODUCT_ID"), rs.getInt("QTY")));
            }
        }
        catch (Exception e)
        {
            stock = null;
        }
        
        return stock;
    }
    
    public static ArrayList<StockDTO> getAllStock()
    {
        ArrayList<StockDTO> stock = new ArrayList<>();
        
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT STOCK.STOCK_ID, PRODUCT.PRODUCT_NAME, STOCK.QTY, STORE.STORE_ID, STORE.STORE_NAME, STORE.LOCATION, PRODUCT.PRODUCT_ID, PRODUCT.PRICE FROM STOCK INNER JOIN STORE ON STORE.STORE_ID = STOCK.STORE_ID INNER JOIN PRODUCT ON PRODUCT.PRODUCT_ID = STOCK.PRODUCT_ID"))
        {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
                StoreDTO s = new StoreDTO(rs.getLong("STORE_ID"), rs.getString("STORE_NAME"), rs.getString("LOCATION"));
                ProductDTO p = new ProductDTO(rs.getLong("PRODUCT_ID"), rs.getString("PRODUCT_NAME"), rs.getDouble("PRICE"));
                
                stock.add(new StockDTO(s, p, rs.getInt("QTY")));
            }
        }
        catch (Exception e)
        {
            stock = null;
            Logger.getLogger(StockManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        
        return stock;
    }
}
