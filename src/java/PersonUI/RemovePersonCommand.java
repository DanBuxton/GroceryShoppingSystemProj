/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonUI;

import manager.PersonManager;

import dto.PersonDTO;

/**
 *
 * @author b018939i
 */
public class RemovePersonCommand implements ICommand
{
    
    private final long id;
    
    public RemovePersonCommand(long id)
    {
        this.id = id;
    }
    public RemovePersonCommand(PersonDTO p)
    {
        this.id = p.getId();
    }

    @Override
    public Object execute()
    {
        return new PersonManager().removeCustomer(id);
    }
    
}
