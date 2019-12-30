package PersonUI;

import dto.PersonDTO;
import manager.UserManager;

public class UpdatePersonCommand implements ICommand
{

    private final PersonDTO user;

    public UpdatePersonCommand(PersonDTO user)
    {
        this.user = user;
    }

    @Override
    public Object execute()
    {
        return new UserManager().updateCustomer(user);
    }

}
