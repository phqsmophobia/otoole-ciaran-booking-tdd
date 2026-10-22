public class Room {
    private final String ID;
    private final String name;

    public Room(String id, String name) {
        if (name == null) {
            throw new IllegalArgumentException("Room name can't be null");
        }
        this.ID = id;
        this.name = name;
    }

    public String getId() {
        return ID;
    }

    public String getName() {
        return name;
    }
}
