package InventoryCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import inventoryDatabase.ConnectionClass;
import InventoryCategory.CategoryModel;

public class CategoryServices extends ConnectionClass {
	Connection connector;
	PreparedStatement statement;
	PreparedStatement statement1;
	PreparedStatement statement2;
	PreparedStatement statement3;
	ResultSet resultSet;
	OraclePreparedStatement pst = null;
	OraclePreparedStatement pst1 = null;
	OracleResultSet rs = null;
	OracleResultSet rs1 = null;
	OracleResultSet rs2 = null;

	public CategoryModel getCategoryData(int id) {
		CategoryModel categoryModel = new CategoryModel();
		try {
			connector = ConnectionClass.dbconnect();
			pst = (OraclePreparedStatement) connector.prepareStatement(Criteria.QUERY11);
			pst.setInt(1, id);
			rs = (OracleResultSet) pst.executeQuery();
			while (rs.next()) {
				int mID = rs.getInt("modified_by");
				String modifiedUser = getModifiedName(mID);
				categoryModel.setName(rs.getString("cat_name"));
				categoryModel.setDescription(rs.getString("description"));
				categoryModel.setCreatedByUser(rs.getString("firstname") +" "+rs.getString("lastname"));
				categoryModel.setDateModified(rs.getString("date_modified"));
				categoryModel.setModifiedByUser(modifiedUser);
				categoryModel.setShortCode(00001);
				categoryModel.setShortMessage(Criteria.SUCCESS_MESSAGE);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			categoryModel.setShortMessage(Criteria.ERROR_MESSAGE);
		}
		return categoryModel;
	}

	public CategoryData getCategory() {
		CategoryData categoryData = new CategoryData();
		List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
		try {
			connector = ConnectionClass.dbconnect();
			statement = connector.prepareStatement(Criteria.QUERY1);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int mID = resultSet.getInt("modified_by");
				String modifiedUser = getModifiedName(mID);
				CategoryModel categoryModel = new CategoryModel();
				categoryModel.setId(resultSet.getInt("id"));
				categoryModel.setName(resultSet.getString("cat_name"));
				categoryModel.setDescription(resultSet.getString("description"));
				categoryModel.setDateCreated(resultSet.getString("date_created"));
				categoryModel.setCreatedByUser(resultSet.getString("firstname") +" "+resultSet.getString("lastname"));
				categoryModel.setDateModified(resultSet.getString("date_modified"));
				categoryModel.setModifiedByUser(modifiedUser);
				categoryModel.setShortCode(00001);
				categoryModel.setShortMessage(Criteria.SUCCESS_MESSAGE);
				categoryModelList.add(categoryModel);
			}
			categoryData.categoryModel = categoryModelList;

		} catch (Exception e) {
			categoryData.setResponseMessage(Criteria.ERROR_MESSAGE);
		}
		return categoryData;
	}

	public CategoryModel addCategoryData(int ids, String cat_name, String description, int user_id) {
		CategoryModel categoryModel = new CategoryModel();
		
		try {
			ids = generateID();
			connector = ConnectionClass.dbconnect();
			pst = (OraclePreparedStatement) connector.prepareStatement(Criteria.QUERY_2);
			pst.setInt(1, ids);
			pst.setString(2, cat_name);
			pst.setString(3, description);
			pst.setInt(4, user_id);
			int ret = pst.executeUpdate();
			if (ret > 0) {
				categoryModel.setShortMessage(Criteria.SUCCESS_MESSAGE);
			} else {
				categoryModel.setShortMessage(Criteria.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			categoryModel.setShortMessage(e.getMessage());
		}
		return categoryModel;
	}
	
	public int getRows() {
		connector = ConnectionClass.dbconnect();
		int rows = 0;
		try {
			String query = "SELECT * FROM category";
			pst = (OraclePreparedStatement) connector.prepareStatement(query);
			rs1 = (OracleResultSet) pst.executeQuery();
			while(rs1.next()) {
				rows++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return rows;
	}
	
	
	public int generateID() {
		connector = ConnectionClass.dbconnect();
		int[] id;
		int max = 0;
		try {
			String query = "SELECT * FROM category";
			pst = (OraclePreparedStatement) connector.prepareStatement(query);
			rs = (OracleResultSet) pst.executeQuery();
			int leng = getRows();
			id = new int[leng];
			int j = 0;
			while(rs.next()) {
				id[j] = rs.getInt("id");
				j++;
			}
			max = getMaxValue(id);
			max++;
		}catch(Exception e){
			e.printStackTrace();
		}
		return max;
	}

	public static int getMaxValue(int[] numbers){
		  int maxValue = numbers[0];
		  for(int i=1; i < numbers.length;i++){
		    if(numbers[i] > maxValue){
			  maxValue = numbers[i];
			} 
		  }
		  return maxValue;
}

	public CategoryModel updateCategoryData(int id, String cat_name, String description,int user_id) {
		CategoryModel categoryModel = new CategoryModel();
		String date_modified;
		Date date = new Date();
		date_modified =date.toString();
		try {
			connector = ConnectionClass.dbconnect();
			statement2 = connector.prepareStatement(Criteria.QUERY_3);
			statement2.setString(1, cat_name);
			statement2.setString(2, description);
			statement2.setInt(3, user_id);
			statement2.setString(4,date_modified);
			statement2.setInt(3, id);
			int retRow = statement2.executeUpdate();
			if (retRow > 0) {
				categoryModel.setShortMessage(Criteria.SUCCESS_MESSAGE_UPDATECAT);
			} else {
				categoryModel.setShortMessage(Criteria.ERROR_MESSAGE_UPDATECAT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			categoryModel.setShortMessage(e.getMessage());
		}
		return categoryModel;
	}

	public CategoryModel deleteCategoryData(int id) {
		CategoryModel categoryModel = new CategoryModel();
		try {
			connector = ConnectionClass.dbconnect();
			statement2 = connector.prepareStatement(Criteria.QUERY_4);
			statement2.setInt(1, id);
			int retDelete = statement2.executeUpdate();
			if (retDelete > 0) {
				categoryModel.setShortMessage(Criteria.SUCCESS_MESSAGE_DELCAT);
			} else {
				categoryModel.setShortMessage(Criteria.ERROR_MESSAGE_DELCAT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			categoryModel.setShortMessage(e.getMessage());
		}
		return categoryModel;
	}
	
	private String getModifiedName(int mID) {
		String mName = "";
		try {
    		connector = ConnectionClass.dbconnect();
    		String query = "SELECT * FROM users WHERE id=?";
    		pst = (OraclePreparedStatement) connector.prepareStatement(query);
    		pst.setInt(1, mID);
    		ResultSet rs1 = (OracleResultSet) pst.executeQuery();
    		while (rs1.next()) {
    			mName = rs1.getString("firstname") + " " + rs1.getString("lastname");	
			}
    		rs1.close();
    		pst.close();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
		return mName;
	}   		
}
