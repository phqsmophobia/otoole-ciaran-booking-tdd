public class Room {
    private final String ID;
    private final String name;
    
    public Room(String id, String name) {
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
