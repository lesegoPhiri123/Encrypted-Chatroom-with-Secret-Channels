package za.co.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;

public class ChatRoomTest {

    @Test
    void testAddClientIncreasesSize() {
        ChatRoom room = new ChatRoom();
        PrintWriter writer = new PrintWriter(new StringWriter());
        room.addClient(writer);
        assertEquals(1, room.getClientCount());
    }

    @Test
    void testBroadcastSendsMessageToAllClients() {
        ChatRoom room = new ChatRoom();
        StringWriter output1 = new StringWriter();
        StringWriter output2 = new StringWriter();

        room.addClient(new PrintWriter(output1, true));
        room.addClient(new PrintWriter(output2, true));

        room.broadcast("Hello!");

        assertTrue(output1.toString().contains("Hello!"));
        assertTrue(output2.toString().contains("Hello!"));
    }
}

