/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import PersonUI.PersonCommandFactory;
import dto.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import managedbean.CartBean;
import org.junit.*;
import static org.junit.Assert.*;

public class GroceryTest
{

    public GroceryTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void UT01() // Get person (customer/admin)
    {
        PersonDTO user = (PersonDTO) PersonCommandFactory.createCommand(PersonCommandFactory.GET_PERSON, "DanBuxton").execute();

        assertEquals("DanBuxton", user.getUsername());
    }

    @Test
    public void IT02() // Register Customer
    {
        try
        {
            String password = Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest("asdf".getBytes(StandardCharsets.UTF_8)));
            PersonDTO p = new PersonDTO("Ben", "Mortom", "b.m", password, "Stoke", false);
            if ((boolean) PersonCommandFactory.createCommand(PersonCommandFactory.ADD_PERSON, p).execute())
            {
                assertEquals(p.toString(), ((PersonDTO) PersonCommandFactory.createCommand(PersonCommandFactory.GET_PERSON, p.getUsername()).execute()).toString());
                PersonCommandFactory.createCommand(PersonCommandFactory.REMOVE_PERSON, p).execute();
            }
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(GroceryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void IT03() // Register Admin
    {
        try
        {
            String password = Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest("asdf".getBytes(StandardCharsets.UTF_8)));
            PersonDTO p = new PersonDTO("Katie", "Radford", "k.r", password, "House 8 Room D", true);
            if ((boolean) PersonCommandFactory.createCommand(PersonCommandFactory.ADD_PERSON, p).execute())
            {
                assertEquals(p.toString(), ((PersonDTO) PersonCommandFactory.createCommand(PersonCommandFactory.GET_PERSON, p.getUsername()).execute()).toString());
                PersonCommandFactory.createCommand(PersonCommandFactory.REMOVE_PERSON, p).execute();
            }
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(GroceryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void UT04() // Remove person (customer/admin)
    {
        try
        {
            String password = Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest("asdf".getBytes(StandardCharsets.UTF_8)));
            PersonDTO p = new PersonDTO("Katie", "Radford", "k.r", password, "House 8 Room D", true);
            if ((boolean) PersonCommandFactory.createCommand(PersonCommandFactory.ADD_PERSON, p).execute())
            {
                p = (PersonDTO) PersonCommandFactory.createCommand(PersonCommandFactory.GET_PERSON, p.getUsername()).execute();

                assertEquals(true, (boolean) PersonCommandFactory.createCommand(PersonCommandFactory.REMOVE_PERSON, p).execute());
            }
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(GroceryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void UT05() // Add Stock to cart. 
    {
        try
        {
            CartBean cart = new CartBean();
            cart.addToCart((StockDTO) PersonCommandFactory.createCommand(PersonCommandFactory.GET_STOCK, 1).execute());

            assertEquals(((StockDTO) PersonCommandFactory.createCommand(PersonCommandFactory.GET_STOCK, 1).execute()).getProduct().getPrice(), cart.getItems().get(0).getStock().getProduct().getPrice(), 0);
        }
        catch (Exception e)
        {
            Logger.getLogger(GroceryTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Test
    public void IT06() // Add Stock. 
    {
        try
        {
        }
        catch (Exception e)
        {
            Logger.getLogger(GroceryTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Test
    public void UT07() // Get stock price
    {
        double price = (double) PersonCommandFactory.createCommand(PersonCommandFactory.GET_STOCK_PRICE, 4).execute();
        
        assertEquals(2.49, price, 0);
    }
}
