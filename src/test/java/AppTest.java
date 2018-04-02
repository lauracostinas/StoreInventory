import controller.StoreController;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import model.Product;
import repository.StoreRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Laura on 25-Mar-18.
 */
public class AppTest extends TestCase {
    private StoreRepository repository;
    private StoreController controller;

    private void emptyFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("testproducts.txt");
        writer.print("");
        writer.close();
    }

    public void test_repo_stockAll() throws IOException {
        emptyFile();
        repository = new StoreRepository("testproducts.txt");
        repository.readFile();
        ArrayList<Product> allProducts;

        Product p1 = new Product(56, "milk", "food", 10, "cora");
        Product p2 = new Product(57, "milky", "food", 10, "cora");

        repository.addNewProduct(p1);
        repository.addNewProduct(p2);

        allProducts = repository.stockSituation();
        assertEquals(2,allProducts.size());
    }

    @org.junit.Test
    public void tc01_repo_add_pass() throws IOException {
        emptyFile();
        repository = new StoreRepository("testproducts.txt");
        repository.readFile();

        Product p = new Product(56, "milk", "food", 10, "cora");
        assertEquals(true,repository.addNewProduct(p));

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
