/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonUI;

import dto.ProductDTO;
import manager.ProductManager;

/**
 *
 * @author apple
 */
public class UpdateProductCommand implements ICommand
{
    
    private ProductDTO p;

    public UpdateProductCommand(ProductDTO p)
    {
        this.p = p;
    }

    @Override
    public Object execute()
    {
        return new ProductManager().updateProduct(p);
    }
    
}
