package ci553.happyshop.tests;

import ci553.happyshop.catalogue.Product;
import ci553.happyshop.client.customer.CustomerModel;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class cusModelTest {
    CustomerModel cusModel;

    @Before
    public void setup() {
        cusModel = new CustomerModel();
    }

    // test trolley adds products correctly
    @Test
    public void trolleyAddsProduct() throws Exception {
        Product p = new Product("0009", "ProductTest", "imageHolder.jpg", 2.00, 200);
        ArrayList<Product> trolley = new ArrayList<>();
        trolley.add(p);
        assertEquals(1, trolley.size());
    }

    // test trolley increments product without making list larger (merging items)
    @Test
    public void trolleyIncrementsProduct() throws Exception {
        Product p = new Product("0009", "ProductTest", "imageHolder.jpg", 2.00, 200);
        ArrayList<Product> trolley = new ArrayList<>();
        p.setOrderedQuantity(2);
        trolley.add(p);
        assertEquals(1, trolley.size());
        assertEquals(2, trolley.get(0).getOrderedQuantity());
    }


}
