package POJOS;

import POJOS.Exceptions.NoSuchMarkupTypeException;
import POJOS.Exceptions.NoSuchPromotionTypeException;

public class Client {
    private Order order;
    private int id;
    private double basicDiscount;
    private double additionalVolumeDiscountOver10k;
    private double additionalVolumeDiscountOver30k;

    public Client (Order order, int id) {
        this.order = order;
        this.id = id;
        setupDiscount(id);
    }

    private void setupDiscount(int id) {
        if(id == 1) {
            basicDiscount = 0.05;
            additionalVolumeDiscountOver10k = 0;
            additionalVolumeDiscountOver30k = 0.02;
        } else if(id ==2){
            basicDiscount = 0.04;
            additionalVolumeDiscountOver10k = 0.01;
            additionalVolumeDiscountOver30k = 0.02;
        } else if(id == 3){
            basicDiscount = 0.03;
            additionalVolumeDiscountOver10k = 0.01;
            additionalVolumeDiscountOver30k = 0.03;
        } else if (id == 4){
            basicDiscount = 0.02;
            additionalVolumeDiscountOver10k = 0.03;
            additionalVolumeDiscountOver30k = 0.05;
        } else if(id ==5){
            basicDiscount = 0;
            additionalVolumeDiscountOver10k = 0.05;
            additionalVolumeDiscountOver30k = 0.07;
        }
    }

    public double calcBasicClientDiscount() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        return order.getLineTotalBeforeClientDiscounts() * basicDiscount;
    }

    public double calcPriceAfterBasicClientDiscount() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        return order.getLineTotalBeforeClientDiscounts() - calcBasicClientDiscount();
    }

    public double calcVolumeDiscount() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        if (calcPriceAfterBasicClientDiscount() > 10000 && calcPriceAfterBasicClientDiscount() <= 30000) {
            return calcPriceAfterBasicClientDiscount() * additionalVolumeDiscountOver10k;
        } else if (calcPriceAfterBasicClientDiscount() > 30000) {
            return calcPriceAfterBasicClientDiscount() * additionalVolumeDiscountOver30k;
        } else {
            return 0;
        }
    }

    public double calcTotalOrderPriceToPay() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        return calcPriceAfterBasicClientDiscount() - calcVolumeDiscount();
    }

    public void printFinalPrice() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        System.out.println(String.format("Order Total Amount: %.2f EUR", calcTotalOrderPriceToPay()));
    }

    public void printDiscounts() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        if (calcBasicClientDiscount() != 0) {
            System.out.println(String.format("Basic Client Discount: -%.2f EUR", calcBasicClientDiscount()));
        }

        if (calcVolumeDiscount() != 0) {
            System.out.println(String.format("Additional Volume Discount: -%.2f EUR", calcVolumeDiscount()));
        }
    }
}
