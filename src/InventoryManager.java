import exception.DuplicatedRoomException;
import exception.RoomNotFoundException;

import java.util.*;

public class InventoryManager {
    private final Map<Integer, Room> rooms;

    public InventoryManager() {
        this.rooms = new HashMap<>();
    }

    public void createRoom(String name, DifficultyLevel level) throws DuplicatedRoomException {
        boolean roomExists = rooms.values().stream()
                .anyMatch(r -> r.getName().equalsIgnoreCase(name));

        if (roomExists) {
            throw new DuplicatedRoomException("A room with the name '" + name + "' already exists.");
        }

        Room newRoom = new Room(name, level);
        rooms.put(newRoom.getId(), newRoom);
    }

    public void addClueToRoom(int roomId, Clue clue) throws RoomNotFoundException {
        Room room = rooms.get(roomId);
        if (room == null) {
            throw new RoomNotFoundException("Error: Room not found.");
        }
        room.addClue(clue);
    }

    public void addDecorationObjectToRoom(int roomId, DecorationObject object) throws RoomNotFoundException{
        Room room = rooms.get(roomId);
        if (room == null) {
            throw new RoomNotFoundException("Error: Room not found.");
        }
        room.addDecorationObject(object);
    }

    public void showInventory() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms in the inventory.");
            return;
        }
        rooms.values().forEach(Room::showInventory);
    }

    public void removeRoom(int roomId) throws RoomNotFoundException {
        if (!rooms.containsKey(roomId)) {
            throw new RoomNotFoundException("Error: Room not found.");
        }
        rooms.remove(roomId);
    }
}

