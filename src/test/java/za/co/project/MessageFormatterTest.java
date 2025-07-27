package za.co.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFormatterTest {

    @Test
    void testFormatMessage() {
        String result = MessageFormatter.format("Lesego", "Hello!");
        assertEquals("[Lesego]: Hello!", result);
    }
}

