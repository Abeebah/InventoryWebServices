package inventoryUsers;

public class Criteria {
	public static String QUERY_1 = "select * from users";
	public static String QUERY_11 = "select * from users where id=?";
	public static String QUERY_111 = "SELECT * FROM USERS WHERE USERNAME=? and PASSWORD=?";
	public static String SUCCESS_MESSAGE = "Successful";
	public static String ERROR_MESSAGE = "Error getting users";
	
	public static String QUERY_2 = "INSERT INTO users (firstname, lastname, email, phone, username, password,role, company) VALUES (?,?,?,?,?,?,?,?)";
	public static String SUCCESS_MESSAGE_ADDUSERS = "User added successfully";
	public static String ERROR_MESSAGE_ADDUSERS = "Problem adding user";
	
	public static String QUERY_3 = "UPDATE users SET firstname=?, lastname=?, email=?,  phone=?, username=?, password=?, role=?, company=? WHERE id=?";
	public static String SUCCESS_MESSAGE_UPDATEUSERS = "User updated successfully";
	public static String ERROR_MESSAGE_UPDATEUSERS = "Problem updating user";
	
	public static String QUERY_4 = "DELETE FROM users WHERE id=?";
	public static String SUCCESS_MESSAGE_DELUSERS = "User deleted successfully";
	public static String ERROR_MESSAGE_DELUSERS = "Problem deleting sales";
}
