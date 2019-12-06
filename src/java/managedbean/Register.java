package managedbean;

import dto.UserDTO;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import userUI.UserCommandFactory;

@Named(value = "register")
@RequestScoped
public class Register
{

    private String forename;
    private String surname;
    private String username;
    private String password;

    public String getForename()
    {
        return forename;
    }

    public void setForename(String forename)
    {
        this.forename = forename;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        try
        {
            this.password = Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest(password.getBytes(StandardCharsets.UTF_8)));
        }
        catch (NoSuchAlgorithmException e)
        {
            this.password = "";
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Register()
    {
    }

    public String checkCredentials()
    {
        if (((UserDTO) UserCommandFactory.createCommand(UserCommandFactory.GET_USER_DETAILS, username, password).execute()) == null)
        {
            if (((boolean) UserCommandFactory.createCommand(UserCommandFactory.ADD_USER, new UserDTO( forename, surname, username, password, false)).execute()))
            {
                return "index?faces-redirect=true";
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR: Login credentials are not correct"));
            }
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR: Username already taken"));
        }
        
        return null;
    }
}
