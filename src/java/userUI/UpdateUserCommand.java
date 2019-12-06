package userUI;

import dto.UserDTO;
import manager.UserManager;

public class UpdateUserCommand implements IUserCommand
{
    
    private final UserDTO user;

    public UpdateUserCommand(UserDTO user)
    {
        this.user = user;
    }

    @Override
    public Object execute()
    {
        return new UserManager().updateUser(user);
    }
    
    
    
}
