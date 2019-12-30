package manager;

import dto.PersonDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserManager
{
    
    private static int countCustomers()
    {
        int result = -1;
        
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(person_id) FROM person WHERE is_admin = FALSE");)
        {
            ResultSet rs = stmt.executeQuery();
            result =  rs.getInt("1");
        }
        catch (SQLException ex)
        {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, "SQL EXCEPTION", ex);
        }
        
        return result;
    }
    private static int countAdmins()
    {
        int result = -1;
        
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(person_id) FROM person WHERE is_admin = TRUE");)
        {
            ResultSet rs = stmt.executeQuery();
            result =  rs.getInt("1");
        }
        catch (SQLException ex)
        {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, "SQL EXCEPTION", ex);
        }
        
        return result;
    }
    private static int countUsers()
    {
        int result = -1;
        
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(person_id) FROM person");)
        {
            ResultSet rs = stmt.executeQuery();
            result =  rs.getInt("1");
        }
        catch (SQLException ex)
        {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, "SQL EXCEPTION", ex);
        }
        
        return result;
    }
    
    public boolean insertCustomer(PersonDTO user)
    {
        boolean result = false;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("INSERT INTO PERSON (FORENAME, SURNAME, USERNAME, PASSWORD, ADDRESS, IS_ADMIN) VALUES (?, ?, ?, ?, ?, ?)"))
        {
            stmt.setString(1, user.getForename());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, "false");

            result = stmt.executeUpdate() == 1;
        }
        catch (Exception e)
        {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return result;
    }

    public PersonDTO getCustomer(int id)
    {
        PersonDTO result = null;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PERSON WHERE ID = ?"))
        {
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                result = new PersonDTO(rs.getString("FORENAME"), rs.getString("SURNAME"), rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("ADDRESS"), Boolean.parseBoolean(rs.getString("IS_ADMIN")));
                result.setId(rs.getInt("PERSON_ID"));
            }
        }
        catch (SQLException e)
        {
            Logger.getLogger(UserManager.class.getName()).log(Level.WARNING, e.getMessage(), e);
        }

        return result;
    }

    public PersonDTO getCustomer(String username, String password)
    {
        PersonDTO result = null;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PERSON WHERE USERNAME = ? AND PASSWORD = ?"))
        {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                result = new PersonDTO(rs.getString("FORENAME"), rs.getString("SURNAME"), username, password, rs.getString("ADDRESS"), rs.getBoolean("IS_ADMIN"));
                result.setId(rs.getLong("PERSON_ID"));
            }
        }
        catch (SQLException e)
        {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return result;
    }

    public PersonDTO[] getCustomers()
    {
        PersonDTO[] cust = new PersonDTO[countCustomers()];

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PERSON WHERE is_admin = FALSE"))
        {
            ResultSet rs = stmt.executeQuery();
            
            for (int i = 0; i < cust.length && rs.next(); i++)
            {
                PersonDTO c = new PersonDTO(rs.getString("FORENAME"), rs.getString("SURNAME"), rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("ADDRESS"), Boolean.parseBoolean(rs.getString("IS_ADMIN")));
                c.setId(rs.getInt("PERSON_ID"));
                cust[i] = c;
            }
        }
        catch (Exception e)
        {
            cust = null;
            Logger.getLogger(UserManager.class.getName()).log(Level.WARNING, e.getMessage(), e);
        }

        return cust;
    }

    public boolean updateCustomer(PersonDTO u)
    {
        boolean result = false;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("UPDATE PERSON (FORENAME, SURNAME, USERNAME, PASSWORD, IS_ADNIN) VALUES (?, ?, ?, ?, ?) WHERE PERSON_ID = ?"))
        {
            stmt.setString(1, u.getForename());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getUsername());
            stmt.setString(4, u.getPassword());
            stmt.setString(5, String.valueOf(u.isAnAdmin()));
            stmt.setLong(6, u.getId());

            result = stmt.executeUpdate() == 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(UserManager.class.getName()).log(Level.WARNING, e.getMessage(), e);
        }

        return result;
    }

    public boolean removeCustomer(int id)
    {
        boolean result = false;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("DELETE PERSON WHERE PERSON_ID = ?"))
        {
            stmt.setInt(1, id);

            result = stmt.executeUpdate() == 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(UserManager.class.getName()).log(Level.WARNING, e.getMessage(), e);
        }

        return result;
    }
    
    public PersonDTO[] getAdmins()
    {
        PersonDTO[] result = new PersonDTO[countAdmins()];
        
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PERSON"))
        {
            ResultSet rs = stmt.executeQuery();
            
            for (int i = 0; i < result.length && rs.next(); i++)
            {
                PersonDTO u = new PersonDTO(rs.getString("FORENAME"), rs.getString("SURNAME"), rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("ADDRESS"), Boolean.parseBoolean(rs.getString("IS_ADMIN")));
                u.setId(rs.getInt("PERSON_ID"));
                result[i] = u;
            }
        }
        catch (Exception e)
        {
            result = null;
            Logger.getLogger(UserManager.class.getName()).log(Level.WARNING, e.getMessage(), e);
        }
        
        return result;
    }

    public ArrayList<PersonDTO> getUsers()
    {
        ArrayList<PersonDTO> users = new ArrayList<>();

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PERSON"))
        {
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                PersonDTO u = new PersonDTO(rs.getString("FORENAME"), rs.getString("SURNAME"), rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("ADDRESS"), Boolean.parseBoolean(rs.getString("IS_ADMIN")));
                u.setId(rs.getInt("PERSON_ID"));
                users.add(u);
            }
        }
        catch (Exception e)
        {
            users = null;
            Logger.getLogger(UserManager.class.getName()).log(Level.WARNING, e.getMessage(), e);
        }

        return users;
    }
}
