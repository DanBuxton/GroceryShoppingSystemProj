/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonUI;

import manager.UserManager;

/**
 *
 * @author apple
 */
public class GetPersonDetailsCommand implements ICommand
{
    
    private final int id;
    private final String username;
    private final String password;
    private boolean getAdmin;

    public GetPersonDetailsCommand()
    {
        id = -1;
        username = "";
        password = "";
    }
    public GetPersonDetailsCommand(boolean isAdmin)
    {
        getAdmin = true;
        id = -1;
        username = "";
        password = "";
    }
    
    public GetPersonDetailsCommand(int id)
    {
        this.id = id;
        username = "";
        password = "";
    }
    public GetPersonDetailsCommand(String username, String password)
    {
        id = Integer.MIN_VALUE;
        this.username = username;
        this.password = password;
    }
    
    @Override
    public Object execute()
    {
        if (getAdmin)
            return new UserManager().getAdmins();
        else if (!getAdmin)
            return new UserManager().getCustomers();
        else if (id == -1)
            return new UserManager().getUsers();
        else if (id == Integer.MIN_VALUE)
            return new UserManager().getCustomer(username, password);
        else if (username.equals("") && password.equals(""))
            return new UserManager().getCustomer(id);
        else
            return null;
    }
    
}
