package POJOS;

public final class Markup {
    public static final String PERCENTAGE = "Percentage";
    public static final String PER_UNIT = "PerUnit";
    private double amount;
    private String type;

    public Markup(double amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }
}
