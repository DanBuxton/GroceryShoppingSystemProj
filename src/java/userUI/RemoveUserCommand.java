/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userUI;

import manager.UserManager;

import dto.UserDTO;

/**
 *
 * @author b018939i
 */
public class RemoveUserCommand implements IUserCommand
{
    
    private final int id;

    public RemoveUserCommand(int id)
    {
        this.id = id;
    }

    @Override
    public Object execute()
    {
        return new UserManager().removeUser(id);
    }
    
}
