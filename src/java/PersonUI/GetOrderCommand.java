/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonUI;

import dto.PersonDTO;
import manager.OrderManager;

public class GetOrderCommand implements ICommand
{

    private final long id;
    private final PersonDTO p;
    
    public GetOrderCommand()
    {
        this.id = Long.MIN_VALUE;
        p = null;
    }
    public GetOrderCommand(long id)
    {
        this.id = id;
        p = null;
    }
    public GetOrderCommand(PersonDTO p)
    {
        this.id = Long.MAX_VALUE;
        this.p = p;
    }

    @Override
    public Object execute()
    {
        if (p != null)
        {
            return new OrderManager().getOrders(p);
        }
        else if (id != Long.MIN_VALUE)
        {
            return new OrderManager().getOrder(id);
        }
        else
        {
            return new OrderManager().getOrders();
        }
    }
    
}
