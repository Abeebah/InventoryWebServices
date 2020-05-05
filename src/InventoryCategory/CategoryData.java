package InventoryCategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryData {
	
	List<CategoryModel> categoryModel = new ArrayList<CategoryModel>();
	public int responseCode;
	public String responseMessage;
	
	public List<CategoryModel> getCategoryModel(){
		return categoryModel;
	}
	
	public void setCategoryModel(List<CategoryModel> categoryModel) {
		this.categoryModel = categoryModel;
	}
	
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	
	public String getResponseMessage() {
		return responseMessage;
	}
	
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	
}
