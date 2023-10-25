import org.ecommerce.products.ProductsApp;
import org.junit.Test;
import org.ordersMicroservice.OrdersApp;

public class ModulesTest {

    @Test
    public void integrationTest1() {
        new OrdersApp();
    }

    @Test
    public void integrationTest2() {
        new ProductsApp();
    }

}