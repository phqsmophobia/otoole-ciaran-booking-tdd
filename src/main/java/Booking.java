public class Booking {

    private final String userID;
    private final String roomID;
    private final TimeSlot bookingslot;
    public Booking(String userId, String roomId, TimeSlot bookingslot) {
        if (userId == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }

        if (bookingslot == null) {
            throw new IllegalArgumentException("Booking time slot cannot be null");
        }

        if (roomId == null) {
            throw new IllegalArgumentException("Room id cannot be null");
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

    @Override
    public boolean equals(Object obj) {
        //if they are both the same object then return trun
        if (this == obj) return true;

        // If the object isn't a Booking, they can't be equal
        if (!(obj instanceof Booking)) return false;
        //convert other to a booking to access its fields
        Booking other = (Booking) obj;
        // bookings are equal only if the user, room and time slot all match
        return java.util.Objects.equals(userID, other.userID) && java.util.Objects.equals(roomID, other.roomID) && java.util.Objects.equals(bookingslot, other.bookingslot);
    }
}
