package POJOS;

import POJOS.Exceptions.NoSuchMarkupTypeException;
import POJOS.Exceptions.NoSuchPromotionTypeException;

import static POJOS.Markup.*;
import static POJOS.Promotion.INVALID_PROMOTION_TYPE;

public final class Product {
    private String name;
    private double unitCost;

    private Markup markup;
    private Promotion promotion;

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

    public Promotion getPromotion() {
        return promotion;
    }

    public double calcUnitPrice() throws NoSuchMarkupTypeException {
        if (markup.getType().equals(PERCENTAGE)) {
            return unitCost + (unitCost * markup.getAmount());
        } else if (markup.getType().equals(PER_UNIT)) {
            return unitCost + markup.getAmount();
        } else {
            throw new NoSuchMarkupTypeException(INVALID_MARKUP_TYPE);
        }
    }

    public double calcPromotionalUnitPrice() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        double calcUnitPrice = calcUnitPrice();
        if (promotion.getType().equals(Promotion.NONE)) {
            return calcUnitPrice;
        } else if (promotion.getType().equals(Promotion.PERCENTAGE)) {
            return calcUnitPrice - (calcUnitPrice * promotion.getAmount());
        } else {
            throw new NoSuchPromotionTypeException(INVALID_PROMOTION_TYPE);
        }

    }
}
