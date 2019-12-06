package manager;

import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserManager
{

    public boolean insertUser(UserDTO user)
    {
        boolean result = false;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("INSERT INTO PERSON (FORENAME, SURNAME, USERNAME, PASSWORD, is_admin) VALUES (?, ?, ?, ?, ?)"))
        {
            stmt.setString(1, user.getForename());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, "false");

            result = stmt.executeUpdate() == 1;
        }
        catch (Exception e)
        {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return result;
    }

    public UserDTO getUser(int id)
    {
        UserDTO result = null;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PERSON WHERE ID = ?"))
        {
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                result = new UserDTO(rs.getString("FORENAME"), rs.getString("SURNAME"), rs.getString("USERNAME"), rs.getString("PASSWORD"), Boolean.parseBoolean(rs.getString("IS_ADMIN")));
                result.setId(rs.getInt("id"));
            }
        }
        catch (SQLException e)
        {
            Logger.getLogger(UserManager.class.getName()).log(Level.WARNING, e.getMessage(), e);
        }

        return result;
    }

    public UserDTO getUser(String username, String password)
    {
        UserDTO result = null;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PERSON WHERE USERNAME = ? AND PASSWORD = ?"))
        {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                result = new UserDTO(rs.getString("FORENAME"), rs.getString("SURNAME"), username, password, rs.getBoolean("IS_ADMIN"));
                result.setId(rs.getLong("id"));
            }
        }
        catch (SQLException e)
        {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return result;
    }

    public ArrayList<UserDTO> getUsers()
    {
        ArrayList<UserDTO> users = new ArrayList<>();

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PERSON"))
        {
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                UserDTO u = new UserDTO(rs.getString("FORENAME"), rs.getString("SURNAME"), rs.getString("USERNAME"), rs.getString("PASSWORD"), Boolean.parseBoolean(rs.getString("IS_ADMIN")));
                u.setId(rs.getInt("id"));
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

    public boolean updateUser(UserDTO u)
    {
        boolean result = false;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("UPDATE PERSON (FORENAME, SURNAME, USERNAME, PASSWORD, IS_ADNIN) VALUES (?, ?, ?, ?, ?) WHERE id = ?"))
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

    public boolean removeUser(int id)
    {
        boolean result = false;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("DELETE PERSON WHERE ID = ?"))
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
}
