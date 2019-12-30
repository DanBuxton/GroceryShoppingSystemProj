/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import PersonUI.UserCommandFactory;
import dto.StockDTO;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "searchProducts")
@RequestScoped
public class SearchProductsBean
{
    
    private long stockId = 1;
    
    /**
     * Creates a new instance of SearchProducts
     */
    public SearchProductsBean()
    {
    }

    public long getStockId()
    {
        return stockId;
    }

    public void setStockId(long stockId)
    {
        this.stockId = stockId;
    }
    
    public StockDTO[] getSomeStock()
    {
        return (StockDTO[]) UserCommandFactory.createCommand(UserCommandFactory.GET_SOME_STOCKS).execute();
    }
    
    public StockDTO[] getStock()
    {
        return (StockDTO[]) UserCommandFactory.createCommand(UserCommandFactory.GET_STOCKS).execute();
    }
    
    public StockDTO[] getProduct()
    {
        return (StockDTO[]) UserCommandFactory.createCommand(UserCommandFactory.GET_STOCKS).execute();
    }
    
    public String viewProduct(long id)
    {
        return "viewProduct?faces-redirect=true";
    }
    
}
