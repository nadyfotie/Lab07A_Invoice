import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product toaster;

    @BeforeEach
    void setUp() {
        toaster = new Product("Toaster", 29.95);
    }

    @Test
    void getName() {
        assertEquals("Toaster", toaster.getName());
    }

    @Test
    void getPrice() {
        assertEquals(29.95, toaster.getPrice(), 0.001);
    }
}