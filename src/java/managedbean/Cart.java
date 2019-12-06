package managedbean;

import dto.StockItem;
import dto.UserDTO;
import java.util.ArrayList;
import javax.inject.Inject;

public class Cart
{
    
    @Inject
    User user;
    
    private ArrayList<StockItem> items;
    
    public Cart()
    {
        
    }
    
}
