/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import PersonUI.PersonCommandFactory;
import dto.StockDTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author apple
 */
@Named(value = "stockBean")
@SessionScoped
public class StockBean implements Serializable
{
    
    private StockDTO stock;

    public StockDTO getStock()
    {
        return stock;
    }

    public void setStock(StockDTO stock)
    {
        this.stock = stock;
    }
    
    public StockBean()
    {
    }
    
    public ArrayList<StockDTO> getSomeStock()
    {
        return (ArrayList<StockDTO>) PersonCommandFactory.createCommand(PersonCommandFactory.GET_SOME_STOCKS).execute();
    }
    
    public ArrayList<StockDTO> getStocks()
    {
        return (ArrayList<StockDTO>) PersonCommandFactory.createCommand(PersonCommandFactory.GET_STOCKS).execute();
    }
    public String viewStock(long stockId)
    {
        stock = (StockDTO) PersonCommandFactory.createCommand(PersonCommandFactory.GET_STOCK, stockId).execute();
        
        return "viewStock?faces-redirect=true";
    }
    
    public String editStock(long stockId)
    {
        stock = (StockDTO) PersonCommandFactory.createCommand(PersonCommandFactory.GET_STOCK, stockId).execute();
        
        System.out.println("Stock ID: " + stock.getId());
        
        return "editStock?faces-redirect=true";
    }
    
    public String removeStock(long stockId)
    {
        if ((boolean) PersonCommandFactory.createCommand(PersonCommandFactory.REMOVE_STOCK, stockId).execute())
        {
            return "index?faces-redirect=true";
        }
        
        return null;
    }
    
}
