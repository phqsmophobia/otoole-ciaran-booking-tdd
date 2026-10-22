import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
public class BookingTest {
    
    @Test
    void checkbookingstoresnameroomidandslot() {
        TimeSlot bookingslot = new TimeSlot(LocalDateTime.of(2027, 1, 11, 10, 0),LocalDateTime.of(2027, 1, 11, 11, 0));
        Booking booktest = new Booking("jerry", "R1", bookingslot);

        assertEquals("jerry", booktest.getUserId());
        assertEquals("R1", booktest.getRoomId());
        assertEquals(bookingslot, booktest.getSlot());
    }
}
