import POJOS.Exceptions.NoSuchMarkupTypeException;
import POJOS.Exceptions.NoSuchPromotionTypeException;
import POJOS.Markup;
import POJOS.Product;
import POJOS.Promotion;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class ProductTest {

    @Test
    public void testCalcUnitPriceWithPercentage() throws NoSuchMarkupTypeException {
        Product product =
                new Product("productA",0.52, new Markup(0.8, Markup.PERCENTAGE), new Promotion(Promotion.NONE, 0));

        double expected = 0.936;
        double actual = product.calcUnitPrice();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testCalcUnitPriceWithPerUnit() throws NoSuchMarkupTypeException {
        Product product =
                new Product("productC",0.41, new Markup(0.9, Markup.PER_UNIT), new Promotion(Promotion.NONE, 0));

        double expected = 1.31;
        double actual = product.calcUnitPrice();
        assertEquals(expected, actual, 0.001);
    }

    @Test (expected = NoSuchMarkupTypeException.class)
    public void testCalcUnitPriceShouldThrowException() throws NoSuchMarkupTypeException {
        Product product =
                new Product("productC",0.41, new Markup(0.9, "Invalid"), new Promotion(Promotion.NONE, 0));

        product.calcUnitPrice();
    }

    @Test
    public void testCalcPromotionalUnitPriceWithNoPromotion() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        Product product =
                new Product("productA",0.52, new Markup(0.8, Markup.PERCENTAGE), new Promotion(Promotion.NONE, 0));

        double expected = 0.936;
        double actual = product.calcPromotionalUnitPrice();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void tesCalcPromotionalUnitPriceWithPercentage() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        Product product =
                new Product("productB",0.38, new Markup(1.2, Markup.PERCENTAGE), new Promotion(Promotion.PERCENTAGE, 0.3));

        double expected = 0.5852;
        double actual = product.calcPromotionalUnitPrice();
        assertEquals(expected, actual, 0.001);
    }

    @Test(expected = NoSuchPromotionTypeException.class)
    public void testCalPromotionShouldThrowException() throws NoSuchPromotionTypeException, NoSuchMarkupTypeException {
        Product product =
                new Product("productB",0.38, new Markup(1.2, Markup.PERCENTAGE), new Promotion("Invalid", 0.3));
        product.calcPromotionalUnitPrice();
    }

}
