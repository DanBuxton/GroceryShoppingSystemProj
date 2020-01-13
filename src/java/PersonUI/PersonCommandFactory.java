package PersonUI;

import dto.*;
import manager.OrderManager;

public class PersonCommandFactory
{
    public static final int ADD_PERSON = 0;
    public static final int GET_PERSON = 1;
    public static final int UPDATE_PERSON = 2;
    public static final int REMOVE_PERSON = 3;
    public static final int GET_PEOPLE = 4;
    public static final int GET_ADMINS = 5;
    
    public static final int ADD_ORDER = 10;
    public static final int GET_ORDER = 11;
//    public static final int UPDATE_ORDER = 12;
    public static final int REMOVE_ORDER = 13;
    public static final int GET_ORDERS = 14;
    
    public static final int ADD_STORE = 20;
    public static final int GET_STORE = 21;
    public static final int UPDATE_STORE = 22;
    public static final int REMOVE_STORE = 23;
    public static final int GET_STORES = 24;
    
    public static final int ADD_STOCK = 30;
    public static final int GET_STOCK = 31;
    public static final int UPDATE_STOCK = 32;
    public static final int REMOVE_STOCK = 33;
    public static final int GET_STOCKS = 34;
    public static final int GET_SOME_STOCKS = 35;
    public static final int GET_STOCK_PRICE = 36;
    
    public static final int ADD_PRODUCT = 40;
    public static final int GET_PRODUCT = 41;
    public static final int UPDATE_PRODUCT = 42;
    public static final int REMOVE_PRODUCT = 43;
    public static final int GET_PRODUCTS = 44;
    
    public static ICommand createCommand(int commandType)
    {
        switch (commandType)
        {
            case GET_PEOPLE:
                return new GetPersonCommand();
            case GET_ORDERS:
                return new GetOrderCommand();
            case GET_ADMINS:
                return new GetPersonCommand(true);
            case GET_STOCKS:
                return new GetStockCommand(true);
            case GET_SOME_STOCKS:
                return new GetStockCommand(false);
            case GET_STORES:
                return new GetStoreCommand();
            case GET_PRODUCTS:
                return new GetProductCommand();
            default:
                return null;
        }
    }
    
    public static ICommand createCommand(int commandType, long id)
    {
        switch (commandType)
        {
            case GET_PERSON:
                return new GetPersonCommand(id);
            case REMOVE_PERSON:
                return new RemovePersonCommand(id);
            case GET_ORDER:
                return new GetOrderCommand(id);
            case REMOVE_ORDER:
                return new RemovePersonCommand(id);
            case GET_STORE:
                return new GetStoreCommand();
            case REMOVE_STORE:
                return new GetStoreCommand(); //////////////////////////
            case GET_STOCK:
                return new GetStockCommand(id);
            case REMOVE_STOCK:
                return new RemoveStockCommand(id); ////////////////////////
            case GET_STOCK_PRICE:
                return new GetStockPriceCommand(id);
            case GET_PRODUCT:
                return new GetProductCommand(id);
            case REMOVE_PRODUCT:
                return new RemoveProductCommand(id);
            default:
                return null;
        }
    }
    public static ICommand createCommand(int commandType, String username, String password)
    {
        switch (commandType)
        {
            case GET_PERSON:
                return new GetPersonCommand(username, password);
            default:
                return null;
        }
    }
    public static ICommand createCommand(int commandType, String username)
    {
        switch (commandType)
        {
            case GET_PERSON:
                return new GetPersonCommand(username);
            default:
                return null;
        }
    }
    
    public static ICommand createCommand(int commandType, PersonDTO user)
    {
        switch (commandType)
        {
            case ADD_PERSON:
                return new AddPersonCommand(user);
            case UPDATE_PERSON:
                return new UpdatePersonCommand(user);
            case GET_ORDERS:
                return new GetOrderCommand(user);
            case REMOVE_PERSON:
                return new RemovePersonCommand(user);
            default:
                return null;
        }
    }
    public static ICommand createCommand(int commandType, OrderDTO o)
    {
        switch (commandType)
        {
            case ADD_ORDER:
                return new AddOrderCommand(o);
            default:
                return null;
        }
    }
    public static ICommand createCommand(int commandType, StoreDTO s)
    {
        switch (commandType)
        {
            case ADD_STORE:
                return null; //////////////////////////////////////////////////
            case UPDATE_STORE:
                return null; //////////////////////////////////////////////////
            default:
                return null;
        }
    }
    public static ICommand createCommand(int commandType, StockDTO s)
    {
        switch (commandType)
        {
            case ADD_STOCK:
                return new AddStockCommand(s);
            case UPDATE_STOCK:
                return new UpdateStockCommand(s);
            default:
                return null;
        }
    }
    public static ICommand createCommand(int commandType, ProductDTO p)
    {
        switch (commandType)
        {
            case ADD_PRODUCT:
                return new AddProductCommand(p);
            case UPDATE_PRODUCT:
                return new UpdateProductCommand(p);
            case REMOVE_PRODUCT:
                return null; //////////////////////////////////////////////////
            default:
                return null;
        }
    }
    
}
