/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.OrderDTO;
import dto.OrderItemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderManager
{

    public OrderDTO getOrder(long id)
    {
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT \"ORDER\".ORDER_ID, \"ORDER\".PERSON_ID, \"ORDER\".DATE_ORDERED, ORDERITEM.QTY, PRODUCT.PRODUCT_NAME, PRODUCT.PRICE FROM \"ORDER\"" +
"INNER JOIN ORDERITEM ON \"ORDER\".ORDER_ID = ORDERITEM.ORDER_ID" +
"INNER JOIN STOCK ON STOCK.STOCK_ID = ORDERITEM.STOCK_ID" +
"INNER JOIN PRODUCT ON PRODUCT.PRODUCT_ID = STOCK.PRODUCT_ID"
                + "WHERE \"ORDER\" = ?");)
        {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next())
            {
//                return new OrderDTO();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public OrderDTO[] getOrders()
    {
        ArrayList<OrderDTO> orders = new ArrayList<>();
        
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("");)
        {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
//                orders.add(new OrderDTO());
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (OrderDTO[]) orders.toArray();
    }

    public boolean updateOrder(OrderDTO order, OrderItemDTO[] items)
    {
        return false;
    }
    
}
