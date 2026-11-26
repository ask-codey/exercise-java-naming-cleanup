package Bakery;

/*
 * Places one order and prints its total, so you can see the app still works after every rename.
 *
 * TO FINISH: rename the two local variables to Java's standard (the README's table lists their names).
 */
public class Main {

    public static void main(String[] args) {
        customerOrder Order1 = new customerOrder("Maya");
        Order1.Add_Line(new Order_Line("Blueberry muffin", 4, 3.25));
        Order1.Add_Line(new Order_Line("Sourdough loaf", 1, 6.50));

        double TOTAL = Order1.CalcTot();
        System.out.printf("%s owes $%.2f%n", Order1.getCustomer_Name(), TOTAL);
    }
}
