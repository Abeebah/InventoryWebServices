package inventoryUsers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inventoryDatabase.ConnectionClass;
import inventoryUsers.UsersData;
import inventoryUsers.Criteria;

public class UsersServices {
	Connection connector;
	PreparedStatement statement;
	ResultSet resultSet;
	ResultSet resultSet1;
	
	public UsersModel getUsers() {
		UsersModel usersModel = new UsersModel();
		List<UsersData> usersDataList = new ArrayList<UsersData>();
		
		try {
			connector =  ConnectionClass.dbconnect();
			statement = connector.prepareStatement(Criteria.QUERY_1);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				UsersData usersData = new UsersData();
				usersData.setId(resultSet.getInt("id"));
				usersData.setFirstName(resultSet.getString("firstName"));
				usersData.setLastName(resultSet.getString("lastName"));
				usersData.setUserName(resultSet.getString("userName"));
				usersData.setPassword(resultSet.getString("password"));
				usersData.setEmail(resultSet.getString("email"));
				usersData.setCompany(resultSet.getString("company"));
				usersData.setShortCode(00001);
				usersData.setShortMessage(Criteria.SUCCESS_MESSAGE);
				usersDataList.add(usersData);
			}
			usersModel.usersData = usersDataList; 
		}catch(Exception e)
		{
			usersModel.setResponseMessage(Criteria.ERROR_MESSAGE);
		}
		return usersModel;
	}
	
	
	public UsersData getUserData(int id) {
		UsersData usersData = new UsersData();
		try {
			connector =  ConnectionClass.dbconnect();
			statement = connector.prepareStatement(Criteria.QUERY_1);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				usersData.setId(resultSet.getInt("id"));
				usersData.setFirstName(resultSet.getString("firstName"));
				usersData.setLastName(resultSet.getString("lastName"));
				usersData.setEmail(resultSet.getString("email"));
				usersData.setUserName(resultSet.getString("userName"));
				usersData.setPassword(resultSet.getString("password"));
				usersData.setCompany(resultSet.getString("company"));
				usersData.setShortCode(00001);
				usersData.setShortMessage(Criteria.SUCCESS_MESSAGE);
			}
			
		}catch(Exception e)
		{
			usersData.setShortMessage(Criteria.ERROR_MESSAGE);
		}
		return usersData;
	}
	
	
	public UsersData processUserLogin(String username, String password) {
		UsersData usersData = new UsersData();
		connector = ConnectionClass.dbconnect();
		try{
			statement = connector.prepareStatement(Criteria.QUERY_111);
			statement.setString(1, username);
			statement.setString(2, password);
			resultSet1 = statement.executeQuery();
			if(resultSet1.next()){
				usersData.setId(resultSet1.getInt("id"));
				usersData.setFirstName(resultSet1.getString("firstname"));
				usersData.setLastName(resultSet1.getString("lastname"));
				usersData.setUserName(resultSet1.getString("username"));
				usersData.setEmail(resultSet1.getString("email"));
				usersData.setCompany(resultSet1.getString("company"));
				usersData.setRole(resultSet1.getString("role"));
				usersData.setShortCode(0);
				usersData.setShortMessage("Login Successful");
				}else{
				usersData.setShortCode(-1000);
				usersData.setShortMessage("Invalid Login Details");
				}
				}catch(Exception e) {
				usersData.setShortCode(-1001);
				usersData.setShortMessage("Connection Error");
				}
		return usersData;
	}
	
	public UsersData addUser(String firstname, String lastname, String email,  String phone, String username, String password, Object role, String company) {
		UsersData usersData = new UsersData();
		connector =  ConnectionClass.dbconnect();
		try {
			
			statement = connector.prepareStatement(Criteria.QUERY_2);
			statement.setString(1, firstname);
			statement.setString(2, lastname);
			statement.setString(3, email);
			statement.setString(4, phone);
			statement.setString(5, username);
			statement.setString(6, password);
			statement.setObject(7, role);
			statement.setString(8, company);
			int	retRow = statement.executeUpdate();
			if(retRow > 0)
				{
				usersData.setShortMessage(Criteria.SUCCESS_MESSAGE_ADDUSERS);
				}
		}catch(SQLException e) {
			usersData.setShortMessage(Criteria.ERROR_MESSAGE);
		}
		return usersData;
	}
	
	public UsersData updateUsers(String firstname, String lastname, String email,  String phone, String username, String password, Object role, String company, int id) {
		UsersData usersData = new UsersData();
		try {
			connector =  ConnectionClass.dbconnect();
			statement = connector.prepareStatement(Criteria.QUERY_3);
			statement.setString(1, firstname);
			statement.setString(2, lastname);
			statement.setString(3, email);
			statement.setString(4, phone);
			statement.setString(5, username);
			statement.setString(6, password);
			statement.setObject(7, role);
			statement.setString(8, company);
			statement.setInt(9, id);
			int	retUser = statement.executeUpdate();
			if(retUser > 0)
				{
				usersData.setShortMessage(Criteria.SUCCESS_MESSAGE_UPDATEUSERS);
				}
		}catch(SQLException e) {
			usersData.setShortMessage(Criteria.ERROR_MESSAGE_UPDATEUSERS);
		}
		return usersData;
	}
	

	public UsersData deleteUser(int id) {
		UsersData usersData = new UsersData();
		try {
		connector =  ConnectionClass.dbconnect();
		statement = connector.prepareStatement(Criteria.QUERY_4);
		statement.setInt(1, id);
		int retRow = statement.executeUpdate();
		if(retRow > 0) {
			usersData.setShortMessage(Criteria.SUCCESS_MESSAGE_DELUSERS);
		}
		}catch(SQLException e ){
			usersData.setShortMessage(Criteria.ERROR_MESSAGE_DELUSERS);
		}
		return usersData;
	}
}
