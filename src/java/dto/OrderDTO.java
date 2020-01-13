/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author apple
 */
public class OrderDTO
{
    private long id;
    private PersonDTO person;
    private OrderItemDTO[] items;
    private double price;
    private GregorianCalendar dateOrdered;
    
    public OrderDTO(long id, PersonDTO person, OrderItemDTO... items)
    {
        this.id = id;
        this.person = person;
        this.items = items;
        dateOrdered = new GregorianCalendar(Locale.UK);
    }
    public OrderDTO(long id, PersonDTO person, ArrayList<CartItemDTO> items)
    {
        this.id = id;
        this.person = person;
        this.items = new OrderItemDTO[items.size()];
        for (int i = 0; i < items.size(); i++)
        {
            this.items[i] = new OrderItemDTO(-1, items.get(i).getStock(), items.get(i).getQty());
            price += items.get(i).getPrice();
        }
        dateOrdered = new GregorianCalendar(Locale.UK);
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public PersonDTO getPerson()
    {
        return person;
    }

    public void setPerson(PersonDTO person)
    {
        this.person = person;
    }

    public GregorianCalendar getDateOrdered()
    {
        return dateOrdered;
    }

    public void setDateOrdered(GregorianCalendar dateOrdered)
    {
        this.dateOrdered = dateOrdered;
    }

    public OrderItemDTO[] getItems()
    {
        return items;
    }

    public void setItems(OrderItemDTO[] items)
    {
        this.items = items;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
}
