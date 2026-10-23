public class NoUserOverlap implements BookingRule {
    private final IBookingRepository bookings;

    public NoUserOverlap(IBookingRepository bookings) {
        this.bookings = bookings;
    }

    @Override
    public void check(String userId, String roomId, TimeSlot slot) {
        for (Booking booking : bookings.findByUser(userId)) {
            if (booking.getSlot().overlaps(slot)) {
                throw new IllegalStateException(
                    "User cannot book 2 rooms at the same time"
                );
            }
        }
    }
}