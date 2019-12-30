/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonUI;

import dto.PersonDTO;
import manager.OrderManager;

/**
 *
 * @author apple
 */
public class GerOrderDetailsCommand implements ICommand
{
    
    private final PersonDTO p;

    public GerOrderDetailsCommand(PersonDTO user)
    {
        p = user;
    }

    @Override
    public Object execute()
    {
//        return new OrderManager().getOrders()
        return null;
    }
    
}
