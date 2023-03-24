import POJOS.Exceptions.NoSuchMarkupTypeException;
import POJOS.Exceptions.NoSuchPromotionTypeException;
import POJOS.Markup;
import POJOS.Order;
import POJOS.Product;
import POJOS.Promotion;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;


public class OrderTest {

    @Test
    public void testProductPriceAfterPromotionEveryThirdIsFree() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        Product product =
                new Product("productD",0.60, new Markup(1, Markup.PER_UNIT), new Promotion(Promotion.EVERY_3RD_IS_FREE, 0));
        LinkedHashMap<Product, Integer> map = new LinkedHashMap<>();
        map.put(product, 3 );
        Order order = new Order(map);

        double expected = 1.06;
        double actual = order.getProductPriceAfterPromotion(product, 3);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testProductPriceAfterPromotionPercentage() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        Product product =
                new Product("productB",0.38, new Markup(1.2, Markup.PERCENTAGE), new Promotion(Promotion.PERCENTAGE, 0.3));

        LinkedHashMap<Product, Integer> map = new LinkedHashMap<>();
        map.put(product, 3 );
        Order order = new Order(map);

        double expected = 0.5852;
        double actual = order.getProductPriceAfterPromotion(product, 3);
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testProductPriceAfterPromotionWithNoPromotion() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        Product product =
                new Product("productA",0.52, new Markup(0.8, Markup.PERCENTAGE), new Promotion(Promotion.NONE, 0));

        LinkedHashMap<Product, Integer> map = new LinkedHashMap<>();
        map.put(product, 3 );
        Order order = new Order(map);

        double expected = 0.936;
        double actual = order.getProductPriceAfterPromotion(product, 3);
        assertEquals(expected, actual, 0.001);
    }

    @Test(expected = NoSuchPromotionTypeException.class)
    public void testProductPriceAfterPromotionShouldThrowException() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        Product product =
                new Product("productA",0.52, new Markup(0.8, Markup.PERCENTAGE), new Promotion("Invalid", 0));

        LinkedHashMap<Product, Integer> map = new LinkedHashMap<>();
        map.put(product, 3 );
        Order order = new Order(map);

        order.getProductPriceAfterPromotion(product, 3);
    }

    @Test
    public void testLineTotalForPromotionEveryThirdIsFree() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        Product product =
                new Product("productD",0.60, new Markup(1, Markup.PER_UNIT), new Promotion(Promotion.EVERY_3RD_IS_FREE, 0));
        LinkedHashMap<Product, Integer> map = new LinkedHashMap<>();
        map.put(product, 3 );
        Order order = new Order(map);

        double expected = 3.2;
        double actual = order.getLineTotal(product, 3);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testLineTotalForPromotionPercentage() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        Product product =
                new Product("productB",0.38, new Markup(1.2, Markup.PERCENTAGE), new Promotion(Promotion.PERCENTAGE, 0.3));

        LinkedHashMap<Product, Integer> map = new LinkedHashMap<>();
        map.put(product, 10 );
        Order order = new Order(map);

        double expected = 5.852;
        double actual = order.getLineTotal(product, 10);
        assertEquals(expected, actual, 0.001);
    }

    @Test(expected = NoSuchPromotionTypeException.class)
    public void testLineTotalShouldThrowException() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        Product product =
                new Product("productB",0.38, new Markup(1.2, Markup.PERCENTAGE), new Promotion("Invalid", 0.3));

        LinkedHashMap<Product, Integer> map = new LinkedHashMap<>();
        map.put(product, 10 );
        Order order = new Order(map);

        order.getLineTotal(product, 10);
    }
}
