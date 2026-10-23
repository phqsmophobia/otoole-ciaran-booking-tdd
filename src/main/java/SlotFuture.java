import java.time.Clock;
import java.time.LocalDateTime;

public class SlotFuture implements BookingRule {
    private final Clock clock;

    public SlotFuture(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void check(String userId, String roomId, TimeSlot slot) {
        if (slot.getStart().isBefore(LocalDateTime.now(clock))) {
            throw new IllegalArgumentException(
                "Booking slot must be for future time slots."
            );
        }
    }
}