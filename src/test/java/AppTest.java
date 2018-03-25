import controller.StoreController;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
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

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
