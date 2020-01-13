/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import PersonUI.PersonCommandFactory;
import dto.ProductDTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author apple
 */
@Named(value = "product")
@SessionScoped
public class ProductBean implements Serializable
{
    
    private ProductDTO product;

    public ProductDTO getProduct()
    {
        return product;
    }

    public void setProduct(ProductDTO product)
    {
        this.product = product;
    }
    
    public ProductBean()
    {
    }
    
    public String viewProduct(long productId)
    {
        product = (ProductDTO) PersonCommandFactory.createCommand(PersonCommandFactory.GET_PRODUCT, productId).execute();
        
        return "viewProduct?faces-redirect=true";
    }
    
    public String editProduct(long productId)
    {
        product = (ProductDTO) PersonCommandFactory.createCommand(PersonCommandFactory.GET_PRODUCT, productId).execute();
        
        return "editProduct?faces-redirect=true";
    }
    
    public String updateProduct()
    {
        if ((boolean) PersonCommandFactory.createCommand(PersonCommandFactory.UPDATE_PRODUCT, product).execute())
            return "index?faces-redirect=true";
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Issue updating product"));
            return null;
        }
    }
    
    public String removeProduct(long productId)
    {
        if ((boolean) PersonCommandFactory.createCommand(PersonCommandFactory.REMOVE_PRODUCT, product.getId()).execute())
            return "index?faces-redirect=true";
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Issue removing product"));
            return null;
        }
    }
    
}
