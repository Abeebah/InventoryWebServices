package inventoryUsers;

import java.util.ArrayList;
import java.util.List;

import inventoryUsers.UsersData;

public class UsersModel {
	List<UsersData> usersData = new ArrayList<UsersData>();
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
	
	public List<UsersData> getUsersData(){
		return usersData;
	}
	public void setUsersData(List<UsersData> usersData){
		this.usersData = usersData;
	}
}
