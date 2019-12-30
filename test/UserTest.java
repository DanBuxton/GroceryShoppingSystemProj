/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dto.PersonDTO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import PersonUI.UserCommandFactory;
import java.util.ArrayList;

/**
 *
 * @author apple
 */
public class UserTest
{

    ArrayList<PersonDTO> users;

    public UserTest()
    {
    }

    @Before
    public void before()
    {

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void GetUsers()
    {
        users = ((ArrayList<PersonDTO>) UserCommandFactory.createCommand(UserCommandFactory.GET_USERS).execute());

        users.forEach((user) ->
        {
            System.out.println(user);
        });
//        assertEquals("1", user.getId());
//        assertEquals("Daniel", user.getForename());
//        assertEquals("Buxton", user.getSurname());
//        assertEquals("DanBuxton", user.getUsername());
//        assertEquals("8OTC92xYkW7CWPJGhRvqCR0U1CR6L8PhhpRGGxgW4Ts=", user.getPassword());
    }
}
