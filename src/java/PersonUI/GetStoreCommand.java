/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonUI;

import manager.StoreManager;

/**
 *
 * @author apple
 */
public class GetStoreCommand implements ICommand
{
    
    private final long id;

    public GetStoreCommand()
    {
        id = Long.MAX_VALUE;
    }
    public GetStoreCommand(long id)
    {
        this.id = id;
    }

    @Override
    public Object execute()
    {
        if (id != Long.MAX_VALUE)
        {
            return new StoreManager().getStores();
        }
        else
        {
            return new StoreManager().getStore(id);
        }
    }
    
}
