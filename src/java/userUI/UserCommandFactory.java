package userUI;

import dto.UserDTO;

public class UserCommandFactory
{
    public static final int ADD_USER = 1;
    public static final int GET_USER_DETAILS = 2;
    public static final int UPDATE_USER_DETAILS = 3;
    public static final int REMOVE_USER = 4;
    
    public static IUserCommand createCommand(int commandType, UserDTO user)
    {
        switch (commandType)
        {
            case ADD_USER:
                return new AddUserCommand(user);
            case UPDATE_USER_DETAILS:
                return new UpdateUserCommand(user);
            default:
                return null;
        }
    }
    
    public static IUserCommand createCommand(int commandType, int id)
    {
        switch (commandType)
        {
            case GET_USER_DETAILS:
                return new GetUserDetailsCommand(id);
            case REMOVE_USER:
                return new RemoveUserCommand(id);
            default:
                return null;
        }
    }
    public static IUserCommand createCommand(int commandType, String username, String password)
    {
        switch (commandType)
        {
            case GET_USER_DETAILS:
                return new GetUserDetailsCommand(username, password);
            default:
                return null;
        }
    }
    
}
