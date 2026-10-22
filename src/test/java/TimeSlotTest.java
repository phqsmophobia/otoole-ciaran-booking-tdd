import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class TimeSlotTest {
    @Test 
    void rejectendbeforestarttimes(){
        LocalDateTime start = LocalDateTime.of(2026,10,12,11,0);
        LocalDateTime end = LocalDateTime.of(2026,10,12,10,0);
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(start, end));
    }

    @Test 
    void rejectstartequalsend(){
        LocalDateTime start = LocalDateTime.of(2026, 10, 24, 11, 0);
        LocalDateTime end = LocalDateTime.of(2026, 10, 24, 11, 0);
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(start, end));
    }

    @Test 
    void rejectnullend(){
        LocalDateTime start = LocalDateTime.of(2026, 10, 29, 9, 0);
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(start, null));
    }

    @Test 
    void rejectnullstart(){
        LocalDateTime end = LocalDateTime.of(2026, 10, 29, 9, 0);
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(null, end));
    }
}
