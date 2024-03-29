package PersonUI;

import dto.PersonDTO;
import manager.PersonManager;

public class AddPersonCommand implements ICommand
{

    private final PersonDTO user;
    
    public AddPersonCommand(PersonDTO user)
    {
        this.user = user;
    }

    @Override
    public Object execute()
    {
        return new PersonManager().insertPerson(user);
    }

}
