import java.time.*;
import java.util.Objects;

public final class TimeSlot {

    private final LocalDateTime slotstart;
    private final LocalDateTime slotend;

    public TimeSlot(LocalDateTime start, LocalDateTime end){
        //To validate that timeslots start or end are not null, validate that end is after start
        if((start == null)||(end == null)||!end.isAfter(start)){
            throw new IllegalArgumentException("start and end cannot be null, and end must be after start.");
        }
        this.slotstart = start;
        this.slotend = end;
    }

    public Duration duration(){
            return Duration.between(slotstart,slotend);
    }

    public boolean overlaps(TimeSlot other){
        boolean OtherBefore = other.slotstart.isBefore(slotend); //other slot starts first
        boolean BeforeOther = slotstart.isBefore(other.slotend); //this slot start first
        if (OtherBefore && BeforeOther) {
            return true; //this means they overlap
        }
        else {
            return false; //dont overlap
        }
    }

    public LocalDateTime getStart() {
        return slotstart;
    }

    public LocalDateTime getEnd() {
        return slotend;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSlot other = (TimeSlot) o;
        return slotstart.equals(other.slotstart) && slotend.equals(other.slotend);
    }

    @Override 
    public int hashCode(){
        return Objects.hash(slotstart, slotend);
    }
}
