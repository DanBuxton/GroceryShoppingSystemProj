/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import PersonUI.PersonCommandFactory;
import java.io.Serializable;

/**
 *
 * @author apple
 */
public class CartItemDTO implements Serializable
{
    private long stockId;
    private int qty;
    private double price;

    public CartItemDTO(long stockId, int qty)
    {
        this.stockId = stockId;
        this.qty = qty;
    }

    public long getStockId()
    {
        return stockId;
    }

    public int getQty()
    {
        return qty;
    }
    
    public void add()
    {
        ++qty;
    }
    
    public StockDTO getStock()
    {
        return (StockDTO) PersonCommandFactory.createCommand(PersonCommandFactory.GET_STOCK, stockId).execute();
    }

    public double getPrice()
    {
        StockDTO stock = (StockDTO) PersonCommandFactory.createCommand(PersonCommandFactory.GET_STOCK, stockId).execute();
        
        return stock.getProduct().getPrice() * qty;
    }

//    public String displayPrice()
//    {
//        return String.format("%.2f", getPrice() * qty);
//    }
}
