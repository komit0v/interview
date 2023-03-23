package POJOS;

import static POJOS.Markup.PERCENTAGE;
import static POJOS.Markup.PER_UNIT;

public final class Product {
    private String name;
    private double unitCost;

    private Markup markup;
    private Promotion promotion;

    public Promotion getPromotion() {
        return promotion;
    }

    public Product(String name, double unitCost, Markup markup, Promotion promotion) {
        this.name = name;
        this.unitCost = unitCost;
        this.markup = markup;
        this.promotion = promotion;
    }

    public String getName() {
        return this.name;
    }

    public double getUnitCost() {
        return this.unitCost;
    }

    //ToDo throw exception instead of 0
    public double calcUnitPrice() {
        if (markup.getType().equals(PERCENTAGE)) {
            return unitCost + (unitCost * markup.getAmount());
        } else if (markup.getType().equals(PER_UNIT)) {
            return unitCost + markup.getAmount();
        } else {
            return 0;
        }
    }

    //ToDo throw exception

    public double calcPromotionalUnitPrice(){
        double calcUnitPrice = calcUnitPrice();
        if(promotion.getType().equals(Promotion.NONE)){
          return  calcUnitPrice;
        } else if (promotion.getType().equals(Promotion.PERCENTAGE)){
            return calcUnitPrice - (calcUnitPrice * promotion.getAmount());
        }
        return calcUnitPrice;
    }
}
