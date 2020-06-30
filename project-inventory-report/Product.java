import java.util.ArrayList;

/**
 * A simple class for holding product information.
 *
 * @author Ian McCamey
 * @version September 18th, 2019
 */

public class Product {

	private String name;
	private String type;
	private String code;
	private int price;
	private int quantity;
	private ArrayList<Integer> reviews;

    /**
     * Product constructor - creates a default product with no name or type and
     * a price of 0.
     */
    public Product() {
    	this.name = "";
    	this.price = 0;
    	this.type = "";
    	this.reviews = new ArrayList<Integer>();
    }

    /**
     * sets the name of the product object
     *
     * @param name: new name for the product
     */
    public void setName(String name) {
    	this.name = name;
    }

    /**
     * returns the name of the product
     *
     * @return the name of the product
     */
    public String getName() {
        return this.name;
    }

    /**
     * sets the type of the product
     *
     * @param type: the type of the product
     */
    public void setType(String type) {
    	this.type = type;
    }

    /**
     * returns the type of the product
     *
     * @return - the product type
     */
    public String getType() {
        return this.type;
    }

    /**
     * sets the price of the product in cents. A $10 product will have a price
     * of 1000.
     *
     * @param price: the price of the product
     */
    public void setPrice(int price) {
    	this.price = price;
    }

    /**
     * returns the price of the product in cents.
     *
     * @return the price of the product
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * sets the count of this product in our inventory.
     *
     * @param quantity: the number of this product in inventory
     */
    public void setQuantity(int quantity) {
    	this.quantity = quantity;
    }

    /**
     * returns the count of this product in our inventory
     *
     * @return the number of this product in inventory
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * sets the inventory code for this product
     *
     * @param code: the new inventory code for the product
     */
    public void setInventoryCode(String code) {
    	this.code = code;
    }

    /**
     * returns the inventory code for this product
     *
     * @return the inventory code of the product
     */
    public String getInventoryCode() {
        return this.code;
    }

    /**
     * This method appends a new rating to the end of the rating list
     *
     * @param rating: the new rating to add to this product
     */
    public void addUserRating(int rating) {
    	this.reviews.add((this.reviews.size()), rating);
    }

    /**
     * @param index: the index of the rating we want to see
     *
     * @return the rating held at the given index within the ArrayList of ratings
     */
    public int getUserRating(int index) {
        return this.reviews.get(index);
    }

    /**
     * @return the number of ratings associated with this product
     */
    public int getUserRatingCount() {
        return this.reviews.size();
    }

    /**
     * This method computes the average user rating on demand 
     * from a stored list of ratings.
     *
     * @return the average rating for this product as a whole integer value
     */
    public int getAvgUserRating() {
        int sum = 0;
        if(reviews.size() != 0) {
        	for(int i = 0; i < reviews.size(); i++) {
        		sum += reviews.get(i);
        	}
        	return (sum/reviews.size());
        }else
        	return 0;
    }
}
