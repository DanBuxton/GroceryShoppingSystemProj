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

    private PersonDTO personDetails = null;

    public PersonDTO getUserDetails()
    {
        return personDetails;
    }
    private boolean credentialsOK = false;

    public PersonBean()
    {

    }

    public void setPersonDetails(PersonDTO userDetails)
    {
        this.personDetails = userDetails;

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
            if (!personDetails.isAnAdmin())
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
        personDetails = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public boolean credentialsAreOK()
    {
        return credentialsOK;
    }

    public long getId()
    {
        return personDetails.getId();
    }

    public String getUsername()
    {
        return personDetails.getUsername();
    }

    public String getPassword()
    {
        return personDetails.getPassword();
    }
    
    public String getAddress()
    {
        return personDetails.getAddress();
    }

    public String logOff()
    {
        clearCredentials();
        return "login?faces-redirect=true";
    }

    public void setUsername(String username)
    {
        personDetails.setUsername(username);
    }

    public void setPassword(String password)
    {
        personDetails.setUsername(password);
    }

}
