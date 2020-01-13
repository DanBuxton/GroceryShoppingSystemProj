/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonUI;

import dto.OrderDTO;
import manager.OrderManager;

/**
 *
 * @author apple
 */
public class AddOrderCommand implements ICommand
{
    
    private final OrderDTO o;

    public AddOrderCommand(OrderDTO o)
    {
        this.o = o;
    }

    @Override
    public Object execute()
    {
        return new OrderManager().addOrder(o);
    }
    
}
