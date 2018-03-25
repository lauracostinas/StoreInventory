import controller.StoreController;
import junit.framework.TestCase;
import model.Product;
import repository.StoreRepository;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Laura on 25-Mar-18.
 */
public class AppTest extends TestCase {
    private StoreRepository repository;
    private StoreController controller;

    public void test_repo_stockAll() throws IOException {
        repository = new StoreRepository();
        repository.readFile("testproducts.txt");
        ArrayList<Product> allProducts;
        allProducts = repository.stockSituation();


        assertEquals(2,allProducts.size());
    }
}
