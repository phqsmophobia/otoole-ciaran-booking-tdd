import java.time.Duration;

public class ObeyMaxDuration implements BookingRule {
    private final Duration maxSlot;
    public ObeyMaxDuration(Duration maxSlot) {
        this.maxSlot = maxSlot;
    }

    @Override
    public void check(String userId, String roomId, TimeSlot slot) {
        if (slot.duration().compareTo(maxSlot) > 0) {
            throw new IllegalArgumentException(
                "Booking cannot exceed 3 hours"
            );
        }
    }
}