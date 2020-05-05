package inventoryPurchase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import inventoryDatabase.ConnectionClass;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
//import inventoryDatabase.ConnectionClass;

import inventoryPurchase.PurchaseData;

import inventoryPurchase.Criteria;
public class PurchaseServices extends ConnectionClass {
	Connection connector;
	PreparedStatement statement;
	PreparedStatement statement1;
	PreparedStatement statement2;
	PreparedStatement statement3;
	PreparedStatement statement4;
	ResultSet resultSet;
	ResultSet resultSet1;
	ResultSet resultSet2;
	ResultSet resultSet3;
	OraclePreparedStatement pst = null;
	OracleResultSet rs = null;
	
	public PurchaseModel getPurchase() {
		PurchaseModel purchaseModel = new PurchaseModel();
		List<PurchaseData> purchaseDataList = new ArrayList<PurchaseData>();
		try {
			connector =  ConnectionClass.dbconnect();
			statement = connector.prepareStatement(Criteria.QUERY1);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int mID = resultSet.getInt("modified_by");
				String modifiedUser = getModifiedName(mID);
				PurchaseData purchaseData = new PurchaseData();
				purchaseData.setId(resultSet.getInt("id"));
				purchaseData.setProduct_id(resultSet.getInt("product_id"));
				purchaseData.setName(resultSet.getString("name"));
				purchaseData.setQuantity(resultSet.getInt("quantity"));
				purchaseData.setCost(resultSet.getDouble("cost"));
				purchaseData.setDateCreated(resultSet.getString("date_created"));
				purchaseData.setCreatedByUser(resultSet.getString("firstname") +" "+resultSet.getString("lastname"));
				purchaseData.setDateModified(resultSet.getString("date_modified"));
				purchaseData.setModifiedByUser(modifiedUser);
				purchaseData.setShortCode(0000);
				purchaseData.setShortMessage(Criteria.SUCCESS_MESSAGE);
				purchaseDataList.add(purchaseData);
			}
			purchaseModel.purchaseData = purchaseDataList; 
		}catch(SQLException e) {
			purchaseModel.setResponseCode(1);
			purchaseModel.setResponseMessage(Criteria.ERROR_MESSAGE);
		}
		return purchaseModel;
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
	
	public PurchaseData getPurchaseData(int id) {
		PurchaseData purchaseData = new PurchaseData();
		connector = ConnectionClass.dbconnect();
		try {
			statement = connector.prepareStatement(Criteria.QUERY_11);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int mID = resultSet.getInt("modified_by");
				String modifiedUser = getModifiedName(mID);
				purchaseData.setId(resultSet.getInt("id"));
				purchaseData.setProduct_id(resultSet.getInt("product_id"));
				purchaseData.setQuantity(resultSet.getInt("quantity"));
				purchaseData.setCost(resultSet.getDouble("cost"));
				purchaseData.setDateCreated(resultSet.getString("date_created"));
				purchaseData.setCreatedByUser(resultSet.getString("firstname") +" "+resultSet.getString("lastname"));
				purchaseData.setDateModified(resultSet.getString("date_modified"));
				purchaseData.setModifiedByUser(modifiedUser);
				purchaseData.setShortCode(0);
				purchaseData.setShortMessage(Criteria.SUCCESS_MESSAGE);
			}
		}catch(Exception e) {
			purchaseData.setShortCode(-1000);
			purchaseData.setShortMessage(Criteria.ERROR_MESSAGE);
		}
		return purchaseData;
	}
	
	public PurchaseData addPurchase(int product, int quantity, int user_id) {
		PurchaseData purchaseData = new PurchaseData();
		connector =  ConnectionClass.dbconnect();
		PreparedStatement statement1 = null;
		PreparedStatement statement2 = null;
		//ResultSet resultSet;
		try {
			statement1 = connector.prepareStatement(Criteria.QUERY_2);
			int qty = getPreviousQuantity(product);
			int nQty = qty + quantity;
		
			statement1.setInt(1, product);
			statement1.setInt(2, quantity);
			statement1.setInt(3, user_id);
			int	retRow = statement1.executeUpdate();
			
			statement2 = connector.prepareStatement(Criteria.QUERY_22);
			statement2.setInt(1, nQty);
			statement2.setInt(2, product);
			int	retRow1 = statement2.executeUpdate();
			
			if((retRow > 0) && (retRow1 > 0))
				{
				purchaseData.setShortCode(0);
					purchaseData.setShortMessage(Criteria.SUCCESS_MESSAGE_ADDPUR);
				}
				
		}catch(SQLException e) {
			purchaseData.setShortCode(-1000);
			purchaseData.setShortMessage(Criteria.ERROR_MESSAGE_ADDPUR);
		}
		return purchaseData;
	}
	
	private int getPreviousQuantity(int product) {
		PreparedStatement statement1 = null;
		ResultSet resultSet1 = null;
		int qtty = 0;
		try {
			String query = "SELECT * FROM products WHERE id=?";
			statement1 = connector.prepareStatement(query);
			statement1.setInt(1, product);
			resultSet1 = statement1.executeQuery();
			while(resultSet1.next()) {
				qtty = resultSet1.getInt("quantity");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return qtty;
	}
	
	public PurchaseData updatePurchase(int product, int quantity, int user_id, int id) {
		PurchaseData purchaseData = new PurchaseData();
		PreparedStatement statement3 = null;
		PreparedStatement statement4 = null;
		connector = ConnectionClass.dbconnect();
		String date_modified;
		Date date = new Date();
		date_modified =date.toString();
		try {
			int newPurchaseQuantity = 0;
			int newProductQuantity = 0;
			int purchaseQty = getPurchaseQuantity(id);
			int productQuantity = getPreviousQuantity(product);
			if(purchaseQty > quantity) {
				newPurchaseQuantity =  purchaseQty - quantity;
				newProductQuantity = productQuantity - newPurchaseQuantity;
			}else if(purchaseQty < quantity) {
				newPurchaseQuantity = quantity - purchaseQty;
				newProductQuantity = productQuantity + newPurchaseQuantity;
			}
			statement3 = connector.prepareStatement(Criteria.QUERY_3);
			statement3.setInt(1, quantity);
			statement3.setInt(2, user_id);
			statement3.setString(3, date_modified);
			statement3.setInt(4, id);
			
			statement4 = connector.prepareStatement(Criteria.QUERY_33);
			statement4.setInt(1, newProductQuantity);
			statement4.setInt(2, product);
			
			int ret = statement3.executeUpdate();
			int ret2 = statement4.executeUpdate();

			if((ret > 0 ) && (ret2 > 0))
			{
				purchaseData.setShortMessage(Criteria.SUCCESS_MESSAGE_UPDATEPUR);
			}
			
		} catch (Exception e) {
			purchaseData.setShortMessage(Criteria.ERROR_MESSAGE_UPDATEPUR);
		}
		return purchaseData;	
	}

	private int getPurchaseQuantity(int id){
		PreparedStatement statement3;
		ResultSet resultSet3;
		int previousQty = 0;
		try {
			String query = "SELECT * FROM PURCHASE WHERE id=?"; 
			statement3 = connector.prepareStatement(query);
			statement3.setInt(1, id);
			resultSet3 = statement3.executeQuery();
			while(resultSet3.next()){
				previousQty = resultSet3.getInt("quantity");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return previousQty;	
	}
	
	public PurchaseData deletePurchase(int id) {
		PurchaseData purchaseData = new PurchaseData();
		try {
		connector =  ConnectionClass.dbconnect();
		statement2 = connector.prepareStatement(Criteria.QUERY_4);
		statement2.setInt(1, id);
		int retRow = statement2.executeUpdate();
		if(retRow > 0) {
			purchaseData.setShortMessage(Criteria.SUCCESS_MESSAGE_DELPUR);
		}
		}catch(SQLException e ){
			purchaseData.setShortMessage(Criteria.ERROR_MESSAGE_DELPUR);
		}
		
		return purchaseData;
	}
}
