package dto;

public class StoreDTO
{
    private long id;
    private String name;
    private ProductDTO[] items;

    public StoreDTO(int id, String name, ProductDTO... items)
    {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ProductDTO[] getItems()
    {
        return items;
    }

    public void setItems(ProductDTO[] items)
    {
        this.items = items;
    }
}
