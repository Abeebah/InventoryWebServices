package inventoryCustomer;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
	List<CustomerData> customerData = new ArrayList<CustomerData>();
	public int responseCode;
	public String responseMessage;
	
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
	
	public List<CustomerData> getCustomerData(){
		return customerData;
	}
	public void setCustomerData(List<CustomerData> customerData){
		this.customerData = customerData;
	}
}
