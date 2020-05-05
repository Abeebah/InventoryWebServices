package inventoryPurchase;

import java.util.ArrayList;
import java.util.List;
import inventoryPurchase.PurchaseData;

public class PurchaseModel {
	public int responseCode;
	public String responseMessage;
	
	List<PurchaseData> purchaseData = new ArrayList<PurchaseData>();
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
	
	public List<PurchaseData> getPurchaseData(){
		return purchaseData;
	}
	public void setPurchaseData(List<PurchaseData> purchaseData){
		this.purchaseData = purchaseData;
	}
}
