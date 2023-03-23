package POJOS;

public class Promotion {
    public static final String PERCENTAGE = "Percentage";
    public static final String NONE = "none";
    public static final String EVERY_3RD_IS_FREE = "thirdFree";

    private String type;
    private double amount;

    public Promotion(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return this.type;
    }

    public double getAmount() {
        return this.amount;
    }


}
