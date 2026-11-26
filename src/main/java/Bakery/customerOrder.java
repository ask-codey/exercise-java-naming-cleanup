package Bakery;

import java.util.ArrayList;
import java.util.List;

/*
 * One customer's order: who placed it, its lines, the total with sales tax, and whether it's paid.
 *
 * TO FINISH: rename this class, its constant, its variables, its parameters and its methods to Java's standard.
 * The README's table lists the name each one should have. Use Rename (Shift+F6 in IntelliJ IDEA), never retyping.
 */
public class customerOrder {

    public static final double salesTaxRate = 0.08;

    private final String customer_name;
    private final List<Order_Line> Lines = new ArrayList<>();
    private boolean PAID;

    public customerOrder(String CustomerName) {
        this.customer_name = CustomerName;
    }

    public String getCustomer_Name() {
        return customer_name;
    }

    public void Add_Line(Order_Line l) {
        Lines.add(l);
    }

    public double CalcTot() {
        double Subtotal = 0;
        for (Order_Line l : Lines) {
            Subtotal += l.Line_Total();
        }
        return Subtotal * (1 + salesTaxRate);
    }

    public boolean Paid() {
        return PAID;
    }

    public void markPaid() {
        PAID = true;
    }
}
