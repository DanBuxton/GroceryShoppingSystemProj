package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreManager
{
    public void getStore(int id)
    {
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Store WHERE Id = ?"))
        {
            stmt.setInt(1, id);
            
            ResultSet store = stmt.executeQuery();
            
        }
        catch (SQLException ex)
        {
        }
    }
}
