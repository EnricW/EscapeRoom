public class Clue extends InventoryItem {
    private final int estimatedTime;
    private final String theme;
    private static final double VAT = 0.10;

    public Clue(int id, String name, double price, int estimatedTime, String theme) {
        super(id, name, price);
        this.estimatedTime = validateEstimatedTime(estimatedTime);
        this.theme = validateTheme(theme);
    }

    private int validateEstimatedTime(int estimatedTime) {
        if (estimatedTime <= 0) {
            throw new IllegalArgumentException("Estimated time must be positive");
        }
        return estimatedTime;
    }

    private String validateTheme(String theme) {
        if (theme == null || theme.trim().isEmpty()) {
            throw new IllegalArgumentException("Theme cannot be empty");
        }
        return theme.trim();
    }

    @Override
    public double calculatePriceWithVAT() {
        return price + (price * VAT);
    }

    @Override
    public String toString() {
        return super.toString() + ", Estimated time: " + estimatedTime + ", Theme: " + theme;
    }
}
