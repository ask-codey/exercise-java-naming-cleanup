import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Reaches the bakery's code by the names Java's standard gives it, so each test turns green once its names are right
 * and the app still works. You don't need to change this file.
 */
class BakeryNamesTests {

    // 1. The package

    @Test
    void package_isTheBakerysDomainReversed_comMaplestreetbakeryOrders() {
        Check.type("OrderLine");
    }

    // 2. OrderLine

    @Test
    void orderLine_hasPrivateVariables_product_quantity_unitPrice() {
        Check.privateVariable("OrderLine", "product", String.class);
        Check.privateVariable("OrderLine", "quantity", int.class);
        Check.privateVariable("OrderLine", "unitPrice", double.class);
    }

    @Test
    void orderLine_getters_give4BlueberryMuffinsAt3_25() {
        Object line = Check.create("OrderLine", "Blueberry muffin", 4, 3.25);

        assertEquals("Blueberry muffin", Check.call(line, "getProduct"), "getProduct() gives the product.");
        assertEquals(4, Check.call(line, "getQuantity"), "getQuantity() gives how many.");
        assertEquals(3.25, Check.call(line, "getUnitPrice"), "getUnitPrice() gives the price of one.");
    }

    @Test
    void orderLine_lineTotal_4MuffinsAt3_25Is13_00() {
        Object line = Check.create("OrderLine", "Blueberry muffin", 4, 3.25);

        assertEquals(13.00, (double) Check.call(line, "lineTotal"), 0.001, "4 muffins at $3.25 make $13.00.");
    }

    // 3. CustomerOrder

    @Test
    void customerOrder_hasConstant_SALES_TAX_RATE_0_08() {
        Check.constant("CustomerOrder", "SALES_TAX_RATE", 0.08);
    }

    @Test
    void customerOrder_hasPrivateVariables_customerName_lines_paid() {
        Check.privateVariable("CustomerOrder", "customerName", String.class);
        Check.privateVariable("CustomerOrder", "lines", List.class);
        Check.privateVariable("CustomerOrder", "paid", boolean.class);
    }

    @Test
    void customerOrder_getCustomerName_givesMaya() {
        Object order = Check.create("CustomerOrder", "Maya");

        assertEquals("Maya", Check.call(order, "getCustomerName"), "getCustomerName() gives the name the order was made with.");
    }

    @Test
    void customerOrder_calculateTotal_muffinsAndSourdoughWithTaxIs21_06() {
        Object order = Check.create("CustomerOrder", "Maya");
        Class<?> lineType = Check.type("OrderLine");
        Check.hasMethod("CustomerOrder", "addLine", lineType);
        Check.call(order, "addLine", Check.create("OrderLine", "Blueberry muffin", 4, 3.25));
        Check.call(order, "addLine", Check.create("OrderLine", "Sourdough loaf", 1, 6.50));

        assertEquals(21.06, (double) Check.call(order, "calculateTotal"), 0.001,
                "$13.00 of muffins and a $6.50 loaf make $19.50, and with 8% sales tax $21.06.");
    }

    @Test
    void customerOrder_isPaid_falseUntilMarkPaid() {
        Object order = Check.create("CustomerOrder", "Maya");

        assertFalse((boolean) Check.call(order, "isPaid"), "A new order isn't paid: isPaid() gives false.");
        Check.call(order, "markPaid");
        assertTrue((boolean) Check.call(order, "isPaid"), "After markPaid(), isPaid() gives true.");
    }

    // 4. OrderRepository

    @Test
    void orderRepository_isAnInterface_withNoIPrefix() {
        assertTrue(Check.type("OrderRepository").isInterface(), "OrderRepository is an interface.");
    }

    @Test
    void orderRepository_hasMethods_save_and_findAll() {
        Check.hasMethod("OrderRepository", "save", Check.type("CustomerOrder"));
        Check.hasMethod("OrderRepository", "findAll");
    }
}
