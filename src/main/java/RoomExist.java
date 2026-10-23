public class RoomExist implements BookingRule {
    private final IRoomRepository rooms;

    public RoomExist(IRoomRepository rooms) {
        this.rooms = rooms;
    }

    @Override
    public void check(String userId, String roomId, TimeSlot slot) {
        if (rooms.findById(roomId) == null) {
            throw new IllegalArgumentException(
                "Room is not available as it does not exist."
            );
        }
    }
}
