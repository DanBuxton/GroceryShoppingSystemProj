package manager;

import dto.StoreDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StoreManager
{
    public boolean addStore(StoreDTO s)
    {
        boolean result = false;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("INSERT INTO STORE (STORE_NAME, LOCATION) VALUES (?, ?)"))
        {
            stmt.setString(1, s.getName());
            stmt.setString(2, s.getLocation());

            result = stmt.executeUpdate() == 1;
        }
        catch (Exception e)
        {
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return result;
    }
    
    public StoreDTO getStore(long id)
    {
        StoreDTO s = null;
        
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT STORE_ID, LOCATION FROM STORE WHERE STORE_ID = ?"))
        {
            stmt.setLong(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next())
            {
                s = new StoreDTO(id, rs.getString("STORE_NAME"), rs.getString("LOCATION"));
            }
        }
        catch (SQLException ex)
        {
        }
        
        return s;
    }
    public StoreDTO getStores()
    {
        StoreDTO s = null;
        
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT STORE_ID, STORE_NAME, LOCATION FROM Store"))
        {
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next())
            {
                s = new StoreDTO(rs.getLong("STORE_ID"), rs.getString("STORE_NAME"), rs.getString("LOCATION"));
            }
        }
        catch (SQLException ex)
        {
        }
        
        return s;
    }
    
    public boolean updateStore()
    {
        boolean res = false;
        
        
        
        return res;
    }
}
