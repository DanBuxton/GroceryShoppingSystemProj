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
public class GetStockCommand implements ICommand
{

    private final long id;

    public GetStockCommand(long id)
    {
        this.id = id;
    }

    public GetStockCommand(boolean all)
    {
        if (all)
        {
            this.id = Long.MAX_VALUE;
        }
        else
        {
            this.id = Long.MIN_VALUE;
        }
    }

    @Override
    public Object execute()
    {
        if (id == Long.MAX_VALUE)
        {
            return new StockManager().getAllStock();
        }
        else if (id == Long.MIN_VALUE)
        {
            return new StockManager().getSomeStock();
        }
        else;
        {
            return new StockManager().getStock(id);
        }
    }

}
