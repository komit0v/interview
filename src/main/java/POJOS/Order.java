package POJOS;

import java.util.HashMap;
import java.util.Map;

import static POJOS.Promotion.*;

public final class Order {
    private Map<Product, Integer> orderMap;


    public Order(HashMap<Product, Integer> orderMap) {
        this.orderMap = orderMap;
    }

    //ToDo throw exception
    private double getProductPriceAfterPromotion(Product product, int amount) {
        if (product.getPromotion().getType().equals(EVERY_3RD_IS_FREE)) {
            return ((product.calcUnitPrice() * amount) - ((amount % 3) * product.calcUnitPrice())) / amount;
        } else if (product.getPromotion().getType().equals(PERCENTAGE)) {
            return product.calcPromotionalUnitPrice();
        } else if (product.getPromotion().getType().equals(NONE)) {
            return product.calcUnitPrice();
        }
        return 0;
    }

    //ToDo throw exception
    private double getLineTotal(Product product, int amount) {
        if (product.getPromotion().getType().equals(EVERY_3RD_IS_FREE)) {
            return ((product.calcUnitPrice() * amount) - ((amount % 3) * product.calcUnitPrice()));
        } else if (product.getPromotion().getType().equals(PERCENTAGE)) {
            return product.calcPromotionalUnitPrice() * amount;
        } else if (product.getPromotion().getType().equals(NONE)) {
            return product.calcUnitPrice() * amount;
        }
        return 0;
    }

    public void printEachOrderedProduct() {
        orderMap.forEach((key, value) -> {
            if (value != 0) {
                if (key.getPromotion().getType().equals(NONE)) {
                    System.out.println
                            (String.format("Units ordered from %s: %d, Base Unit Price: %.2f, Line Total: %.2f EUR",
                                    key.getName(), value, key.calcUnitPrice(), getLineTotal(key, value)));
                } else {
                    System.out.println
                            (String.format
                                    ("Units ordered from %s: %d, Base Unit Price: %.2f, Promotional Unit Price: %.2f, Line Total: %.2f EUR",
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
