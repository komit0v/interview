import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        int clientID = Integer.parseInt(input[0]);
        int orderedAmountOfProductA = Integer.parseInt(input[1]);
        int orderedAmountOfProductB = Integer.parseInt(input[2]);
        int orderedAmountOfProductC = Integer.parseInt(input[3]);
        int orderedAmountOfProductD = Integer.parseInt(input[4]);

        Client client = new Client();
        Product product = new Product();

        if (client.getClients().contains(clientID)) {
            double productACost = product.calculateProductAAndBCost(orderedAmountOfProductA, ProductConstants.MARKUP_PRODUCT_A, ProductConstants.UNIT_COST_PRODUCT_A);
            double productBCost = product.calculateProductAAndBCost(orderedAmountOfProductB, ProductConstants.MARKUP_PRODUCT_B, ProductConstants.UNIT_COST_PRODUCT_B);
            double productCCost = product.calculateProductCAndDCost(orderedAmountOfProductC, ProductConstants.MARKUP_PRODUCT_C, ProductConstants.UNIT_COST_PRODUCT_C);
            double productDCost = product.calculateProductCAndDCost(orderedAmountOfProductD, ProductConstants.MARKUP_PRODUCT_D, ProductConstants.UNIT_COST_PRODUCT_D);

            double productBPromotion = product.calculateProductBPromotion(productBCost, ProductConstants.PROMOTION_PRODUCT_B);
            double productDPromotion = product.calculateProductDPromotion
                    (orderedAmountOfProductD, ProductConstants.PROMOTION_PRODUCT_D, ProductConstants.MARKUP_PRODUCT_D, ProductConstants.UNIT_COST_PRODUCT_D);
        } else {
            throw new IllegalArgumentException("No such client listed!");
        }


    }
}
