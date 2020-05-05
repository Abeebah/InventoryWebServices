package InventoryCategory;

public class Criteria {
	public static String QUERY1 = "SELECT category.*, users.firstname, users.lastname FROM category INNER JOIN USERS ON USERS.ID = category.CREATED_BY";
	public static String QUERY11 = "SELECT category.*, users.firstname, users.lastname FROM category INNER JOIN USERS ON USERS.ID = category.CREATED_BY WHERE category.id=?";
	public static String QUERY_1 = "SELECT category.*, users.firstname, users.lastname FROM category INNER JOIN USERS ON USERS.ID = category.CREATED_BY";
	public static String SUCCESS_MESSAGE = "Category successful";
	public static String ERROR_MESSAGE = "Error getting category";
	
	public static String QUERY_2 = "INSERT INTO CATEGORY (id, cat_name, description, created_by) VALUES (?, ?, ?, ?)";
	public static String SUCCESS_MESSAGE_ADDCAT = "Category added successful";
	public static String ERROR_MESSAGE_ADDCAT = "Problem adding category";
	
	public static String QUERY_3 = "UPDATE category SET cat_name=?, description=?, modified_by=?, date_modified=? WHERE id=?";
	public static String SUCCESS_MESSAGE_UPDATECAT = "Category updated successful";
	public static String ERROR_MESSAGE_UPDATECAT = "Problem updating category";
	
	public static String QUERY_4 = "DELETE FROM category WHERE id=?";
	public static String SUCCESS_MESSAGE_DELCAT = "Category deleted successful";
	public static String ERROR_MESSAGE_DELCAT = "Problem deleting category";
}
