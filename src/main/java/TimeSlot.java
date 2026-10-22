import java.time.*;

public final class TimeSlot {

    public TimeSlot(LocalDateTime start, LocalDateTime end) {
        //To validate that timeslots start or end are not null, validate that end is after start
        if((start == null)||(end == null)||!end.isAfter(start)){
            throw new IllegalArgumentException("start and end cannot be null, and end must be after start.");
        }
    }
}
