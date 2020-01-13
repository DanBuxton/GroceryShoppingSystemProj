/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import PersonUI.PersonCommandFactory;
import dto.OrderDTO;
import dto.PersonDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author apple
 */
@Named(value = "orderBean")
@SessionScoped
public class OrderBean implements Serializable
{
    /**
     * Creates a new instance of OrderBean
     */
    public OrderBean()
    {
    }
    
    public OrderDTO[] getAllOrders()
    {
        return (OrderDTO[]) PersonCommandFactory.createCommand(PersonCommandFactory.GET_ORDERS).execute();
    }
    
    public OrderDTO[] getOrders(long personId)
    {
        PersonDTO p = (PersonDTO) PersonCommandFactory.createCommand(PersonCommandFactory.GET_PERSON, personId).execute();
        
        return (OrderDTO[]) PersonCommandFactory.createCommand(PersonCommandFactory.GET_ORDERS, p).execute();
    }
    
    public OrderDTO[] getOrder(long orderId)
    {
        return (OrderDTO[]) PersonCommandFactory.createCommand(PersonCommandFactory.GET_ORDERS).execute();
    }
    
}
