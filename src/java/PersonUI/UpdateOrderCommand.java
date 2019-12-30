/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonUI;

import dto.OrderDTO;
import dto.OrderItemDTO;
import dto.PersonDTO;
import manager.OrderManager;

/**
 *
 * @author apple
 */
public class UpdateOrderCommand implements ICommand
{
    
    private final OrderDTO order;
    private final OrderItemDTO[] items;

    public UpdateOrderCommand(OrderDTO o, OrderItemDTO... i)
    {
        order = o;
        items = i;
    }

    @Override
    public Object execute()
    {
        return new OrderManager().updateOrder(order, items);
    }
    
}
