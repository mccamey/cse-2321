
public class ProductTest {

	public static void main(String[] args) {

		Product product = new Product ();
		System.out.println(product.getPrice());
		
		product.setName("IT Chapter 2");
		System.out.println(product.getName());
		
		product.setType("Movie");
		System.out.println(product.getType());
		
		product.setPrice(995);
		System.out.println(product.getPrice());
		
		product.setQuantity(100);
		System.out.println(product.getName());

		product.setInventoryCode("C000005");
		System.out.println(product.getName());

		product.addUserRating(3);
		product.addUserRating(4);
		product.addUserRating(5);
		
		System.out.println(product.getUserRating(2));
		
		System.out.println(product.getUserRatingCount());
		
		System.out.println(product.getAvgUserRating());

	}

}
