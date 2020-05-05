package inventoryCustomer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import inventoryCustomer.Criteria;
import inventoryDatabase.ConnectionClass;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

public class CustomersServices extends ConnectionClass {
	Connection connector;
	PreparedStatement statement;
	PreparedStatement statement1;
	PreparedStatement statement2;
	ResultSet resultSet;
	OraclePreparedStatement pst = null;
	OracleResultSet rs = null;

	public CustomerModel getCustomers() {
		CustomerModel customerModel = new CustomerModel();
		List<CustomerData> customersModelLst = new ArrayList<CustomerData>();
		try {
			connector = ConnectionClass.dbconnect();
			statement = connector.prepareStatement(Criteria.QUERY_1);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int mID = resultSet.getInt("modified_by");
				String modifiedUser = getModifiedName(mID);
				CustomerData customerData = new CustomerData();
				customerData.setId(resultSet.getInt("id"));
				customerData.setFirstName(resultSet.getString("firstname"));
				customerData.setLastName(resultSet.getString("lastname"));
				customerData.setEmail(resultSet.getString("email"));
				customerData.setPhone(resultSet.getString("phone"));
				customerData.setAddress(resultSet.getString("address"));
				customerData.setDateCreated(resultSet.getString("date_created"));
				customerData.setCreatedByUser(resultSet.getString("name") + " " + resultSet.getString("last"));
				customerData.setDateModified(resultSet.getString("date_modified"));
				customerData.setModifiedByUser(modifiedUser);
				customerData.setShortCode(0000);
				customerData.setShortMessage(Criteria.SUCCESS_MESSAGE);

				customersModelLst.add(customerData);
			}
			customerModel.customerData = customersModelLst;
		} catch (SQLException e) {
			customerModel.setResponseCode(00001);
			customerModel.setResponseMessage(Criteria.ERROR_MESSAGE);
		}
		return customerModel;
	}

	private String getModifiedName(int mID) {
		String modifiedUser = "";
		try {
			connector = ConnectionClass.dbconnect();
			String query = "SELECT * FROM users WHERE id=?";
			pst = (OraclePreparedStatement) connector.prepareStatement(query);
			pst.setInt(1, mID);
			ResultSet rs7 = (OracleResultSet) pst.executeQuery();
			while (rs7.next()) {
				modifiedUser = rs7.getString("firstname") + " " + rs7.getString("lastname");
			}
			rs7.close();
			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return modifiedUser;
	}

	public CustomerData getCustomerData(int id) {
		CustomerData customerData = new CustomerData();
		try {
			connector = ConnectionClass.dbconnect();
			statement = connector.prepareStatement(Criteria.QUERY_11);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int mID = resultSet.getInt("modified_by");
				String modifiedUser = getModifiedName(mID);
				customerData.setFirstName(resultSet.getString("firstname"));
				customerData.setLastName(resultSet.getString("lastname"));
				customerData.setEmail(resultSet.getString("email"));
				customerData.setPhone(resultSet.getString("phone"));
				customerData.setAddress(resultSet.getString("address"));
				customerData.setDateCreated(resultSet.getString("date_created"));
				customerData.setCreatedByUser(resultSet.getString("name") + " " + resultSet.getString("last"));;
				customerData.setDateModified(resultSet.getString("date_modified"));
				customerData.setModifiedByUser(modifiedUser);
				customerData.setShortCode(0000);
				customerData.setShortMessage(Criteria.SUCCESS_MESSAGE);
			}
		} catch (Exception e) {
			customerData.setShortCode(-1000);
			customerData.setShortMessage(Criteria.SUCCESS_MESSAGE);
		}
		return customerData;
	}

	public CustomerData addCustomer(String firstname, String lastname, String email, String phone, String address, int user_id) {
		CustomerData customerData = new CustomerData();
		try {
			connector = ConnectionClass.dbconnect();
			statement = connector.prepareStatement(Criteria.QUERY_2);
			statement.setString(1, firstname);
			statement.setString(2, lastname);
			statement.setString(3, email);
			statement.setString(4, phone);
			statement.setString(5, address);
			statement.setInt(6, user_id);
			int retRow = statement.executeUpdate();
			if (retRow > 0) {
				customerData.setShortMessage(Criteria.SUCCESS_MESSAGE_ADDCUSTOMER);
			}
		} catch (SQLException e) {
			customerData.setShortMessage(Criteria.ERROR_MESSAGE_ADDCUSTOMER);
		}
		return customerData;
	}

	public CustomerData updateCustomer(String firstname, String lastname, String email, String phone, String address,
			int user_id, int id) {
		CustomerData customerData = new CustomerData();
		String date_modified;
		Date date = new Date();
		date_modified =date.toString();
		try {
			connector = ConnectionClass.dbconnect();
			statement = connector.prepareStatement(Criteria.QUERY_3);
			statement.setInt(1, id);
			statement.setString(2, firstname);
			statement.setString(3, lastname);
			statement.setString(4, email);
			statement.setString(5, phone);
			statement.setString(6, address);
			statement.setInt(7, user_id);
			statement.setString(8, date_modified);
			int retCustomer = statement.executeUpdate();
			if (retCustomer > 0) {
				customerData.setShortMessage(Criteria.SUCCESS_MESSAGE_UPDATECUSTOMER);
			}
		} catch (SQLException e) {
			customerData.setShortMessage(Criteria.ERROR_MESSAGE_UPDATECUSTOMER);
		}
		return customerData;
	}

	public CustomerData deleteCustomer(int id) {
		CustomerData customerData = new CustomerData();
		try {
			connector = ConnectionClass.dbconnect();
			statement = connector.prepareStatement(Criteria.QUERY_4);
			statement.setInt(1, id);
			int retRow = statement.executeUpdate();
			if (retRow > 0) {
				customerData.setShortMessage(Criteria.SUCCESS_MESSAGE_DELCUSTOMER);
			}
		} catch (SQLException e) {
			customerData.setShortMessage(Criteria.ERROR_MESSAGE_DELCUSTOMER);
		}

		return customerData;

	}
}
