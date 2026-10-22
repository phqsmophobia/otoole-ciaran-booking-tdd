import java.time.*;

public final class TimeSlot {

    public TimeSlot(LocalDateTime start, LocalDateTime end) {
        if((start == null)||(end == null)||!end.isAfter(start)){
            throw new IllegalArgumentException("start and end cannot be null, and end must be after start.");
        }
    }
}
