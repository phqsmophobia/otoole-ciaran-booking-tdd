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

        this.rules = List.of(new RoomExist(rooms),new SlotFuture(clock),new ObeyMaxDuration(MAX_SLOT),new NoRoomOverlap(bookings),new NoUserOverlap(bookings));
    }

    /** Book a room for a user. Returns the new booking. */
    public Booking book(String userId, String roomId, TimeSlot slot) {
        // Check every booking rule in order.
        for (BookingRule rule : rules) {
            rule.check(userId, roomId, slot);
        }

        Booking booking = new Booking(userId, roomId, slot);
        //save booking
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
        //memorybooking uses to list but we need it to be mutable not immutable which means we can change its order
        List<Booking> userbooked = new java.util.ArrayList<>(bookings.findByUser(userId)); //find all their bookings
        userbooked.sort((a,b) -> a.getSlot().getStart().compareTo(b.getSlot().getStart()));
        return userbooked;

    }
}
