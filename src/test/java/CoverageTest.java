import junit.framework.TestCase;
import model.Product;
import repository.StoreRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Laura on 03-Apr-18.
 */
public class CoverageTest extends TestCase {
    private StoreRepository repository;

    private void emptyFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("testproducts.txt");
        writer.print("");
        writer.close();
    }

    public void test_tc1_repo_category_no_products_match() throws IOException {
        emptyFile();
        repository = new StoreRepository("testproducts.txt");
        repository.readFile();

        Product p1 = new Product(56, "milk", "food", 10, "cora");
        Product p2 = new Product(57, "milky", "food", 10, "cora");

        repository.addNewProduct(p1);
        repository.addNewProduct(p2);
        ArrayList<Product> foundProducts = repository.getProductsCategory("nothing");

        assertEquals(0,foundProducts.size());
    }

    public void test_tc2_repo_category_empty_products() throws IOException {
        emptyFile();
        repository = new StoreRepository("testproducts.txt");
        repository.readFile();

        ArrayList<Product> foundProducts = repository.getProductsCategory("nothing");

        assertEquals(0,foundProducts.size());
    }

    public void test_tc3_repo_category_is_match() throws IOException{
        emptyFile();
        repository = new StoreRepository("testproducts.txt");
        repository.readFile();

        Product p1 = new Product(56, "milk", "food", 10, "cora");
        Product p2 = new Product(57, "milky", "food", 10, "cora");

        repository.addNewProduct(p1);
        repository.addNewProduct(p2);
        ArrayList<Product> foundProducts = repository.getProductsCategory("food");

        assertEquals(2,foundProducts.size());
    }
}
