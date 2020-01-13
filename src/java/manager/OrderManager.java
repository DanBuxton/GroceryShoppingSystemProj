/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.OrderDTO;
import dto.OrderItemDTO;
import dto.PersonDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderManager
{
    
    public boolean addOrder(OrderDTO o)
    {
        System.out.println("addOrder()");
        boolean res = false;
        
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("INSERT INTO ORDER (PERSON_ID, ORDER_PRICE, DATE_ORDERED) VALUES (?, ?, ?)"))
        {
            stmt.setLong(1, o.getPerson().getId());
            stmt.setDouble(2, o.getPrice());
            stmt.setDate(3, new Date(o.getDateOrdered().get(Calendar.YEAR), o.getDateOrdered().get(Calendar.MONTH), o.getDateOrdered().get(Calendar.DATE)));
            
            res = stmt.executeUpdate() == 1;
        }
        catch (Exception e)
        {
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        
        System.out.println("Res: " + res);
        
        return res;
    }

    public OrderDTO getOrder(long id)
    {
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT \"ORDER\".ORDER_ID, \"ORDER\".PERSON_ID, \"ORDER\".DATE_ORDERED, ORDERITEM.QTY, PRODUCT.PRODUCT_NAME, PRODUCT.PRICE FROM \"ORDER\""
                + "INNER JOIN ORDERITEM ON \"ORDER\".ORDER_ID = ORDERITEM.ORDER_ID"
                + "INNER JOIN STOCK ON STOCK.STOCK_ID = ORDERITEM.STOCK_ID"
                + "INNER JOIN PRODUCT ON PRODUCT.PRODUCT_ID = STOCK.PRODUCT_ID"
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
        OrderDTO[] orders = null;
        try
        {
            orders = new OrderDTO[countOrders()];
        }
        catch (Exception ex)
        {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (orders != null)
        {
            try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT \"ORDER\".ORDER_ID, \"ORDER\".PERSON_ID, \"ORDER\".DATE_ORDERED, ORDERITEM.QTY, PRODUCT.PRODUCT_NAME, PRODUCT.PRICE FROM \"ORDER\""
                    + "INNER JOIN ORDERITEM ON \"ORDER\".ORDER_ID = ORDERITEM.ORDER_ID"
                    + "INNER JOIN STOCK ON STOCK.STOCK_ID = ORDERITEM.STOCK_ID"
                    + "INNER JOIN PRODUCT ON PRODUCT.PRODUCT_ID = STOCK.PRODUCT_ID");)
            {
                ResultSet rs = stmt.executeQuery();

                for (int i = 0; i < orders.length && rs.next(); i++)
                {
                    long orderId = rs.getLong("ORDERID");
                    
                    OrderItemDTO[] oItems;
                    try
                    {
                        oItems = new OrderItemDTO[countOrderItems(orderId)];
                        
                        PreparedStatement stmt1 = conn.prepareStatement("SELECT * FROM ORDERITEM INNER JOIN STOCK ON STOCK.STOCKID = ORDERITEM.STOCKID INNER JOIN  WHERE ORDERITEM.ORDERID = ?");
                        stmt1.setLong(1, orderId);
                        
                        ResultSet rs1 = stmt.executeQuery();
                        
                        for (int j = 0; j < oItems.length && rs1.next(); j++)
                        {
                            oItems[i] = new OrderItemDTO(rs1.getLong("ORDERITEM_ID"), null, rs.getInt("QTY"));
                        }
                    }
                    catch (Exception ex)
                    {
                        Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                    }
                    
                    orders[i] = new OrderDTO(orderId, new PersonDTO(rs.getString("FORENAME"), rs.getString("SURNAME"), rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("ADDRESS"), rs.getBoolean("IS_ADMIN")), oItems);
                }
                
                rs.close();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return orders;
    }

    public OrderDTO[] getOrders(PersonDTO p)
    {
        OrderDTO[] orders = null;

        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT \"ORDER\".ORDER_ID, \"ORDER\".PERSON_ID, \"ORDER\".DATE_ORDERED, ORDERITEM.QTY, PRODUCT.PRODUCT_NAME, PRODUCT.PRICE FROM \"ORDER\" "
                + "INNER JOIN ORDERITEM ON \"ORDER\".ORDER_ID = ORDERITEM.ORDER_ID "
                + "INNER JOIN STOCK ON STOCK.STOCK_ID = ORDERITEM.STOCK_ID "
                + "INNER JOIN PRODUCT ON PRODUCT.PRODUCT_ID = STOCK.PRODUCT_ID "
                + "WHERE \"ORDER\".PersonID = ?");)
        {
            stmt.setLong(0, p.getId());

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

//        return new OrderDTO[] {orders};
        return orders;
    }

    public boolean updateOrder(OrderDTO order, OrderItemDTO[] items)
    {
        return false;
    }

    private static int countOrders() throws Exception
    {
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(ORDER_ID) FROM \"ORDER\"");)
        {
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                return rs.getInt("1");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        throw new Exception("bla bla bla");
    }
    
    private static int countOrderItems(long orderId) throws Exception
    {
        try (Connection conn = DbManager.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(ORDERITEM_ID) FROM \"ORDER\" WHERE ORDER_ID = ?");)
        {
            stmt.setLong(1, orderId);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                return rs.getInt("1");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        throw new Exception("bla bla bla");
    }

}
