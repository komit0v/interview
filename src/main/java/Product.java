import java.util.ArrayList;
import java.util.List;

public class Product {
    private List<Products> products;

    public Product() {
        this.products = new ArrayList<>();
    }

    public void addProducts(){
        products.add(Products.PRODUCT_A);
        products.add(Products.PRODUCT_B);
        products.add(Products.PRODUCT_C);
        products.add(Products.PRODUCT_D);
    }

    public double calculatePricePerUnitForProductAAndB(double markup, double unitCost){
        return unitCost + (unitCost * markup);
    }
    public double calculateProductAAndBCost(int amount, double markup, double unitCost){
        return amount * (calculatePricePerUnitForProductAAndB(markup, unitCost));
    }

    public double calculatePricePerUnitForProductCAndD(double markup, double unitCost){
        return unitCost + markup;
    }
    public double calculateProductCAndDCost(int amount, double markup, double unitCost){
        return amount * (calculatePricePerUnitForProductCAndD(markup, unitCost));
    }

    public double calculateProductBPromotion(double totalPrice, double promotion) {
        return totalPrice -= totalPrice * promotion;
    }

    public double calculateProductDPromotion(int amount, double promotion, double markup, double unitCost){
        int productsToPayFor = (int)(amount - (Math.floor(amount / promotion)));
        return calculateProductCAndDCost(productsToPayFor, markup, unitCost);
    }


}
