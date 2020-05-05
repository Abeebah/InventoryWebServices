package InventoryCategory;

public class CategoryModel {
	private int id;
	private String name;
	private String description;
	private String dateCreated;
	private String createdByUser;
	private String ModifiedByUser;
	private String dateModified;
	private int shortCode;
	private String shortMessage;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

}
