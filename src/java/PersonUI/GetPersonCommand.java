/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonUI;

import manager.PersonManager;

/**
 *
 * @author apple
 */
public class GetPersonCommand implements ICommand
{
    
    private final long id;
    private final String username;
    private final String password;
    private boolean getAdmin;

    public GetPersonCommand()
    {
        id = -1;
        username = null;
        password = null;
    }
    public GetPersonCommand(boolean isAdmin)
    {
        getAdmin = true;
        id = -1;
        username = null;
        password = null;
    }
    
    public GetPersonCommand(long id)
    {
        this.id = id;
        username = null;
        password = null;
    }
    public GetPersonCommand(String username)
    {
        id = Long.MIN_VALUE;
        this.username = username;
        this.password = null;
    }
    public GetPersonCommand(String username, String password)
    {
        id = Long.MIN_VALUE;
        this.username = username;
        this.password = password;
    }
    
    @Override
    public Object execute()
    {
        if (getAdmin)
            return new PersonManager().getAdmins();
        else if (id == -1)
            return new PersonManager().getUsers();
        else if (id == Long.MIN_VALUE && password == null)
            return new PersonManager().getPerson(username);
        else if (id == Long.MIN_VALUE)
            return new PersonManager().getPerson(username);
        else if (username == null && password == null)
            return new PersonManager().getCustomer(id);
        else if (!getAdmin)
            return new PersonManager().getCustomers();
        else
            return null;
    }
    
}
