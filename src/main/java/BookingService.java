import java.time.*;
import java.util.List;

public class BookingService {
    public static final Duration MAX_SLOT = Duration.ofHours(3);

    private final IRoomRepository rooms;
    private final IBookingRepository bookings;
    private final Clock clock;

    public BookingService(IRoomRepository rooms, IBookingRepository bookings, Clock clock) {
        this.rooms = rooms;
        this.bookings = bookings;
        this.clock = clock;
    }

    /** Book a room for a user. Returns the new booking. */
    public Booking book(String userId, String roomId, TimeSlot slot) {
        Room room = rooms.findById(roomId);
        if (room == null) {
            throw new IllegalArgumentException("Room is not available as it does not exist.");
        }

        if(slot.getStart().isBefore(LocalDateTime.now(clock))){
            throw new IllegalArgumentException("Booking slot must be for future time slots.");
        }

        if(slot.duration().compareTo(MAX_SLOT)>0){
            throw new IllegalArgumentException("Booking cannot exceed 3 hours");
        }

        for(Booking e : bookings.findByRoom(roomId)){
            if(e.getSlot().overlaps(slot)){
                throw new IllegalStateException("Room cannot have overlapping bookings");
            }
        }

        for(Booking e : bookings.findByUser(userId)){
            if(e.getSlot().overlaps(slot)){
                throw new IllegalStateException("User cannot book 2 rooms at the same time");
            }
        }

        Booking booking = new Booking(userId, roomId, slot);
        bookings.save(booking);
        return booking;
    }

    /** Cancel a user's booking. */
    public void cancel(String userId, String roomId, TimeSlot slot) {
        for(Booking e: bookings.findByUser(userId)){
            if(e.getRoomId().equals(roomId)&&e.getSlot().equals(slot)){
                bookings.delete(e);
                return;
            }
        }
        throw new IllegalArgumentException("booking does not exist");
    }

    /** A user's bookings, earliest first. */
    public List<Booking> bookingsFor(String userId) {
        throw new UnsupportedOperationException("TODO: implement using TDD");
    }
}
