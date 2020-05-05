package InventoryProduct;

public class Criteria {
	public static String QUERY_1 = "SELECT PRODUCTS.*, CATEGORY.CAT_NAME,USERS.FIRSTNAME, USERS.LASTNAME FROM PRODUCTS INNER JOIN CATEGORY ON PRODUCTS.CATEGORY_ID = CATEGORY.ID INNER JOIN USERS ON PRODUCTS.CREATED_BY = USERS.ID ORDER BY  2,1";
	public static String QUERY_11 = "SELECT PRODUCTS.*, CATEGORY.CAT_NAME,USERS.FIRSTNAME, USERS.LASTNAME FROM PRODUCTS INNER JOIN CATEGORY ON PRODUCTS.CATEGORY_ID = CATEGORY.ID INNER JOIN USERS ON PRODUCTS.CREATED_BY = USERS.ID where products.id=?";
	public static String SUCCESS_MESSAGE = "Successful";
	public static String ERROR_MESSAGE = "Error getting product";
	
	public static String QUERY_2 = "INSERT INTO PRODUCTS (name, price, category_id, cost, created_by) VALUES (?, ?, ?, ?, ?)";
	public static String SUCCESS_MESSAGE_ADDPRODUCT = "Product added successful";
	public static String ERROR_MESSAGE_ADDPRODUCT = "Problem adding Product";
	
	public static String QUERY_3 = "UPDATE products SET name=?, price=?, category_id=?, cost=?, modified_by=?, date_modified=? WHERE id=?";
	public static String SUCCESS_MESSAGE_UPDATEPRODUCT = "Product updated successful";
	public static String ERROR_MESSAGE_UPDATEPRODUCT = "Problem updating product";
	
	public static String QUERY_4 = "DELETE FROM products WHERE id=?";
	public static String SUCCESS_MESSAGE_DELPRODUCT = "Product deleted successful";
	public static String ERROR_MESSAGE_DELPRODUCT = "Problem deleting product";
}
