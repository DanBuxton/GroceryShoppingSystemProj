/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author apple
 */
public class OrderItemDTO
{
    private long id;
//    private OrderDTO order;
    private StockDTO stock;
    private int qty;

    public OrderItemDTO(long id, StockDTO stock, int qty)
    {
        this.id = id;
//        this.order = order;
        this.stock = stock;
        this.qty = qty;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

//    public OrderDTO getOrder()
//    {
//        return order;
//    }
//
//    public void setOrder(OrderDTO order)
//    {
//        this.order = order;
//    }

    public StockDTO getStock()
    {
        return stock;
    }

    public void setStock(StockDTO stock)
    {
        this.stock = stock;
    }

    public int getQty()
    {
        return qty;
    }

    public void setQty(int qty)
    {
        this.qty = qty;
    }
}
