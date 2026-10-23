import java.time.*;
import java.util.List;

public class BookingService {
    public static final Duration MAX_SLOT = Duration.ofHours(3);

    private final IRoomRepository rooms;
    private final IBookingRepository bookings;
    private final List<BookingRule> rules;
    private final Clock clock;

    public BookingService(IRoomRepository rooms, IBookingRepository bookings, Clock clock) {
        this.rooms = rooms;
        this.bookings = bookings;
        this.clock = clock;

        this.rules = List.of(new RoomExist(rooms),new SlotFuture(clock));
    }

    /** Book a room for a user. Returns the new booking. */
    public Booking book(String userId, String roomId, TimeSlot slot) {
        rules.get(0).check(userId, roomId, slot);
        rules.get(1).check(userId, roomId, slot);
        checkduration(userId, roomId, slot);
        checknooverlaprooms(userId, roomId, slot);
        checknooverlapuser(userId, roomId, slot);

        Booking booking = new Booking(userId, roomId, slot);
        bookings.save(booking);
        return booking;
    }

    private void checkifslotinfuture(String userId, String roomId, TimeSlot slot){
        if(slot.getStart().isBefore(LocalDateTime.now(clock))){
            throw new IllegalArgumentException("Booking slot must be for future time slots.");
        }
    }

    private void checkduration(String userId, String roomId, TimeSlot slot) {
        if (slot.duration().compareTo(MAX_SLOT) > 0) {
            throw new IllegalArgumentException("Booking cannot exceed 3 hours");
        }
    }

    private void checknooverlaprooms(String userId, String roomId, TimeSlot slot){
        for(Booking e : bookings.findByRoom(roomId)){
            if(e.getSlot().overlaps(slot)){
                throw new IllegalStateException("Room cannot have overlapping bookings");
            }
        }
    }

    private void checknooverlapuser(String userId, String roomId, TimeSlot slot) {
        for (Booking e : bookings.findByUser(userId)) {
            if (e.getSlot().overlaps(slot)) {
                throw new IllegalStateException(
                    "User cannot book 2 rooms at the same time"
                );
            }
        }
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
        //memorybooking uses to list but we need it to be mutable not immutable which means we can change its order
        List<Booking> userbooked = new java.util.ArrayList<>(bookings.findByUser(userId)); //find all their bookings
        userbooked.sort((a,b) -> a.getSlot().getStart().compareTo(b.getSlot().getStart()));
        return userbooked;

    }
}
