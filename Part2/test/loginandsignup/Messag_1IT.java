package loginandsignup;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RC_Student_lab
 */
public class Messag_1IT {

    @Test
    public void testValidRecipient() {
        assertTrue(Message_1.checkRecipientCell("+27812345678"));
    }

    @Test
    public void testInvalidRecipientNoCode() {
        assertFalse(Message_1.checkRecipientCell("0812345678"));
    }

    @Test
    public void testInvalidRecipientTooLong() {
        assertFalse(Message_1.checkRecipientCell("+278123456789999"));
    }

    @Test
    public void testHashFormat() {
        String hash = Message_1.createMessageHash("9876543210", 1, "Hello world");
        assertEquals("98:1:HELLOWORLD", hash);
    }

    @Test
    public void testMessageCounter() {
        int countBefore = Message_1.returnTotalMessages();
        Message_1.messageCounter++;
        assertEquals(countBefore + 1, Message_1.returnTotalMessages());
    }
}
