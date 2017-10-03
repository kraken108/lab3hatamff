
package Model;

/**
 * Data representation of an item from the database.
 */
public class Item {
    private String name;
    private Float price;
    private int inStock;
    private int id;
    
    /**
     * Constructs a new Item object.
     * @param name
     * @param price
     * @param inStock How many of the items exists in stock.
     * @param id 
     */
    public Item(String name, Float price,int inStock,int id){
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.id = id;
    }

    /**
     * Constructs a new Item object using Strings as params.
     * @param name
     * @param price
     * @param inStock
     * @param id 
     */
    public Item(String name, Float price, String inStock, String id){
        this.name = name;
        this.price = price;
        
        String st = "";
        st += inStock.charAt(0);
        this.inStock = Integer.parseInt(st);
        
        String s = "";
        s += id.charAt(0);
        this.id = Integer.parseInt(s);
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the price
     */
    public Float getPrice() {
        return price;
    }
    
    /**
     * Constructs a string of the Item with all information about it.
     * @return 
     */
    @Override
    public String toString(){
        return "Product: " + getName() + " - Price:" + getPrice() + " - (" + getInStock() + ") in Stock";
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the inStock
     */
    public int getInStock() {
        return inStock;
    }
    
    
    
}
