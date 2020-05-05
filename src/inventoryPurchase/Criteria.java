package inventoryPurchase;

public class Criteria {
	public static String QUERY1 = "SELECT (PURCHASE.ID),(PURCHASE.PRODUCT_ID), (PURCHASE.QUANTITY), (TO_CHAR(PURCHASE.DATE_CREATED, 'DAY Month YYYY')), (PURCHASE.CREATED_BY),(PURCHASE.DATE_MODIFIED), (PURCHASE.MODIFIED_BY), (PURCHASE.DATE_MODIFIED), PRODUCTS.NAME, PRODUCTS.COST, USERS.FIRSTNAME, USERS.LASTNAME FROM PURCHASE INNER JOIN PRODUCTS ON PURCHASE.PRODUCT_ID = PRODUCTS.ID INNER JOIN USERS ON PURCHASE.CREATED_BY=USERS.ID";
	public static String QUERY_11 = "SELECT (PURCHASE.ID),(PURCHASE.PRODUCT_ID), (PURCHASE.QUANTITY), (TO_CHAR(PURCHASE.DATE_CREATED, 'DAY Month YYYY')), (PURCHASE.CREATED_BY),(PURCHASE.DATE_MODIFIED), (PURCHASE.MODIFIED_BY), (PURCHASE.DATE_MODIFIED), PRODUCTS.NAME, PRODUCTS.COST, USERS.FIRSTNAME, USERS.LASTNAME FROM PURCHASE INNER JOIN PRODUCTS ON PURCHASE.PRODUCT_ID = PRODUCTS.ID INNER JOIN USERS ON PURCHASE.CREATED_BY=USERS.ID WHERE PURCHASE.ID = ?";
	public static String SUCCESS_MESSAGE = "Successful";
	public static String ERROR_MESSAGE = "Error getting purchase";
	
	public static String QUERY_2 = "INSERT INTO purchase (product_id, quantity,created_by) VALUES (?, ?, ? )";
	public static String QUERY_22 = "update products set quantity=? where id=?";
	public static String SUCCESS_MESSAGE_ADDPUR = "Purchase added successful";
	public static String ERROR_MESSAGE_ADDPUR = "Problem adding purchase";
	
	public static String QUERY_3 = "update purchase set quantity=?, modified_by=?, date_modified=? where id=?";
	public static String QUERY_33 = "update products set quantity=? where id=?";
	public static String SUCCESS_MESSAGE_UPDATEPUR = "Purchase updated successfully";
	public static String ERROR_MESSAGE_UPDATEPUR = "Problem updating purchase";
	
	public static String QUERY_4 = "DELETE FROM purchase WHERE id=?";
	public static String SUCCESS_MESSAGE_DELPUR = "Purchase deleted successful";
	public static String ERROR_MESSAGE_DELPUR = "Problem deleting purchase";
}
