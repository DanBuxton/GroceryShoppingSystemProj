package managedbean;

import dto.PersonDTO;
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
import PersonUI.PersonCommandFactory;

@Named(value = "register")
@RequestScoped
public class RegisterBean
{

    private String forename;
    private String surname;
    private String username;
    private String password;
    private String address;

    public String getForename()
    {
        return forename;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getAddress()
    {
        return address;
    }

    public void setForename(String forename)
    {
        this.forename = forename;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public void setUsername(String username)
    {
        this.username = username;
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
            Logger.getLogger(PersonBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public RegisterBean()
    {
    }

    public String checkCredentials()
    {
        if (PersonCommandFactory.createCommand(PersonCommandFactory.GET_PERSON, username).execute() == null)
        {
            if ((boolean) PersonCommandFactory.createCommand(PersonCommandFactory.ADD_PERSON, new PersonDTO( forename, surname, username, password, address, false)).execute())
            {
                return "login?faces-redirect=true";
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
