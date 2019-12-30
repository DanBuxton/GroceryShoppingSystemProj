package managedbean;

import dto.PersonDTO;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "person")
@SessionScoped
public class PersonBean implements Serializable
{

    private PersonDTO userDetails = null;

    public PersonDTO getUserDetails()
    {
        return userDetails;
    }
    private boolean credentialsOK = false;

    public PersonBean()
    {

    }

    public void setCustomerDetails(PersonDTO userDetails)
    {
        this.userDetails = userDetails;

        if (userDetails != null)
        {
            credentialsOK = true;
        }
        else
        {
            clearCredentials();
        }
    }

    public String checkCredentials()
    {
        if (credentialsAreOK())
        {
            if (!userDetails.isAnAdmin())
            {
                return "index?faces-redirect=true";
            }
            else
            {
                return "adminView?faces-redirect=true";
            }
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
    
    public String getAddress()
    {
        return userDetails.getAddress();
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
