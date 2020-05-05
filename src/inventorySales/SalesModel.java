package inventorySales;
import java.util.ArrayList;
import java.util.List;
import inventorySales.SalesData;

public class SalesModel {
	public int responseCode;
	public String responseMessage;
	
	List<SalesData> salesData = new ArrayList<SalesData>();
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
	
	public List<SalesData> getSalesData(){
		return salesData;
	}
	
	public void setSalesData(List<SalesData> salesData){
		this.salesData = salesData;
	}
}
