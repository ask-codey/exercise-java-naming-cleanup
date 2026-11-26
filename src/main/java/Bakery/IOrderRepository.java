package Bakery;

import java.util.List;

/*
 * Where the bakery's orders are saved. A later version keeps them in a database.
 *
 * TO FINISH: rename this interface, its methods and its parameter to Java's standard.
 * The README's table lists the name each one should have. Use Rename (Shift+F6 in IntelliJ IDEA), never retyping.
 */
public interface IOrderRepository {

    void Save(customerOrder o);

    List<customerOrder> FIND_ALL();
}
