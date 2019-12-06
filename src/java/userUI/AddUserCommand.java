package userUI;

import dto.UserDTO;
import manager.UserManager;

public class AddUserCommand implements IUserCommand
{

    private final UserDTO user;
    
    public AddUserCommand(UserDTO user)
    {
        this.user = user;
    }

    @Override
    public Object execute()
    {
        return new UserManager().insertUser(user);
    }

}
