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

    @Test
    void notallownulluserid() {
        TimeSlot slot = new TimeSlot( LocalDateTime.of(2027, 1, 11, 10, 0), LocalDateTime.of(2027, 1, 11, 11, 0));
        assertThrows(IllegalArgumentException.class, () -> new Booking(null, "R1", slot));
    }

    @Test
    void notallownulltimeslot() {
        assertThrows(IllegalArgumentException.class, () -> new Booking("alice", "R1", null) );
    }

    @Test
    void notallownullroomid() {
        TimeSlot slot = new TimeSlot(LocalDateTime.of(2027, 1, 11, 10, 0), LocalDateTime.of(2027, 1, 11, 11, 0));
        assertThrows(IllegalArgumentException.class, () -> new Booking("jerry", null, slot));
    }
}
