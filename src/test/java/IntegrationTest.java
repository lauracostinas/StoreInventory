import junit.framework.TestCase;
import model.Product;
import repository.StoreRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Laura on 24-Apr-18.
 */
public class IntegrationTest extends TestCase {
    private StoreRepository repository;

    private void emptyFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("testproducts.txt");
        writer.print("");
        writer.close();
    }

    public void test_tc0_call_all() throws IOException{
        emptyFile();
        tc2_repo_add_pass();
        tc3_repo_category_no_products_match();
        tc1_repo_stock_all();
    }

    public void tc1_repo_stock_all() throws IOException {
        repository = new StoreRepository("testproducts.txt");
        repository.readFile();

        ArrayList<Product> stock = repository.stockSituation();

        assertEquals(1,stock.size());
    }

    public void tc2_repo_add_pass() throws IOException {
        repository = new StoreRepository("testproducts.txt");
        repository.readFile();

        Product p = new Product(1, "cocoa", "food", 1, "metro");
        assertEquals(true,repository.addNewProduct(p));
    }

    public void tc3_repo_category_no_products_match() throws IOException {
        repository = new StoreRepository("testproducts.txt");
        repository.readFile();

        ArrayList<Product> foundProducts = repository.getProductsCategory("something");

        assertEquals(0,foundProducts.size());
    }
}
