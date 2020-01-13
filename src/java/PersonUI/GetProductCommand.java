/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonUI;

import manager.ProductManager;

/**
 *
 * @author apple
 */
public class GetProductCommand implements ICommand
{
    
    private final long id;

    public GetProductCommand()
    {
        id = Long.MAX_VALUE;
    }
    public GetProductCommand(long id)
    {
        this.id = id;
    }

    @Override
    public Object execute()
    {
        if (id == Long.MAX_VALUE)
        {
            return new ProductManager().getProducts();
        }
        else
        {
            return new ProductManager().getProduct(id);
        }
    }
    
}
