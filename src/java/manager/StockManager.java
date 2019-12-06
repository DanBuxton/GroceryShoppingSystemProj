/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.StockDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author apple
 */
public class StockManager
{
    public static ArrayList<StockDTO> getStock()
    {
        ArrayList<StockDTO> stock = new ArrayList<>();
        
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Store WHERE Id = ?"))
        {
            
        }
        catch (Exception e)
        {
            stock = null;
        }
        
        return stock;
    }
}
