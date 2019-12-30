/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonUI;

import manager.UserManager;

import dto.PersonDTO;

/**
 *
 * @author b018939i
 */
public class RemovePersonCommand implements ICommand
{
    
    private final int id;

    public RemovePersonCommand(int id)
    {
        this.id = id;
    }

    @Override
    public Object execute()
    {
        return new UserManager().removeCustomer(id);
    }
    
}
