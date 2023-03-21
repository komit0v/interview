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

        double productACost = client.calculateProductAAndBCost(orderedAmountOfProductA, ProductConstants.UNIT_COST_PRODUCT_A, ProductConstants.MARKUP_PRODUCT_A);
        double productBCost = client.calculateProductAAndBCost(orderedAmountOfProductB, ProductConstants.MARKUP_PRODUCT_B, ProductConstants.UNIT_COST_PRODUCT_B);
        double productCCost = client.calculateProductCAndDCost(orderedAmountOfProductC, ProductConstants.MARKUP_PRODUCT_C, ProductConstants.UNIT_COST_PRODUCT_C);
        double productDCost = client.calculateProductCAndDCost(orderedAmountOfProductD, ProductConstants.MARKUP_PRODUCT_D, ProductConstants.UNIT_COST_PRODUCT_D);

        double productBPromotion = client.calculateProductBPromotion(productBCost, ProductConstants.PROMOTION_PRODUCT_B);


    }
}
