public class Booking {

    private final String userID;
    private final String roomID;
    private final TimeSlot bookingslot;
    public Booking(String userId, String roomId, TimeSlot bookingslot) {
        if (userId == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }
        this.userID = userId;
        this.roomID = roomId;
        this.bookingslot = bookingslot;
    }

    public String getUserId() {
        return userID;
    }

    public String getRoomId() {
        return roomID;
    }

    public TimeSlot getSlot() {
        return bookingslot;
    }
}
