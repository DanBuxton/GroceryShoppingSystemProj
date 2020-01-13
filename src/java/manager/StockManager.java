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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class StockManager
{

    public boolean addStock(StockDTO s)
    {
        boolean result = false;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("INSERT INTO STOCK (STORE_ID, PRODUCT_ID, QTY) VALUES (?, ?, ?)"))
        {
            stmt.setLong(1, s.getStore().getId());
            stmt.setLong(2, s.getProduct().getId());
            stmt.setInt(3, s.getQty());

            result = stmt.executeUpdate() == 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(PersonManager.class.getName()).log(Level.WARNING, e.getMessage(), e);
        }

        return result;
    }

    public StockDTO getStock(long stockId)
    {
        StockDTO stock = null;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT Stock.Store_ID, Store.Store_Name, Store.Location, Product.Product_ID, Product.Product_Name, Product.Product_Price, Stock.Qty FROM Stock INNER JOIN Store ON Store.Store_Id = Stock.Store_Id INNER JOIN Product ON Product.Product_ID = Stock.Product_ID WHERE Stock.Stock_ID = ?"))
        {
            stmt.setLong(1, stockId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                stock = new StockDTO(stockId, new StoreDTO(rs.getLong("STORE_ID"), rs.getString("STORE_NAME"), rs.getString("LOCATION")), new ProductDTO(rs.getLong("PRODUCT_ID"), rs.getString("PRODUCT_NAME"), rs.getDouble("PRODUCT_PRICE")), rs.getInt("QTY"));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Stock is null!!!!!!!"));
            }
        }
        catch (Exception e)
        {
            Logger.getLogger(StockManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return stock;
    }

    public ArrayList<StockDTO> getStoreStock(long storeId)
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

    public ArrayList<StockDTO> getAllStock()
    {
        ArrayList<StockDTO> stock = new ArrayList<>();

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT STOCK.STOCK_ID, PRODUCT.PRODUCT_NAME, STOCK.QTY, STORE.STORE_ID, STORE.STORE_NAME, STORE.LOCATION, PRODUCT.PRODUCT_ID, PRODUCT.PRODUCT_PRICE FROM STOCK INNER JOIN STORE ON STORE.STORE_ID = STOCK.STORE_ID INNER JOIN PRODUCT ON PRODUCT.PRODUCT_ID = STOCK.PRODUCT_ID"))
        {
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                StoreDTO s = new StoreDTO(rs.getLong("STORE_ID"), rs.getString("STORE_NAME"), rs.getString("LOCATION"));
                ProductDTO p = new ProductDTO(rs.getLong("PRODUCT_ID"), rs.getString("PRODUCT_NAME"), rs.getDouble("PRODUCT_PRICE"));

                stock.add(new StockDTO(rs.getLong("STOCK_ID"), s, p, rs.getInt("QTY")));
            }
        }
        catch (Exception e)
        {
            stock = null;
            Logger.getLogger(StockManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return stock;
    }

    public ArrayList<StockDTO> getSomeStock()
    {
        ArrayList<StockDTO> stock = new ArrayList<>();

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT STOCK.STOCK_ID, PRODUCT.PRODUCT_NAME, STOCK.QTY, STORE.STORE_ID, STORE.STORE_NAME, STORE.LOCATION, PRODUCT.PRODUCT_ID, PRODUCT.PRODUCT_PRICE FROM STOCK INNER JOIN STORE ON STORE.STORE_ID = STOCK.STORE_ID INNER JOIN PRODUCT ON PRODUCT.PRODUCT_ID = STOCK.PRODUCT_ID"))
        {
            ResultSet rs = stmt.executeQuery();

            for (int i = 0; i < 2 && rs.next(); i++)
            {
                StoreDTO s = new StoreDTO(rs.getLong("STORE_ID"), rs.getString("STORE_NAME"), rs.getString("LOCATION"));
                ProductDTO p = new ProductDTO(rs.getLong("PRODUCT_ID"), rs.getString("PRODUCT_NAME"), rs.getDouble("PRODUCT_PRICE"));

                stock.add(new StockDTO(rs.getLong("STOCK_ID"), s, p, rs.getInt("QTY")));
            }
        }
        catch (Exception e)
        {
            stock = null;
            Logger.getLogger(StockManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return stock;
    }

    public boolean updateStock(StockDTO s)
    {
        boolean result = false;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("UPDATE STOCK (QTY) VALUES (?) WHERE STOCK_ID = ?"))
        {
            stmt.setInt(1, s.getQty());
            stmt.setLong(2, s.getId());

            result = stmt.executeUpdate() == 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(StockManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return result;
    }

    public double getStockPrice(long id)
    {
        double result = 0.0;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT PRODUCT_PRICE FROM STOCK INNER JOIN PRODUCT ON PRODUCT.PRODUCT_ID = STOCK.PRODUCT_ID WHERE STOCK_ID = ?"))
        {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                result = rs.getDouble("PRODUCT_PRICE");
            }
        }
        catch (SQLException e)
        {
            Logger.getLogger(StockManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return result;
    }

    public boolean removeStock(long id)
    {
        boolean result = false;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("DELETE FROM STOCK WHERE STOCK_ID = ?"))
        {
            stmt.setLong(1, id);

            result = stmt.executeUpdate() == 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(StockManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return result;
    }

}
