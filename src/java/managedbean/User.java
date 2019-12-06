package managedbean;

import dto.UserDTO;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "user")
@SessionScoped
public class User implements Serializable
{

    private UserDTO userDetails = null;
    private boolean credentialsOK = false;

    public User()
    {

    }

    public void setCustomerDetails(UserDTO userDetails)
    {
        this.userDetails = userDetails;
        
        credentialsOK = true;
    }

    public String checkCredentials()
    {
        if (credentialsAreOK())
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
        userDetails = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public boolean credentialsAreOK()
    {
        return credentialsOK;
    }

    public long getId()
    {
        return userDetails.getId();
    }

    public String getUsername()
    {
        return userDetails.getUsername();
    }

    public String getPassword()
    {
        return userDetails.getPassword();
    }

    public String logOff()
    {
        clearCredentials();
        return "login?faces-redirect=true";
    }

    public void setUsername(String username)
    {
        userDetails.setUsername(username);
    }

    public void setPassword(String password)
    {
        userDetails.setUsername(password);
    }

}
