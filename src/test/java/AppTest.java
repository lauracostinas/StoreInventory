import controller.StoreController;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import model.Product;
import org.junit.Before;
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

    public void test_tc1_repo_add_pass() throws IOException {
        emptyFile();
        repository = new StoreRepository("testproducts.txt");
        repository.readFile();

        Product p = new Product(1, "a", "abc", 0, "abc");
        assertEquals(true,repository.addNewProduct(p));
    }

    public void test_tc2_repo_add_invalid_fields() throws IOException {
        emptyFile();
        repository = new StoreRepository("testproducts.txt");
        repository.readFile();

        Product p = new Product(-1, "ab c", "ab c", -1, "a bc");
        assertEquals(false,repository.addNewProduct(p));
    }

    public void test_tc3_repo_add_null_strings() throws IOException {
        emptyFile();
        repository = new StoreRepository("testproducts.txt");
        repository.readFile();

        Product p = new Product(10, null, null, 100, null);
        assertEquals(false,repository.addNewProduct(p));
    }

    public void test_tc4_repo_add_exceed_int() throws IOException {
        emptyFile();
        repository = new StoreRepository("testproducts.txt");
        repository.readFile();

        Product p = new Product(Integer.MAX_VALUE+1, "z", "1234abc", Integer.MAX_VALUE+1, "1234abc");
        assertEquals(false,repository.addNewProduct(p));
    }

    public void test_tc5_repo_add_special_strings() throws IOException {
        emptyFile();
        repository = new StoreRepository("testproducts.txt");
        repository.readFile();

        Product p = new Product(0, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", "&01", 1, "&01");
        assertEquals(false,repository.addNewProduct(p));
    }

    public void test_tc6_repo_add_maxint_min1() throws IOException {
        emptyFile();
        repository = new StoreRepository("testproducts.txt");
        repository.readFile();

        Product p = new Product(Integer.MAX_VALUE-1, "milk", "*%&aaa", Integer.MAX_VALUE-1, "*%&aaa");
        assertEquals(true,repository.addNewProduct(p));
    }

    public void test_tc7_repo_add_empty_string_maxint() throws IOException {
        emptyFile();
        repository = new StoreRepository("testproducts.txt");
        repository.readFile();

        Product p = new Product(Integer.MAX_VALUE, "", "", Integer.MAX_VALUE, "");
        assertEquals(false,repository.addNewProduct(p));
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
