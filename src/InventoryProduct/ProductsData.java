package InventoryProduct;

public class ProductsData {
	private int id;
	private String name;
	private int price;
	private int quantity;
	private String category;
	private int cost;
	private String dateCreated;
	private String createdByUser;
	private String dateModified;
	private String ModifiedByUser;
	private String shortMessage;
	private int shortCode;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id ;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price){
		this.price = price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public String getShortMessage() {
		return shortMessage;
		}

		public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
		}

		public int getShortCode() {
		return shortCode;
		}

		public void setShortCode(int shortCode) {
		this.shortCode = shortCode;
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
		
}
