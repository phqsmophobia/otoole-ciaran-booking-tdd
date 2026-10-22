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

}
