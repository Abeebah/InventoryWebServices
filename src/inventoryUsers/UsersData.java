package inventoryUsers;

public class UsersData {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String username;
	private String role;
	private String company;
	private String dateCreated;
	private String createdByUser;
	private String dateModified;
	private String ModifiedByUser;
	private int shortCode;
	private String shortMessage;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDateModified() {
		return dateModified;
	}
	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}
	public String getCreatedByUser() {
		return createdByUser;
	}
	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}
	public String getModifiedByUser() {
		return ModifiedByUser;
	}
	public void setModifiedByUser(String modifiedByUser) {
		ModifiedByUser = modifiedByUser;
	}
	public int getId(){
		return id;
	}
	public int setId(int id) {
		return this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUserName() {
		return username;
	}
	
	public void setUserName(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
		}

	public void setPassword(String password) {
	this.password = password;
		}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getCompany() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public int getShortCode() {
		return shortCode;
	}
	public void setShortCode(int shortcode) {
		this.shortCode = shortcode;
	}
	
	public String getShortMessage() {
		return shortMessage;
	}
	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
		
	}
}
