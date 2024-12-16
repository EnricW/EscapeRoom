public abstract class InventoryItem {
    protected int id;
    protected String name;
    protected double price;

    public InventoryItem(int id, String name, double price) {
        this.id = id;
        this.name = validateName(name);
        this.price = validatePrice(price);
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return name.trim();
    }

    private double validatePrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        return price;
    }

    public abstract double calculatePriceWithVAT();

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Price: " + price;
    }
}
