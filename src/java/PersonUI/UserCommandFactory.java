package PersonUI;

import dto.PersonDTO;

public class UserCommandFactory
{
    public static final int ADD_PERSON = 0;
    public static final int GET_PERSON = 1;
    public static final int UPDATE_PERSON = 2;
    public static final int REMOVE_PERSON = 3;
    public static final int GET_USERS = 4;
    public static final int GET_ADMINS = 5;
    
    public static final int ADD_ORDER = 10;
    public static final int GET_ORDER = 11;
    public static final int UPDATE_ORDER = 12;
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
    
    public static final int ADD_PRODUCT = 40;
    public static final int GET_PRODUCT = 41;
    public static final int UPDATE_PRODUCT = 42;
    public static final int REMOVE_PRODUCT = 43;
    public static final int GET_PRODUCTS = 44;
    
    public static ICommand createCommand(int commandType)
    {
        switch (commandType)
        {
            case GET_USERS:
                return new GetPersonDetailsCommand();
            case GET_ORDERS:
                return new GetOrderDetailsCommand();
            case GET_ADMINS:
                return new GetPersonDetailsCommand(true);
            case GET_STOCKS:
                return new GetPersonDetailsCommand(true); /////////////////////
            case GET_SOME_STOCKS:
                return new GetPersonDetailsCommand(true); /////////////////////
            case GET_STORES:
                return new GetPersonDetailsCommand(true); /////////////////////
            case GET_STORE:
                return new GetPersonDetailsCommand(true); /////////////////////
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
                return new GerOrderDetailsCommand(user);
            default:
                return null;
        }
    }
    
    public static ICommand createCommand(int commandType, int id)
    {
        switch (commandType)
        {
            case GET_PERSON:
                return new GetPersonDetailsCommand(id);
            case REMOVE_PERSON:
                return new RemovePersonCommand(id);
            case GET_ORDER:
                return new GetPersonDetailsCommand(id); ///////////////////////
            case REMOVE_ORDER:
                return new RemovePersonCommand(id); ///////////////////////////
            default:
                return null;
        }
    }
    public static ICommand createCommand(int commandType, String username, String password)
    {
        switch (commandType)
        {
            case GET_PERSON:
                return new GetPersonDetailsCommand(username, password);
            default:
                return null;
        }
    }
    
}
