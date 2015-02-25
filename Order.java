package fos;

public class Order {
    
    private Box box;
    private int quantity;
    
    public Order(Box box, int quantity) { // get the ordered box and the user defined quantity
        this.box = box;
        this.quantity = quantity;
    }
        
    protected double cost() { // calculate the order cost by the ordered box cost multiplied by the quantity
        return box.cost() * quantity;
    }
    
}