/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonUI;

import dto.StockDTO;
import manager.StockManager;

/**
 *
 * @author apple
 */
public class UpdateStockCommand implements ICommand
{
    private final StockDTO s;

    public UpdateStockCommand(StockDTO s)
    {
        this.s = s;
    }

    @Override
    public Object execute()
    {
        return new StockManager().updateStock(s);
    }
    
}
