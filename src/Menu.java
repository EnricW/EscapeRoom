import exception.DuplicatedRoomException;
import exception.RoomNotFoundException;
import utils.InputUtils;

import java.util.*;

public class Menu {

    private final InventoryManager inventoryManager;

    public Menu() {
        this.inventoryManager = new InventoryManager();
    }

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            displayMenuOptions();

            try {
                int option = InputUtils.requestPositiveInteger("Select an option: ");
                if (option < 1 || option > 6) {
                    System.out.println("Invalid option. Please select a number between 1 and 6.");
                    continue;
                }
                exit = processMenuOption(option);
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        InputUtils.closeScanner();
    }

    private void displayMenuOptions() {
        System.out.println("\n1. Create a new room");
        System.out.println("2. Add a clue to a room");
        System.out.println("3. Add a decoration object to a room");
        System.out.println("4. Show inventory");
        System.out.println("5. Remove a room");
        System.out.println("6. Exit");
    }

    private boolean processMenuOption(int option) throws Exception {
        switch (option) {
            case 1:
                createRoom();
                break;
            case 2:
                addClueToRoom();
                break;
            case 3:
                addDecorationObjectToRoom();
                break;
            case 4:
                inventoryManager.showInventory();
                break;
            case 5:
                removeRoom();
                break;
            case 6:
                System.out.println("Exiting the application.");
                return true;
            default:
                System.out.println("Invalid option. Please select a number between 1 and 6.");
        }
        return false;
    }

    private void createRoom() throws DuplicatedRoomException {
        String name = InputUtils.requestString("Enter room name: ");
        System.out.println("Select difficulty level:");
        Arrays.stream(DifficultyLevel.values())
                .forEach(level -> System.out.println(level.ordinal() + 1 + ". " + level));

        int levelChoice = InputUtils.requestPositiveInteger("Choose difficulty level: ");

        if (levelChoice < 1 || levelChoice > DifficultyLevel.values().length) {
            System.out.println("Invalid difficulty level selected.");
            return;
        }

        DifficultyLevel level = DifficultyLevel.values()[levelChoice - 1];
        inventoryManager.createRoom(name, level);
        System.out.println("Room created successfully.");
    }

    private void addClueToRoom() throws RoomNotFoundException {
        int roomId = InputUtils.requestPositiveInteger("Enter the room ID to add a clue: ");
        String clueName = InputUtils.requestString("Enter clue name: ");
        double price = InputUtils.requestPositiveDouble("Enter clue price: ");
        int estimatedTime = InputUtils.requestPositiveInteger("Enter estimated time to solve the clue (minutes): ");
        String theme = InputUtils.requestString("Enter clue theme: ");

        Clue clue = new Clue(roomId, clueName, price, estimatedTime, theme);
        inventoryManager.addClueToRoom(roomId, clue);
        System.out.println("Clue added successfully.");
    }

    private void addDecorationObjectToRoom() throws RoomNotFoundException{
        int roomId = InputUtils.requestPositiveInteger("Enter the room ID to add a decoration object: ");
        String objectName = InputUtils.requestString("Enter decoration object name: ");
        double price = InputUtils.requestPositiveDouble("Enter decoration object price: ");
        String materialType = InputUtils.requestString("Enter decoration object material: ");

        DecorationObject object = new DecorationObject(roomId, objectName, price, materialType);
        inventoryManager.addDecorationObjectToRoom(roomId, object);
        System.out.println("Decoration object added successfully.");
    }

    private void removeRoom() throws RoomNotFoundException{
        int roomId = InputUtils.requestPositiveInteger("Enter the room ID to remove: ");
        inventoryManager.removeRoom(roomId);
        System.out.println("Room removed successfully.");
    }
}

