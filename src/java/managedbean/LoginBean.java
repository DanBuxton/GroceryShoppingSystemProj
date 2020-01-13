package managedbean;

import dto.PersonDTO;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import PersonUI.PersonCommandFactory;

@Named(value = "login")
@RequestScoped
public class LoginBean
{
    
    @Inject
    PersonBean user;

    private String username;
    private String password;

    public LoginBean()
    {
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
        }
    }

    public String checkCredentials()
    {
        user.setPersonDetails((PersonDTO) PersonCommandFactory.createCommand(PersonCommandFactory.GET_PERSON, username, password).execute());
        
//        return user.checkCredentials();

        if (user.credentialsAreOK())
        {
            return "index?faces-redirect=true";
        }
        else
        {
            clearCredentials();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login credentials are not correct"));
            return null;
        }
    }

    private void clearCredentials()
    {
        this.username = "";
        this.password = "";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

}
