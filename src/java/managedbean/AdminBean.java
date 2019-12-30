/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import PersonUI.UserCommandFactory;
import dto.PersonDTO;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import manager.UserManager;

/**
 *
 * @author apple
 */
@Named(value = "adminBean")
@RequestScoped
public class AdminBean
{
    private ArrayList<PersonDTO> users;
    /**
     * Creates a new instance of AdminBean
     */
    public AdminBean()
    {
    }

    public ArrayList<PersonDTO> getUsers()
    {
        return (ArrayList<PersonDTO>) UserCommandFactory.createCommand(UserCommandFactory.GET_USERS).execute();
    }

}
