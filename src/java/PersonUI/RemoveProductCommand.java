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
public class RemoveProductCommand implements ICommand
{
    
    private final long id;

    public RemoveProductCommand(long id)
    {
        this.id = id;
    }

    @Override
    public Object execute()
    {
        return new ProductManager().removeProduct(id);
    }
    
}
