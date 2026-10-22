import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
        LocalDateTime start = LocalDateTime.of(2026,10,29,9,0);
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(start, null));
    }

    @Test 
    void rejectnullstart(){
        LocalDateTime end = LocalDateTime.of(2026,10,29,9,0);
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(null, end));
    }

    @Test 
    void calculatetimebetweenstartandend(){
        LocalDateTime start = LocalDateTime.of(2026,10,12,11,0);
        LocalDateTime end = LocalDateTime.of(2026,10,12,12,30);
        TimeSlot timeslot = new TimeSlot(start, end);
        assertEquals(90, timeslot.duration().toMinutes());
    }

    private static TimeSlot slot(int startHour, int endHour) {
        return new TimeSlot(
            LocalDateTime.of(2027, 1, 11, startHour, 0),
            LocalDateTime.of(2027, 1, 11, endHour, 0)
        );
    }

    @ParameterizedTest(name = "{0}-{1} vs {2}-{3} overlaps: {4}")
    @CsvSource({
        "10, 11, 11, 12, false", // touching
        "10, 12, 11, 13, true",  // partly overlapping
        "8, 9, 10, 11, false", //a is before b
        "14, 15, 9, 11, false", //a is after b
        "10, 12, 9, 13, true", //a is in b
        "8, 9, 8, 9, true"  //a is identical to b

    })
    void overlapCases(int aStart, int aEnd, int bStart, int bEnd, boolean expected) {
        assertEquals(expected, slot(aStart, aEnd).overlaps(slot(bStart, bEnd)));
        assertEquals(expected, slot(bStart, bEnd).overlaps(slot(aStart, aEnd)));
    }

    @Test 
    void aoverlapsbisequaltoboverlapsa(){
        //create 2 slots that overlap
        TimeSlot slot1 = new TimeSlot(LocalDateTime.of(2026,10,12,8,0,0), LocalDateTime.of(2026,10,12,10,0,0));
        TimeSlot slot2 = new TimeSlot(LocalDateTime.of(2026,10,12,9,0,0), LocalDateTime.of(2026,10,12,11,0,0));
        assertEquals(slot1.overlaps(slot2), slot2.overlaps(slot1));
    }

    @Test 
    void testforequalhashcodes(){
        LocalDateTime start = LocalDateTime.of(2026, 10, 24, 11, 30, 0);
        LocalDateTime end = LocalDateTime.of(2026, 10, 24, 13, 30, 0);
        TimeSlot slot1 = new TimeSlot(start, end);
        TimeSlot slot2 = new TimeSlot(start, end);
        assertEquals(slot1.hashCode(), slot2.hashCode());
    }
}
