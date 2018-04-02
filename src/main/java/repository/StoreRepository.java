package repository;

import model.Product;

import java.io.*;
import java.util.ArrayList;

public class StoreRepository {
	private String filename;
	private ArrayList<Product> allProducts=new ArrayList<Product>();

	public StoreRepository(String filename) {
		this.filename = filename;
	}

	public ArrayList<Product> getAllProducts() {
		return allProducts;
	}
	public void readFile() throws NumberFormatException, IOException{
		FileInputStream f=new FileInputStream(filename);
		DataInputStream in = new DataInputStream(f);
		BufferedReader buf =new BufferedReader(new InputStreamReader(in));
		String rd;
		String []line;
		while((rd=buf.readLine())!=null){
		    if(!rd.equals("")) {
                line=rd.split(" ");
				this.allProducts.add(new Product(Integer.parseInt(line[0]),line[1],line[2],Integer.parseInt(line[3]),line[4]));
            }
		}
		in.close();
	}
	public boolean addNewProduct(Product p) throws IOException{
		if(
		        p.getCode()>0 &&
				p.getQuantity()>=0 &&
                p.getCode()<Integer.MAX_VALUE &&
                p.getQuantity()<Integer.MAX_VALUE &&
                p.getSupplier() != null &&
                !illegal(p.getName())){
			BufferedWriter out = new BufferedWriter(new FileWriter(filename,true));
			int k=1;
			for(Product i:allProducts){
				if(i.getCode()==p.getCode()){
					k=0;
				}
			}
			if(k==1){
				out.newLine();
				out.write(p.getCode()+" "+p.getName()+" "+p.getCategory()+" "+p.getQuantity()+" "+p.getSupplier());
				out.close();
				allProducts.add(p);
			}
			else{
				System.err.println("This code already exists");
				out.close();
				return false;
			}
		}
		else{
            System.err.println("One or more attributes are not valid");
            return false;
		}
		return true;
	}

	private boolean illegal(String name) {
		char c;
		for(int i=0;i<name.length();++i) {
			c = name.charAt(i);
			if (!((c <= 'z' && c >= 'a') || (c <= 'Z' && c >= 'A')|| (c <= '9' && c >= '1')))
				return true;
		}
		return false;
	}

	public ArrayList<Product> getProductsCategory(String cat){
		ArrayList<Product> cProducts=new ArrayList<Product>();
		for(Product p:allProducts){
			if(p.getCategory().compareTo(cat)==0){
				cProducts.add(p);
			}
		}
		return cProducts;
	}

	public ArrayList<Product> stockSituationProduct(String pname){
		ArrayList<Product> prods=new ArrayList<Product>();
		for(Product p:allProducts)
			if(p.getName().compareTo(pname)==0)
				prods.add(p);
		return prods;
	}
	public ArrayList<Product> stockSituation() {
		return allProducts;
	}

}
