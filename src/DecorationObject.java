public class DecorationObject extends InventoryItem {
    private final String materialType;
    private static final double VAT = 0.21;

    public DecorationObject(int id, String name, double price, String materialType) {
        super(id, name, price);
        this.materialType = validateMaterialType(materialType);
    }

    private String validateMaterialType(String materialType) {
        if (materialType == null || materialType.trim().isEmpty()) {
            throw new IllegalArgumentException("Material type cannot be empty");
        }
        return materialType.trim();
    }

    @Override
    public double calculatePriceWithVAT() {
        return price + (price * VAT);
    }

    @Override
    public String toString() {
        return super.toString() + ", Material: " + materialType;
    }
}
