package InventoryProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import inventoryDatabase.ConnectionClass;
import InventoryProduct.ProductsData;
import InventoryProduct.Criteria;
import java.util.Date;

public class ProductServices extends ConnectionClass{
	Connection connector;
	PreparedStatement statement;
	PreparedStatement statement1;
	PreparedStatement statement2;
	ResultSet resultSet;
	OraclePreparedStatement pst = null;
	OracleResultSet rs = null;
	
	
	public ProductModel getProduct() {
		ProductModel productModel = new ProductModel();
		List<ProductsData> productDataList = new ArrayList<ProductsData>();
		try {
			connector = ConnectionClass.dbconnect();
			statement = connector.prepareStatement(Criteria.QUERY_1);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int mID = resultSet.getInt("modified_by");
				String modifiedUser = getModifiedName(mID);
				ProductsData productData = new ProductsData();
				productData.setId(resultSet.getInt("id"));
				productData.setName(resultSet.getString("name"));
				productData.setPrice(resultSet.getInt("price"));
				productData.setQuantity(resultSet.getInt("quantity"));
				productData.setCategory(resultSet.getString("cat_name"));
				productData.setCost(resultSet.getInt("cost"));
				productData.setDateCreated(resultSet.getString("date_created"));
				productData.setCreatedByUser(resultSet.getString("firstname") +" "+resultSet.getString("lastname"));
				productData.setDateModified(resultSet.getString("date_modified"));
				productData.setModifiedByUser(modifiedUser);
				productData.setShortCode(0);
				productData.setShortMessage(Criteria.SUCCESS_MESSAGE);
				productDataList.add(productData);
			}
			productModel.productData = productDataList;
			
		} catch (SQLException e) {
			productModel.setResponseCode(1);
			productModel.setResponseMessage(Criteria.ERROR_MESSAGE);
		}
		return productModel;
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

	
	public ProductsData getProductdata(int id) {
		ProductsData productData = new ProductsData();
		try {
			connector = ConnectionClass.dbconnect();
			statement = connector.prepareStatement(Criteria.QUERY_11);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int mID = resultSet.getInt("modified_by");
				String modifiedUser = getModifiedName(mID);
				productData.setId(resultSet.getInt("id"));
				productData.setName(resultSet.getString("name"));
				productData.setPrice(resultSet.getInt("price"));
				productData.setQuantity(resultSet.getInt("quantity"));
				productData.setCategory(resultSet.getString("category_id"));
				productData.setCost(resultSet.getInt("cost"));
				productData.setDateCreated(resultSet.getString("date_created"));
				productData.setDateCreated(resultSet.getString("date_created"));
				productData.setCreatedByUser(resultSet.getString("firstname") +" "+resultSet.getString("lastname"));
				productData.setDateModified(resultSet.getString("date_modified"));
				productData.setModifiedByUser(modifiedUser);
				productData.setShortCode(0);
				productData.setShortMessage(Criteria.SUCCESS_MESSAGE);
			}
		}catch(Exception e) {
			productData.setShortCode(1);
			productData.setShortMessage(Criteria.ERROR_MESSAGE);
		}
		return productData;
	}
	
	public ProductsData addProduct(String name, Double price, int category, Double cost, int user_id) {
		ProductsData productData = new ProductsData();
		try {
			connector =  ConnectionClass.dbconnect();
			statement1 = connector.prepareStatement(Criteria.QUERY_2);
			statement1.setString(1, name);
			statement1.setDouble(2, price);
			statement1.setInt(3, category);
			statement1.setDouble(4, cost);
			statement1.setDouble(5, user_id);
			int	retRow = statement1.executeUpdate();
			if(retRow > 0)
				{
					productData.setShortMessage(Criteria.SUCCESS_MESSAGE_ADDPRODUCT);
				}
				else
				{
					productData.setShortMessage(Criteria.ERROR_MESSAGE);
				}
		}catch(SQLException e) {
			
		}
		return productData;
	}
	
	public ProductsData updateProduct(String name, Double price, int category, Double cost, int user_id, int id) {
		ProductsData productData = new ProductsData();
		String date_modified;
		Date date = new Date();
		date_modified =date.toString();
		try {
			connector =  ConnectionClass.dbconnect();
			statement2 = connector.prepareStatement(Criteria.QUERY_3);
			statement2.setString(1, name);
			statement2.setDouble(2, price);
			statement2.setInt(3, category);
			statement2.setDouble(4, cost);
			statement2.setInt(5, user_id);
			statement2.setString(6, date_modified);
			statement2.setInt(7, id);
			int	retProduct = statement2.executeUpdate();
			if(retProduct > 0)
				{
					productData.setShortMessage(Criteria.SUCCESS_MESSAGE_UPDATEPRODUCT);
				}
		}catch(SQLException e) {
			productData.setShortMessage(Criteria.ERROR_MESSAGE_UPDATEPRODUCT);
		}
		return productData;
	}
	
	public ProductsData deleteProduct(int id) {
		ProductsData productsData = new ProductsData();
		try {
		connector =  ConnectionClass.dbconnect();
		statement2 = connector.prepareStatement(Criteria.QUERY_4);
		statement2.setInt(1, id);
		int retRow = statement2.executeUpdate();
		if(retRow > 0) {
			productsData.setShortMessage(Criteria.SUCCESS_MESSAGE_DELPRODUCT);
		}
		}catch(SQLException e ){
			productsData.setShortMessage(Criteria.ERROR_MESSAGE_DELPRODUCT);
		}
		return productsData;
	}
}
