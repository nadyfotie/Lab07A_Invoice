import static org.junit.jupiter.api.Assertions.*;

class LineItemTest {

    @org.junit.jupiter.api.Test
    void getProduct() {
        Product toaster = new Product("Toaster", 29.93);
        LineItem item = new LineItem(toaster, 2);
        assertEquals(toaster, item.getProduct());
    }

    @org.junit.jupiter.api.Test
    void getQuantity() {
        Product toaster = new Product("Toaster", 29.93);
        LineItem item = new LineItem(toaster, 2);
        assertEquals(2, item.getQuantity());
    }

    @org.junit.jupiter.api.Test
    void getTotal() {
        Product toaster = new Product("Toaster", 29.93);
        LineItem item = new LineItem(toaster, 2);
        assertEquals(59.86, item.getTotal(), 0.001);
    }
}