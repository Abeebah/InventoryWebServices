package InventoryProduct;

import java.util.ArrayList;
import java.util.List;

//import inventoryProduct.ProductsData;

public class ProductModel {
	public int responseCode;
	public String responseMessage;
	
	List<ProductsData> productData = new ArrayList<ProductsData>();
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String
			responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	public List<ProductsData> getProductData(){
		return productData;
	}
	public void setProductsData(List<ProductsData> productData){
		this.productData = productData;
	}
}
