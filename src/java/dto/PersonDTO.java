package dto;

import java.io.Serializable;

public class PersonDTO implements Serializable
{
    private long id;
    private String forename;
    private String surname;
    private String username;
    private String password;
    private String address;
    private boolean anAdmin = false;

    public PersonDTO()
    {
    }

    /**
     * Create an instance of a Customer. 
     * @param forename 
     * @param surname 
     * @param username The user's unique password
     * @param password The user's hashed password. 
     * @param address The user's hashed password. 
     * 
     * @deprecated 
     */
    public PersonDTO(String forename, String surname, String username, String password, String address)
    {
        this.forename = forename;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.address = address;
        anAdmin = false;
    }

    /**
     * Create an instance of a Customer or Administrator. 
     * @param forename 
     * @param surname 
     * @param username The user's unique password
     * @param password The user's hashed password. 
     * @param isAnAdmin Set {@code true} if person is an Administrator. Otherwise {@code false}. 
     */
    public PersonDTO(String forename, String surname, String username, String password, String address, boolean isAnAdmin)
    {
        this.forename = forename;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.address = address;
        anAdmin = isAnAdmin;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

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

    public String getName()
    {
        return String.format("\"%s, %s\"", surname, forename);
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
        this.password = password;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public boolean isAnAdmin()
    {
        return anAdmin;
    }

    public void setAnAdmin(boolean anAdmin)
    {
        this.anAdmin = anAdmin;
    }

    @Override
    public String toString()
    {
        return id + ", " + getName() + ", " + username + ",\n" + 
                (isAnAdmin() ? "Administrator" : "Customer");
    }
}
