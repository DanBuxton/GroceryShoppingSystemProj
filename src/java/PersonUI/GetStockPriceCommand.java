/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonUI;

import manager.StockManager;

/**
 *
 * @author apple
 */
public class GetStockPriceCommand implements ICommand
{

    private final long id;
    
    public GetStockPriceCommand(long id)
    {
        this.id = id;
    }

    @Override
    public Object execute()
    {
        return new StockManager().getStockPrice(id);
    }
    
}
