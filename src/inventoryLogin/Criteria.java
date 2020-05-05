package inventoryLogin;

public class Criteria {
	public static String QUERY_1 = "SELECT * FROM USERS WHERE USERNAME=? and PASSWORD=?";
	public static String QUERY_11 = "SELECT * FROM customers where id=?";
	public static String SUCCESS_MESSAGE = "Successful";
	public static String ERROR_MESSAGE = "Error getting customers";
	
	public static String QUERY_2 = "INSERT INTO CUSTOMERS (firstName, lastName, email, phone, address) VALUES (?, ?, ?, ?, ?)";
	public static String SUCCESS_MESSAGE_ADDCUSTOMER = "Customer added successfully";
	public static String ERROR_MESSAGE_ADDCUSTOMER = "Problem adding customer";
	
	public static String QUERY_3 = "UPDATE customers SET firstname=?,lastname=?, email=?, phone=?, address=? WHERE id=?";
	public static String SUCCESS_MESSAGE_UPDATECUSTOMER = "Customer updated successfully";
	public static String ERROR_MESSAGE_UPDATECUSTOMER = "Problem updating customer";
	
	public static String QUERY_4 = "DELETE FROM customers WHERE id=?";
	public static String SUCCESS_MESSAGE_DELCUSTOMER = "Customer deleted successfully";
	public static String ERROR_MESSAGE_DELCUSTOMER = "Problem deleting customer";
}
