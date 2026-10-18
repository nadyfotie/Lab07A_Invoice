import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

    private Invoice inv;

    @BeforeEach
    void setUp() {
        inv = new Invoice("INVOICE", "Sam's Small Appliances\n100 Main Street\nAnytown, CA 98765");
        inv.addItem(new LineItem(new Product("Toaster", 29.95), 3));
        inv.addItem(new LineItem(new Product("Hair Dryer", 24.95), 1));
        inv.addItem(new LineItem(new Product("Car Vacuum", 19.99), 2));
    }

    @Test
    void getTitle() {
        assertEquals("INVOICE", inv.getTitle());
    }

    @Test
    void getAddress() {
        assertEquals("Sam's Small Appliances\n100 Main Street\nAnytown, CA 98765", inv.getAddress());
    }

    @Test
    void getItems() {
        assertEquals(3, inv.getItems().size());
    }

    @Test
    void addItem() {
        LineItem extra = new LineItem(new Product("Blender", 10.00), 1);
        inv.addItem(extra);
        assertEquals(4, inv.getItems().size());
        assertSame(extra, inv.getItems().get(3));
    }

    @Test
    void getAmountDue() {
        assertEquals(154.78, inv.getAmountDue(), 0.001);
    }

    @Test
    void getAmountDueEmpty() {
        Invoice empty = new Invoice("INVOICE", "Nobody");
        assertEquals(0.0, empty.getAmountDue(), 0.001);
    }
}