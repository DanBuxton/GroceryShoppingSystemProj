/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author apple
 */
@Named(value = "view")
@RequestScoped
public class ViewProductBean
{

    /**
     * Creates a new instance of ViewProduct
     */
    public ViewProductBean()
    {
    }
    
    public String view(long id)
    {
        
        return "";
    }
}
