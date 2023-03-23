package POJOS;

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

    public double calcBasicClientDiscount() {
        return order.getLineTotalBeforeClientDiscounts() * basicDiscount;
    }

    public double calcPriceAfterBasicClientDiscount() {
        return order.getLineTotalBeforeClientDiscounts() - calcBasicClientDiscount();
    }

    public double calcVolumeDiscount() {
        if (calcPriceAfterBasicClientDiscount() > 10000 && calcPriceAfterBasicClientDiscount() <= 30000) {
            return calcPriceAfterBasicClientDiscount() * additionalVolumeDiscountOver10k;
        } else if (calcPriceAfterBasicClientDiscount() > 30000) {
            return calcPriceAfterBasicClientDiscount() * additionalVolumeDiscountOver30k;
        } else {
            return 0;
        }
    }

    public double calcTotalOrderPriceToPay() {
        return calcPriceAfterBasicClientDiscount() - calcVolumeDiscount();
    }

    public void printFinalPrice() {
        System.out.println(String.format("Order Total Amount: %.2f EUR", calcTotalOrderPriceToPay()));
    }

    public void printDiscounts() {
        if (calcBasicClientDiscount() != 0) {
            System.out.println(String.format("Basic Client Discount: -%.2f EUR", calcBasicClientDiscount()));
        }

        double volumeDiscount = calcVolumeDiscount();

        if (volumeDiscount != 0) {
            System.out.println(String.format("Additional Volume Discount: -%.2f EUR", volumeDiscount));
        }
    }
}
