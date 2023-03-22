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
        StringBuilder sb = new StringBuilder();

        Client client = new Client();
        client.addClients();
        Product product = new Product();

        double productACost = 0;
        double baseUnitPriceProductA = product.calculatePricePerUnitForProductAAndB(ProductConstants.MARKUP_PRODUCT_A, ProductConstants.UNIT_COST_PRODUCT_A);
        double productBCost = 0;
        double baseUnitPriceProductB = product.calculatePricePerUnitForProductAAndB(ProductConstants.MARKUP_PRODUCT_B, ProductConstants.UNIT_COST_PRODUCT_B);
        double promotionalUnitPriceProductB = baseUnitPriceProductB - (baseUnitPriceProductB * ProductConstants.PROMOTION_PRODUCT_B);
        double productBCostWithPromotion = 0;
        double productCCost = 0;
        double baseUnitPriceProductC = product.calculatePricePerUnitForProductCAndD(ProductConstants.MARKUP_PRODUCT_C, ProductConstants.UNIT_COST_PRODUCT_C);
        double productDCost = 0;
        double baseUnitPriceProductD = product.calculatePricePerUnitForProductCAndD(ProductConstants.MARKUP_PRODUCT_D, ProductConstants.UNIT_COST_PRODUCT_D);
        double promotionalUnitPriceProductD = (baseUnitPriceProductD * 2) / ProductConstants.PROMOTION_PRODUCT_D;
        double productDCostWithPromotion = 0;


        double totalPriceWithOutDiscount = 0;
        double totalPriceAfterDiscount = 0;
        double discount = 0;

        if (client.getClients().contains(clientID)) {
            if (orderedAmountOfProductA != 0) {
                productACost = product.calculateProductAAndBCost(orderedAmountOfProductA, ProductConstants.MARKUP_PRODUCT_A, ProductConstants.UNIT_COST_PRODUCT_A);
                sb.append(String.format
                        ("Units ordered: %d, Base Unit Price: %.2f EUR, Line Total: %.2f EUR",
                                orderedAmountOfProductA, baseUnitPriceProductA,productACost)).append(System.lineSeparator());
            }

            if (orderedAmountOfProductB != 0) {
                productBCost = product.calculateProductAAndBCost(orderedAmountOfProductB, ProductConstants.MARKUP_PRODUCT_B, ProductConstants.UNIT_COST_PRODUCT_B);
                productBCostWithPromotion = product.calculateProductBPromotion(productBCost, ProductConstants.PROMOTION_PRODUCT_B);
                sb.append(String.format
                        ("Units ordered: %d, Base Unit Price: %.2f EUR, Promotional Unit Price: %.2f EUR, Line Total: %.2f EUR",
                                orderedAmountOfProductB, baseUnitPriceProductB, promotionalUnitPriceProductB,productBCost)).append(System.lineSeparator());
            }

            if (orderedAmountOfProductC != 0) {
                productCCost = product.calculateProductCAndDCost(orderedAmountOfProductC, ProductConstants.MARKUP_PRODUCT_C, ProductConstants.UNIT_COST_PRODUCT_C);
                sb.append(String.format
                        ("Units ordered: %d, Base Unit Price: %.2f EUR, Line Total: %.2f EUR",
                                orderedAmountOfProductC, baseUnitPriceProductC, productCCost)).append(System.lineSeparator());
            }

            if (orderedAmountOfProductD != 0) {
                productDCost = product.calculateProductCAndDCost(orderedAmountOfProductD, ProductConstants.MARKUP_PRODUCT_D, ProductConstants.UNIT_COST_PRODUCT_D);
                productDCostWithPromotion = product.calculateProductDPromotion
                        (orderedAmountOfProductD, ProductConstants.PROMOTION_PRODUCT_D, ProductConstants.MARKUP_PRODUCT_D, ProductConstants.UNIT_COST_PRODUCT_D);
                sb.append(String.format
                        ("Units ordered: %d, Base Unit Price: %.2f EUR, Promotional Unit Price: %.2f EUR, Line Total: %.2f EUR",
                                orderedAmountOfProductD, baseUnitPriceProductD, promotionalUnitPriceProductD,productDCost)).append(System.lineSeparator());

            }

            totalPriceWithOutDiscount = productACost + productBCostWithPromotion + productCCost + productDCostWithPromotion;
            sb.append(String.format("Total amount before client discounts: %.2f EUR", totalPriceWithOutDiscount)).append(System.lineSeparator());

            if (clientID == 1) {
                totalPriceAfterDiscount = client.calculateBasicClientDiscount(totalPriceWithOutDiscount, ClientConstants.CLIENT_ONE_BASIC_DISCOUNT);
                discount = totalPriceWithOutDiscount - totalPriceAfterDiscount;
                sb.append(String.format("Basic client discount: -%.2f EUR", discount)).append(System.lineSeparator());
                if (totalPriceAfterDiscount > 30000) {
                    totalPriceAfterDiscount = client.calculateAdditionalClientDiscount(totalPriceAfterDiscount, ClientConstants.CLIENT_ONE_ABOVE_30000_DISCOUNT);
                    discount = totalPriceWithOutDiscount - totalPriceAfterDiscount;
                    sb.append(String.format("Additional volume discount: -%.2f EUR", discount)).append(System.lineSeparator());
                }
            } else if (clientID == 2) {
                totalPriceAfterDiscount = client.calculateBasicClientDiscount(totalPriceWithOutDiscount, ClientConstants.CLIENT_TWO_BASIC_DISCOUNT);
                discount = totalPriceWithOutDiscount - totalPriceAfterDiscount;
                sb.append(String.format("Basic client discount: -%.2f EUR", discount)).append(System.lineSeparator());
                if (totalPriceWithOutDiscount > 10000 && totalPriceWithOutDiscount <= 30000) {
                    totalPriceAfterDiscount = client.calculateAdditionalClientDiscount(totalPriceAfterDiscount, ClientConstants.CLIENT_TWO_ABOVE_10000_DISCOUNT);
                    discount = totalPriceWithOutDiscount - totalPriceAfterDiscount;
                    sb.append(String.format("Additional volume discount: -%.2f EUR", discount)).append(System.lineSeparator());
                } else if (totalPriceAfterDiscount > 30000) {
                    totalPriceAfterDiscount -= client.calculateAdditionalClientDiscount(totalPriceAfterDiscount, ClientConstants.CLIENT_TWO_ABOVE_30000_DISCOUNT);
                    discount = totalPriceWithOutDiscount - totalPriceAfterDiscount;
                    sb.append(String.format("Additional volume discount: -%.2f EUR", discount)).append(System.lineSeparator());
                }
            } else if (clientID == 3) {
                totalPriceAfterDiscount = client.calculateBasicClientDiscount(totalPriceWithOutDiscount, ClientConstants.CLIENT_THREE_BASIC_DISCOUNT);
                discount = totalPriceWithOutDiscount - totalPriceAfterDiscount;
                sb.append(String.format("Basic client discount: -%.2fEUR", discount)).append(System.lineSeparator());
                if (totalPriceWithOutDiscount > 10000 && totalPriceWithOutDiscount <= 30000) {
                    totalPriceAfterDiscount = client.calculateAdditionalClientDiscount(totalPriceAfterDiscount, ClientConstants.CLIENT_THREE_ABOVE_10000_DISCOUNT);
                    discount = totalPriceWithOutDiscount - totalPriceAfterDiscount;
                    sb.append(String.format("Additional volume discount: -%.2f EUR", discount)).append(System.lineSeparator());
                } else if (totalPriceAfterDiscount > 30000) {
                    totalPriceAfterDiscount = client.calculateAdditionalClientDiscount(totalPriceAfterDiscount, ClientConstants.CLIENT_THREE_ABOVE_30000_DISCOUNT);
                    discount = totalPriceWithOutDiscount - totalPriceAfterDiscount;
                    sb.append(String.format("Additional volume discount: -%.2f EUR", discount)).append(System.lineSeparator());
                }
            } else if (clientID == 4) {
                totalPriceAfterDiscount = client.calculateBasicClientDiscount(totalPriceWithOutDiscount, ClientConstants.CLIENT_FOUR_BASIC_DISCOUNT);
                discount = totalPriceWithOutDiscount - totalPriceAfterDiscount;
                sb.append(String.format("Basic client discount: -%.2f EUR", discount)).append(System.lineSeparator());
                if (totalPriceWithOutDiscount > 10000 && totalPriceWithOutDiscount <= 30000) {
                    totalPriceAfterDiscount = client.calculateAdditionalClientDiscount(totalPriceAfterDiscount, ClientConstants.CLIENT_FOUR_ABOVE_10000_DISCOUNT);
                    discount = totalPriceWithOutDiscount - totalPriceAfterDiscount;
                    sb.append(String.format("Additional volume discount: -%.2f EUR", discount)).append(System.lineSeparator());
                } else if (totalPriceAfterDiscount > 30000) {
                    totalPriceAfterDiscount = client.calculateAdditionalClientDiscount(totalPriceAfterDiscount, ClientConstants.CLIENT_FOUR_ABOVE_30000_DISCOUNT);
                    discount = totalPriceWithOutDiscount - totalPriceAfterDiscount;
                    sb.append(String.format("Additional volume discount: -%.2f EUR", discount)).append(System.lineSeparator());
                }
            } else if (clientID == 5) {
                if (totalPriceWithOutDiscount > 10000 && totalPriceWithOutDiscount <= 30000) {
                    totalPriceAfterDiscount = client.calculateAdditionalClientDiscount(totalPriceWithOutDiscount, ClientConstants.CLIENT_FIVE_ABOVE_10000_DISCOUNT);
                    discount = totalPriceWithOutDiscount - totalPriceAfterDiscount;
                    sb.append(String.format("Additional volume discount: -%.2f EUR", discount)).append(System.lineSeparator());
                } else if (totalPriceWithOutDiscount > 30000) {
                    totalPriceAfterDiscount = client.calculateAdditionalClientDiscount(totalPriceWithOutDiscount, ClientConstants.CLIENT_FIVE_ABOVE_30000_DISCOUNT);
                    discount = totalPriceWithOutDiscount - totalPriceAfterDiscount;
                    sb.append(String.format("Additional volume discount: -%.2f EUR", discount)).append(System.lineSeparator());
                }else{
                    totalPriceAfterDiscount = totalPriceWithOutDiscount;
                }
            }

            sb.append(String.format("Total order amount: %.2f EUR", totalPriceAfterDiscount));
            System.out.println(sb.toString());

        } else {
            throw new IllegalArgumentException("No such client listed!");
        }


    }
}
