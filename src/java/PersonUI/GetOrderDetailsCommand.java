/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonUI;

import manager.OrderManager;

/**
 *
 * @author apple
 */
public class GetOrderDetailsCommand implements ICommand
{

    private int id;
    
    public GetOrderDetailsCommand()
    {
        id = Integer.MIN_VALUE;
    }

    @Override
    public Object execute()
    {
        if (id == Integer.MIN_VALUE)
        {
            return new OrderManager().getOrders();
        }
        else
        {
            return new OrderManager().getOrder(id);
        }
    }
    
}
