import java.util.ArrayList;
import java.util.List;

public class Client {
    private final List<Integer> clients;

    public Client() {
        this.clients = new ArrayList<>();
    }

    public List<Integer> getClients() {
        return this.clients;
    }

    public void addClients() {
        clients.add(1);
        clients.add(2);
        clients.add(3);
        clients.add(4);
        clients.add(5);
    }

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
