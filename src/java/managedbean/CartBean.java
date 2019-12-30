package managedbean;

import dto.StockDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "cart")
@SessionScoped
public class CartBean implements Serializable
{
    
    @Inject
    PersonBean user;
    
    private ArrayList<StockDTO> items;
    
    public CartBean()
    {
        
    }
    
}
