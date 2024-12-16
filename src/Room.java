import java.util.ArrayList;
import java.util.List;

public class Room {
    private static int roomCounter = 1;
    private final int id;
    private final String name;
    private final DifficultyLevel difficultyLevel;
    private double totalValue;
    private final List<Clue> clues;
    private final List<DecorationObject> decorationObjects;

    public Room(String name, DifficultyLevel difficultyLevel) {
        this.id = roomCounter++;
        this.name = name;
        this.difficultyLevel = difficultyLevel;
        this.totalValue = 0.0;
        this.clues = new ArrayList<>();
        this.decorationObjects = new ArrayList<>();
    }

    public void addClue(Clue clue) {
        clues.add(clue);
        calculateTotalValue();
    }

    public void addDecorationObject(DecorationObject object) {
        decorationObjects.add(object);
        calculateTotalValue();
    }

    private void calculateTotalValue() {
        this.totalValue = clues.stream()
                .mapToDouble(Clue::calculatePriceWithVAT)
                .sum() +
                decorationObjects.stream()
                        .mapToDouble(DecorationObject::calculatePriceWithVAT)
                        .sum();
    }

    public double getTotalValue() {
        return totalValue;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void showInventory() {
        System.out.println("Room: " + name + " (ID: " + id + ")");
        System.out.println("Difficulty Level: " + difficultyLevel);
        System.out.println("Clues:");
        clues.forEach(System.out::println);
        System.out.println("Decoration Objects:");
        decorationObjects.forEach(System.out::println);
        System.out.println("Total Value (Including VAT): " + getTotalValue() + " Euros");
    }
}
