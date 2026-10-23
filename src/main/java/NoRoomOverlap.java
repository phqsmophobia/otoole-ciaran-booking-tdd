public class NoRoomOverlap implements BookingRule {
    private final IBookingRepository bookings;
    public NoRoomOverlap(IBookingRepository bookings) {
        this.bookings = bookings;
    }

    @Override
    public void check(String userId, String roomId, TimeSlot slot) {
        for (Booking booking : bookings.findByRoom(roomId)) {
            if (booking.getSlot().overlaps(slot)) {
                throw new IllegalStateException(
                    "Room cannot have overlapping bookings"
                );
            }
        }
    }
}
