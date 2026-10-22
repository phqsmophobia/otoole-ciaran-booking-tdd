import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
    
    @Test
    void roomtakesanIDandName() {
        Room room = new Room("1", "Room 1");

        assertEquals("1", room.getId());
        assertEquals("Room 1", room.getName());
    }
}
