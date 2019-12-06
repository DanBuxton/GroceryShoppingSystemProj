package dto;

public class UserDTO
{
    private long id;
    private String forename;
    private String surname;
    private String username;
    private String password;
    private boolean anAdmin = false;

    public UserDTO()
    {
    }

    public UserDTO(String forename, String surname, String username, String password, boolean isAnAdmin)
    {
        this.forename = forename;
        this.surname = surname;
        this.username = username;
        this.password = password;
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
        return String.format("%s, %s", surname, forename);
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

    public boolean isAnAdmin()
    {
        return anAdmin;
    }

    public void setAnAdmin(boolean anAdmin)
    {
        this.anAdmin = anAdmin;
    }
}
