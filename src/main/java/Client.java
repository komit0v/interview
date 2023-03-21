import java.util.ArrayList;
import java.util.List;

public class Client {
    private final List<Integer> clients;
    private Products products;

    public Client() {
        this.clients = new ArrayList<>();
        clients.add(1);
        clients.add(2);
        clients.add(3);
        clients.add(4);
        clients.add(5);
    }

    public List<Integer> getClients() {
        return this.clients;
    }

    public double calculateProductAAndBCost(int amount, double markup, double unitCost){
        return amount * (unitCost + (unitCost * markup));
    }

    public double calculateProductCAndDCost(int amount, double markup, double unitCost){
        return amount * (unitCost + markup);
    }

    public double calculateProductBPromotion(double totalPrice, double promotion) {
        return totalPrice -= totalPrice * promotion;
    }

//    public double calculateProductCPromotion(double totalPrice, double promotion){
//        return ;
//    }

    public double calculateBasicClientDiscount(double totalPrice, double discount){
        if(discount != 0){
            return totalPrice -= totalPrice * discount;
        } else {
            return totalPrice;
        }
    }

    public double calculateAdditionalClientDiscount(double totalPrice, double discount){
        if(discount != 0){
            return totalPrice -= totalPrice * discount;
        } else {
            return totalPrice;
        }
    }
}
