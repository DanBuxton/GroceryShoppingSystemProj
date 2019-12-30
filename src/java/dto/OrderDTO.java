/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author apple
 */
public class OrderDTO
{
    private long id;
    private PersonDTO person;

    public OrderDTO(PersonDTO person)
    {
        this.person = person;
    }
    public OrderDTO(long id, PersonDTO person)
    {
        this.id = id;
        this.person = person;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public PersonDTO getPerson()
    {
        return person;
    }

    public void setPerson(PersonDTO person)
    {
        this.person = person;
    }
}
