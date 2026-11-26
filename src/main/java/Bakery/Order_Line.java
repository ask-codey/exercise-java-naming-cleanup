package Bakery;

/*
 * One line of an order: a product, how many, and the price of one.
 *
 * TO FINISH: rename this class, its variables, its constructor's parameters and its methods to Java's standard.
 * The README's table lists the name each one should have. Use Rename (Shift+F6 in IntelliJ IDEA), never retyping.
 */
public class Order_Line {

    private final String Product;
    private final int qty;
    private final double UNIT_PRICE;

    public Order_Line(String product_name, int Qty, double unit_price) {
        this.Product = product_name;
        this.qty = Qty;
        this.UNIT_PRICE = unit_price;
    }

    public String Product() {
        return Product;
    }

    public int get_qty() {
        return qty;
    }

    public double GetUnitPrice() {
        return UNIT_PRICE;
    }

    public double Line_Total() {
        return qty * UNIT_PRICE;
    }
}
