/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userUI;

import manager.UserManager;

/**
 *
 * @author apple
 */
public class GetUserDetailsCommand implements IUserCommand
{
    
    private final int id;
    private final String username;
    private final String password;

    public GetUserDetailsCommand(int id)
    {
        this.id = id;
        username = "";
        password = "";
    }
    public GetUserDetailsCommand(String username, String password)
    {
        id = Integer.MIN_VALUE;
        this.username = username;
        this.password = password;
    }
    
    @Override
    public Object execute()
    {
        if (id == Integer.MIN_VALUE)
            return new UserManager().getUser(username, password);
        else if (username.equals("") && password.equals(""))
            return new UserManager().getUser(id);
        else
            return null;
    }
    
}
