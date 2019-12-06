/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import dto.StockDTO;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import manager.StockManager;

/**
 *
 * @author apple
 */
@Named(value = "searchProducts")
@RequestScoped
public class SearchProducts
{

    /**
     * Creates a new instance of SearchProducts
     */
    public SearchProducts()
    {
    }
    
    public ArrayList<StockDTO> getStock()
    {
        return StockManager.getStock();
    }
    
}
