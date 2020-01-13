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
public class StockDTO
{
    private long id;
    private StoreDTO store;
    private ProductDTO product;
    private int qty;

    /**
     * 
     * @param store
     * @param product
     * @param qty 
     * 
     * @deprecated 
     */
    public StockDTO(StoreDTO store, ProductDTO product, int qty)
    {
        this.store = store;
        this.product = product;
        this.qty = qty;
    }
    public StockDTO(long id, StoreDTO store, ProductDTO product, int qty)
    {
        this.id = id;
        this.store = store;
        this.product = product;
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

    public StoreDTO getStore()
    {
        return store;
    }

    public void setStore(StoreDTO store)
    {
        this.store = store;
    }

    public ProductDTO getProduct()
    {
        return product;
    }

    public void setProduct(ProductDTO product)
    {
        this.product = product;
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
