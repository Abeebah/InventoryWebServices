package inventorySales;
public class SalesData {
	private int id;
	private int product_id;
	private String name;
	private String c_FirstName;
	private String c_LastName;
	private String customerName;
	private int customer_id;
	private int saleQuantity;
	private String dateCreated;
	private String createdByUser;
	private String dateModified;
	private String ModifiedByUser;
	public int responseCode;
	public String responseMessage;
	
	public String getC_FirstName() {
		return c_FirstName;
	}

	public void setC_FirstName(String c_FirstName) {
		this.c_FirstName = c_FirstName;
	}

	public String getC_LastName() {
		return c_LastName;
	}

	public void setC_LastName(String c_LastName) {
		this.c_LastName = c_LastName;
	}
	
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responsecode) {
		this.responseCode = responsecode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public int getSaleQuantity() {
		return saleQuantity;
	}

	public void setSaleQuantity(int saleQuantity) {
		this.saleQuantity = saleQuantity;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
