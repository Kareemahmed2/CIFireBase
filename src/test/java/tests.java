import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class tests {
    @Test
    void testAdd() {
        assertEquals(5, Backend.add(2, 3), "2 + 3 should equal 5");
    }
}
