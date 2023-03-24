import POJOS.*;
import POJOS.Exceptions.NoSuchMarkupTypeException;
import POJOS.Exceptions.NoSuchPromotionTypeException;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

public class ClientTest {


    @Test
    public void testCalcBasicClientDiscount() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        Product product =
                new Product("productA", 0.52, new Markup(0.8, Markup.PERCENTAGE), new Promotion(Promotion.NONE, 0));

        LinkedHashMap<Product, Integer> map = new LinkedHashMap<>();
        map.put(product, 10 );
        Order order = new Order(map);
        Client client = new Client(order, 1);

        double expected = 0.468;
        double actual = client.calcBasicClientDiscount();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testCalcPriceAfterBasicClientDiscount() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        Product product =
                new Product("productA", 0.52, new Markup(0.8, Markup.PERCENTAGE), new Promotion(Promotion.NONE, 0));
        LinkedHashMap<Product, Integer> map = new LinkedHashMap<>();
        map.put(product, 10 );
        Order order = new Order(map);
        Client client = new Client(order, 1);

        double expected = 8.892;
        double actual = client.calcPriceAfterBasicClientDiscount();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testCalcVolumeDiscountBetween10000And30000() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        Product product =
                new Product("productA", 0.52, new Markup(0.8, Markup.PERCENTAGE), new Promotion(Promotion.NONE, 0));
        LinkedHashMap<Product, Integer> map = new LinkedHashMap<>();
        map.put(product, 20000 );
        Order order = new Order(map);
        Client client = new Client(order, 2);

        double expected = 179.712;
        double actual = client.calcVolumeDiscount();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testCalcVolumeDiscountOver30000() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        Product product =
                new Product("productA", 0.52, new Markup(0.8, Markup.PERCENTAGE), new Promotion(Promotion.NONE, 0));
        LinkedHashMap<Product, Integer> map = new LinkedHashMap<>();
        map.put(product, 100000 );
        Order order = new Order(map);
        Client client = new Client(order, 3);

        double expected = 2723.76;
        double actual = client.calcVolumeDiscount();
        assertEquals(expected,actual, 0.001);
    }

    @Test
    public void testCalcVolumeUnder1000() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        Product product =
                new Product("productA", 0.52, new Markup(0.8, Markup.PERCENTAGE), new Promotion(Promotion.NONE, 0));
        LinkedHashMap<Product, Integer> map = new LinkedHashMap<>();
        map.put(product, 100 );
        Order order = new Order(map);
        Client client = new Client(order, 5);

        double expected = 0;
        double actual = client.calcVolumeDiscount();
        assertEquals(expected,actual,0.001);
    }

    @Test
    public void testCalcTotalOrderPriceToPay() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        Product product =
                new Product("productA", 0.52, new Markup(0.8, Markup.PERCENTAGE), new Promotion(Promotion.NONE, 0));
        LinkedHashMap<Product, Integer> map = new LinkedHashMap<>();
        map.put(product, 100 );
        Order order = new Order(map);
        Client client = new Client(order, 4);

        double expected = 91.728;
        double actual = client.calcTotalOrderPriceToPay();
        assertEquals(expected, actual, 0.001);
    }
}
