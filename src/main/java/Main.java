import POJOS.*;
import POJOS.Exceptions.NoSuchMarkupTypeException;
import POJOS.Exceptions.NoSuchPromotionTypeException;

import java.util.*;

import static POJOS.Markup.PERCENTAGE;
import static POJOS.Markup.PER_UNIT;
import static POJOS.Promotion.EVERY_3RD_IS_FREE;
import static POJOS.Promotion.NONE;

//values should be taken from the database and not hard coded directly
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        int clientID = Integer.parseInt(input[0]);
        int orderedAmountOfProductA = Integer.parseInt(input[1]);
        int orderedAmountOfProductB = Integer.parseInt(input[2]);
        int orderedAmountOfProductC = Integer.parseInt(input[3]);
        int orderedAmountOfProductD = Integer.parseInt(input[4]);


        LinkedHashMap<Product, Integer> orderMap = new LinkedHashMap<>();

        if (orderedAmountOfProductA != 0) {
            Product productA = initializeProduct(0.8, PERCENTAGE, NONE, 0, "product A", UnitCost.UNIT_COST_PRODUCT_A);
            orderMap.put(productA, orderedAmountOfProductA);
        }

        if (orderedAmountOfProductB != 0) {
            Product productB = initializeProduct(1.2, PERCENTAGE, PERCENTAGE, 0.3, "product B", UnitCost.UNIT_COST_PRODUCT_B);
            orderMap.put(productB, orderedAmountOfProductB);
        }

        if (orderedAmountOfProductC != 0) {
            Product productC = initializeProduct(0.9, PER_UNIT, NONE, 0, "product C", UnitCost.UNIT_COST_PRODUCT_C);
            orderMap.put(productC, orderedAmountOfProductC);
        }

        if (orderedAmountOfProductD != 0) {
            Product productD = initializeProduct(1, PER_UNIT, EVERY_3RD_IS_FREE, 0, "product D", UnitCost.UNIT_COST_PRODUCT_D);
            orderMap.put(productD, orderedAmountOfProductD);
        }

        Order order = new Order(orderMap);
        if (clientID > 0 && clientID < 6) {
            try {
                Client client = new Client(order, clientID);

                order.printEachOrderedProduct();
                System.out.println(String.format("Total amount before client discounts: %.2f", order.getLineTotalBeforeClientDiscounts()));
                client.printDiscounts();
                client.printFinalPrice();
            } catch (NoSuchMarkupTypeException | NoSuchPromotionTypeException exception){
                System.out.println(exception.getMessage());
            }

        } else {
            System.out.println(("No such client!"));
        }


    }

    public static Product initializeProduct
            (double markupAmount, String markupType, String promotionType, double promotionAmount,
             String productName, double unitCost) {
        Markup markup = new Markup(markupAmount, markupType);
        Promotion promotion = new Promotion(promotionType, promotionAmount);
        Product product = new Product(productName, unitCost, markup, promotion);

        return product;
    }
}
