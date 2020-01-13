package managedbean;

import PersonUI.PersonCommandFactory;
import dto.CartItemDTO;
import dto.OrderDTO;
import dto.OrderItemDTO;
import dto.PersonDTO;
import dto.StockDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "cart")
@SessionScoped
public class CartBean implements Serializable
{

    @Inject
    PersonBean user;

    private ArrayList<CartItemDTO> items = new ArrayList<>();
    private int count;
    private double price;
    
    public double getPrice()
    {
        double res = 0;
        
        for (CartItemDTO item : items)
        {
            res += item.getStock().getProduct().getPrice() * item.getQty();
        }
        
        return res;
    }
    
//    public String displayPrice()
//    {
//        return String.format("%.2f", getPrice());
//    }

    public int getItemsCount()
    {
        return items.size();
    }

    public ArrayList<CartItemDTO> getItems()
    {
        return items;
    }

    public CartBean()
    {

    }

    public String addToCart(StockDTO i)
    {
        String res = "cart?faces-redirect=true";
        
        for (CartItemDTO item : items)
        {
            if (item.getStockId() == i.getId())
            {
                item.add();

                return res;
            }
        }
        CartItemDTO cI = new CartItemDTO(i.getId(), 1);
        
        items.add(cI);

        return res;
    }
    
    public String orderItems(PersonDTO p)
    {
        System.out.println("Id: " + p.getId());
        
        if  ((boolean) PersonCommandFactory.createCommand(PersonCommandFactory.ADD_ORDER, new OrderDTO(-1, p, items)).execute())
        {
            return "index?faces-redirect=true";
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR: Unable to create order"));
            return null;
        }
    }

}
