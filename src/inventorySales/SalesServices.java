package inventorySales;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import inventoryDatabase.ConnectionClass;
import inventoryPurchase.PurchaseData;
import inventorySales.Criteria;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

public class SalesServices extends ConnectionClass{
	OraclePreparedStatement pst = null;
	OracleResultSet rs = null;
	Connection connector = null;;
	PreparedStatement statement ;
	PreparedStatement statement1;
	PreparedStatement statement2=null;
	PreparedStatement statement3 = null;
	PreparedStatement statement4 ;
	PreparedStatement statement5 ;
	PreparedStatement statement6 ;
	PreparedStatement statement7 ;
	ResultSet resultSet = null;
	ResultSet resultSet1 = null;
	ResultSet resultSet2 ;
	ResultSet resultSet3 ;
	ResultSet resultSet6 ;

	public SalesModel getSales() {
		SalesModel salesModel = new SalesModel();
		List<SalesData> salesDataList = new ArrayList<SalesData>();
		try {
			connector =  ConnectionClass.dbconnect();
			statement = connector.prepareStatement(Criteria.QUERY_1);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int mID = resultSet.getInt("modified_by");
				String modifiedUser = getModifiedName(mID);
				//String customerName =(resultSet.getString("customer.firstname") + resultSet.getString("customer.lastname"));
				SalesData salesData = new SalesData();
				salesData.setId(resultSet.getInt("id"));
				salesData.setProduct_id(resultSet.getInt("product_id"));
				salesData.setName(resultSet.getString("name"));
				salesData.setCustomerName(resultSet.getString("c_firstName") +" "+resultSet.getString("c_lastName"));
				salesData.setSaleQuantity(resultSet.getInt("sale_quantity"));
				salesData.setDateCreated(resultSet.getString("date_created"));
				salesData.setCreatedByUser(resultSet.getString("firstname") +" "+resultSet.getString("lastname"));
				salesData.setDateModified(resultSet.getString("date_modified"));
				salesData.setModifiedByUser(modifiedUser);
				salesData.setResponseCode(0000);
				salesData.setResponseMessage(Criteria.SUCCESS_MESSAGE);
				salesDataList.add(salesData);	
			}
			salesModel.salesData = salesDataList;
		}catch(SQLException e) {
			salesModel.setResponseCode(0001);
			salesModel.setResponseMessage(Criteria.ERROR_MESSAGE);
		}
		return salesModel;
	}
	
	private String getModifiedName(int mID) {
		String modifiedUser = "";
		try {
			connector = ConnectionClass.dbconnect();
			String query = "SELECT * FROM users WHERE id=?";
			pst = (OraclePreparedStatement) connector.prepareStatement(query);
			pst.setInt(1, mID);
			ResultSet rs6 = (OracleResultSet) pst.executeQuery();
			while (rs6.next()) {
				modifiedUser = rs6.getString("firstname") + " " + rs6.getString("lastname");
			}
			rs6.close();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modifiedUser;
	}
	
	public SalesData getSaleData(int id) {
		SalesData salesData= new SalesData();
		connector = ConnectionClass.dbconnect();
		try {
			statement = connector.prepareStatement(Criteria.QUERY_11);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				int mID = resultSet.getInt("modified_by");
				String modifiedUser = getModifiedName(mID);
				salesData.setId(resultSet.getInt("id"));
				salesData.setProduct_id(resultSet.getInt("product_id"));
				salesData.setCustomer_id(resultSet.getInt("customer_id"));
				salesData.setSaleQuantity(resultSet.getInt("sale_quantity"));
				salesData.setDateCreated(resultSet.getString("date_created"));
				salesData.setCreatedByUser(resultSet.getString("firstname") +" "+resultSet.getString("lastname"));
				salesData.setDateModified(resultSet.getString("date_modified"));
				salesData.setModifiedByUser(modifiedUser);
				salesData.setResponseCode(0000);
				salesData.setResponseMessage(Criteria.SUCCESS_MESSAGE);
			}
		}catch(SQLException e) {
			salesData.setResponseCode(-1000);
			salesData.setResponseMessage(Criteria.ERROR_MESSAGE);
		}
		return salesData;
	}
	
	public SalesData addSale(int product_id, int customer_id, int quantity,int user_id) {
		SalesData salesData = new SalesData();
		connector =  ConnectionClass.dbconnect();
		int qty = getPreviousQuantity(product_id);
		int newQty = qty - quantity;
		if(quantity > qty) {
			salesData.setResponseCode(20);
			salesData.setResponseMessage("Product out of stock");
		}else {
		try {
			statement1 = connector.prepareStatement(Criteria.QUERY_2);
			statement1.setInt(1, product_id);
			statement1.setInt(2, customer_id);
			statement1.setInt(3, quantity);
			statement1.setInt(4, user_id);
			int ret1 = statement1.executeUpdate();
			
			statement2 = connector.prepareStatement(Criteria.QUERY_22);
			statement2.setInt(1, newQty);
			statement2.setInt(2, product_id);
			int ret2 = statement2.executeUpdate();
			
			if((ret1>0) && (ret2>0)) {
				salesData.setResponseCode(0);
				salesData.setResponseMessage(inventorySales.Criteria.SUCCESS_MESSAGE_ADDSALES);
			}
		}catch(SQLException e) {
			salesData.setResponseCode(-1000);
			salesData.setResponseMessage(Criteria.ERROR_MESSAGE_ADDSALES);
		}
		}
		return salesData;
		}
	private int getProductId(String name) {
		PreparedStatement statement5 = null;
		ResultSet resultSet5 = null;
		int productId =0;
		try {
			String query = "SELECT * FROM products where name=?";
			statement5 = (PreparedStatement) connector.prepareStatement(query);
			statement5.setString(1, name);
			resultSet5 = statement5.executeQuery();
			while(resultSet5.next()) {
				productId = resultSet5.getInt("id");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return productId;
		}
	
	public SalesData updateSale( int product_id, int quantity, int user_id,  int id ) {
		SalesData  salesData = new SalesData();
		connector =  ConnectionClass.dbconnect();
		PreparedStatement statement5 = null;
		PreparedStatement statement6 = null;
		String date_modified;
		Date date = new Date();
		date_modified =date.toString();
		int newSaleQuantity = 0;
		int newProductQuantity = 0;
		int newSale = 0;
		int productQty = getPreviousQuantity(product_id);
		int saleQty = getPreviousSaleQuantity(id);
		try {
			
			if(quantity > saleQty) {
				newSale = quantity - saleQty;
				newSaleQuantity = saleQty + newSale;
				newProductQuantity = productQty - newSale;
			}else {
				newSale = saleQty - quantity;
				newSaleQuantity = saleQty - newSale;
				newProductQuantity = productQty + newSale;
			}	
			statement5 = connector.prepareStatement(Criteria.QUERY_3);
			statement5.setInt(1, quantity);
			statement5.setInt(2, user_id);
			statement5.setString(3, date_modified);
			statement5.setInt(4, id);
			int ret = statement5.executeUpdate();
			
			statement6 = connector.prepareStatement(Criteria.QUERY_33);
			statement6.setInt(1, newProductQuantity);
			statement6.setInt(2, product_id);
			int ret2 = statement6.executeUpdate();
			
			if((ret>0) && (ret2 > 0)) {
				salesData.setResponseMessage(Criteria.SUCCESS_MESSAGE_UPDATESALES);
			}
		}catch(SQLException exx) {
			salesData.setResponseMessage(Criteria.ERROR_MESSAGE_UPDATESALES);
		}
		return salesData;
	}
	
	public SalesData deleteSale(int id) {
		SalesData salesData = new SalesData();
		try {
		connector =  ConnectionClass.dbconnect();
		statement7 = connector.prepareStatement(Criteria.QUERY_4);
		statement7.setInt(1, id);
		int retRow = statement7.executeUpdate();
		if(retRow > 0) {
			salesData.setResponseMessage(Criteria.SUCCESS_MESSAGE_DELSALES);
		}
		}catch(SQLException e ){
			salesData.setResponseMessage(Criteria.ERROR_MESSAGE_DELSALES);
		}
		
		return salesData;
	}
		
	private int getPreviousSaleQuantity(int id) {
		PreparedStatement statement5 = null;
		ResultSet resultSet5 = null;
		int qtty =0;
		try {
			String query = "SELECT * FROM salles WHERE id=?";
			statement5 = (PreparedStatement) connector.prepareStatement(query);
			statement5.setInt(1, id);
			resultSet5 = statement5.executeQuery();
			while(resultSet5.next()) {
				qtty = resultSet5.getInt("sale_quantity");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return qtty;
	}	
	
	private int getPreviousQuantity(int product_id) {
		int qtty = 0;
		try {
			String query = "SELECT * FROM products WHERE id=?";
			statement3 = connector.prepareStatement(query);
			statement3.setInt(1, product_id);
			resultSet3 = statement3.executeQuery();
			while(resultSet3.next()) {
				qtty = resultSet3.getInt("quantity");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return qtty;
		
	}
}
