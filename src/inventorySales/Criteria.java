package inventorySales;

public class Criteria {
	//public static String QUERY_1 = "SELECT salles.*, PRODUCTS.NAME, PRODUCTS.PRICE, USERS.FIRSTNAME, USERS.LASTNAME FROM salles INNER JOIN PRODUCTS ON SALLES.PRODUCT_ID = PRODUCTS.ID INNER JOIN USERS ON SALLES.CREATED_BY=USERS.ID";
	public static String QUERY_1 = "SELECT salles.*, PRODUCTS.NAME, PRODUCTS.PRICE, USERS.FIRSTNAME, USERS.LASTNAME,CUSTOMERS.FIRSTNAME AS C_FIRSTNAME,\r\n" + 
			"CUSTOMERS.LASTNAME AS C_LASTNAME FROM salles INNER JOIN PRODUCTS ON SALLES.PRODUCT_ID = PRODUCTS.ID INNER JOIN USERS \r\n" + 
			"ON SALLES.CREATED_BY=USERS.ID INNER JOIN CUSTOMERS ON SALLES.CUSTOMER_ID=CUSTOMERS.ID";
	public static String QUERY_11 = "SELECT salles.*, PRODUCTS.NAME, PRODUCTS.PRICE, USERS.FIRSTNAME, USERS.LASTNAME FROM salles INNER JOIN PRODUCTS ON SALLES.PRODUCT_ID = PRODUCTS.ID INNER JOIN USERS ON SALLES.CREATED_BY=USERS.ID WHERE SALLES.ID=?";
	public static String SUCCESS_MESSAGE = "Successful";
	public static String ERROR_MESSAGE = "Error getting sales";
	
	public static String QUERY_2 = "INSERT INTO SALLES (product_id, customer_id, sale_quantity, created_by) VALUES (?,?,?,?)";
	public static String QUERY_22 = "UPDATE products SET quantity=? WHERE id=?";
	public static String SUCCESS_MESSAGE_ADDSALES = "Sales added successfully";
	public static String ERROR_MESSAGE_ADDSALES = "Problem adding sale";
	
	public static String QUERY_3 = "UPDATE salles SET sale_quantity=?, modified_by=?, date_modified=? WHERE id=?";
	public static String QUERY_33 = "UPDATE products SET quantity=? WHERE id=?";
	public static String SUCCESS_MESSAGE_UPDATESALES = "Sales updated successfully";
	public static String ERROR_MESSAGE_UPDATESALES = "Problem updating sales";
	
	public static String QUERY_4 = "DELETE FROM salles WHERE id=?";
	public static String SUCCESS_MESSAGE_DELSALES = "sales deleted successfully";
	public static String ERROR_MESSAGE_DELSALES = "Problem deleting sales";
}
