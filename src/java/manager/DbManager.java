package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.derby.jdbc.ClientDriver;

public class DbManager
{

    public static Connection getConnection() throws SQLException
    {
        DriverManager.registerDriver(new ClientDriver());
        return DriverManager.getConnection("jdbc:derby://localhost:1527/GroceryShoppingSystem", "dan", "dan");
    }
}
