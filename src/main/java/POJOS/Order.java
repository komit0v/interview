package POJOS;

import java.util.LinkedHashMap;
import java.util.Map;

import static POJOS.Promotion.*;

public final class Order {
    private Map<Product, Integer> orderMap;


    public Order(LinkedHashMap<Product, Integer> orderMap) {
        this.orderMap = orderMap;
    }


    public double getProductPriceAfterPromotion(Product product, int amount) {
        if (product.getPromotion().getType().equals(EVERY_3RD_IS_FREE)) {
            return ((product.calcUnitPrice() * amount) - (Math.floor(amount / 3) * product.calcUnitPrice())) / amount;
        } else if (product.getPromotion().getType().equals(PERCENTAGE)) {
            return product.calcPromotionalUnitPrice();
        } else if (product.getPromotion().getType().equals(NONE)) {
            return product.calcUnitPrice();
        } else {
            throw new IllegalArgumentException(INVALID_PROMOTION_TYPE);
        }

    }


    public double getLineTotal(Product product, int amount) {
        if (product.getPromotion().getType().equals(EVERY_3RD_IS_FREE)) {
            return ((product.calcUnitPrice() * amount) - (Math.floor(amount / 3) * product.calcUnitPrice()));
        } else if (product.getPromotion().getType().equals(PERCENTAGE)) {
            return product.calcPromotionalUnitPrice() * amount;
        } else if (product.getPromotion().getType().equals(NONE)) {
            return product.calcUnitPrice() * amount;
        } else {
            throw new IllegalArgumentException(INVALID_PROMOTION_TYPE);
        }
    }

    public void printEachOrderedProduct() {

        orderMap.forEach((key, value) -> {
            if (value != 0) {

                if (key.getPromotion().getType().equals(NONE)) {
                    System.out.println
                            (String.format("Units ordered from %s: %d, Base Unit Price: %.2f EUR, Line Total: %.2f EUR",
                                    key.getName(), value, key.calcUnitPrice(), getLineTotal(key, value)));
                } else {
                    System.out.println
                            (String.format
                                    ("Units ordered from %s: %d, Base Unit Price: %.2f EUR, Promotional Unit Price: %.2f EUR, Line Total: %.2f EUR",
                                            key.getName(), value, key.calcUnitPrice(), getProductPriceAfterPromotion(key, value), getLineTotal(key, value)));
                }
            }
        });
    }

    public double getLineTotalBeforeClientDiscounts() {
        double sum = 0;
        for (Map.Entry<Product, Integer> entry : orderMap.entrySet()) {
            sum += getLineTotal(entry.getKey(), entry.getValue());
        }
        return sum;
    }
}
