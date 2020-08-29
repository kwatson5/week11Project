package shoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shoes.entity.Shoe;

public class ShoeDao {
	/**
	 * 
	 * @param shoeName
	 * @throws SQLException
	 */
	public void createShoe(String shoeName) throws SQLException {
		String sql = "INSERT INTO shoes (shoe_name) VALUES (?)";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DBConnection.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, shoeName);
			
			statement.executeUpdate();
		} 
		finally {
			if (statement != null) {
			statement.close();
		}
			
			if(connection != null) {
				connection.close();	
			}
		}	
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
public List<Shoe> getShoes() throws SQLException {
	String sql = "SELECT * FROM shoes";
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	List<Shoe> shoes = new ArrayList<>();
	
	try {
		connection = DBConnection.getInstance().getConnection();
		statement = connection.prepareStatement(sql);
		resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			long shoeId = resultSet.getLong("shoe_id");
			String shoeName = resultSet.getString("shoe_name");
			Shoe shoe = new Shoe(shoeId, shoeName);
			shoes.add(shoe);
		}
		
		return shoes;
	}
	finally {
		if (resultSet != null) {
			resultSet.close();
		}
		
		if (statement != null) {
			statement.close();
		}
			
		if(connection != null) {
			connection.close();	
		}
	}
  }

	/**
	 * 
	 * @param shoeId
	 * @param shoeName
	 * @throws SQLException
	 */
public void modifyShoe(long shoeId, String shoeName) throws SQLException {
	String sql = "UPDATE shoes SET shoe_name = ? WHERE shoe_id = ?";
	Connection connection = null;
	PreparedStatement statement = null;
	
	try {
		connection = DBConnection.getInstance().getConnection();
		statement = connection.prepareStatement(sql);
		statement.setString(1, shoeName);
		statement.setLong(2, shoeId);
		
		statement.executeUpdate();
	} 
	finally {
		if (statement != null) {
			statement.close();
		}
			
		if(connection != null) {
			connection.close();	
		}
	}
}

	/**
	 * 
	 * @param shoeId
	 * @throws SQLException
	 */
public void deleteShoe(long shoeId) throws SQLException {
	String sql = "DELETE FROM shoes WHERE shoe_id = ?";
	Connection connection = null;
	PreparedStatement statement = null;
	
	try {
		connection = DBConnection.getInstance().getConnection();
		statement = connection.prepareStatement(sql);
		statement.setLong(1, shoeId);
		
		statement.executeUpdate();
	} 
	finally {
		if (statement != null) {
			statement.close();
		}
			
		if(connection != null) {
			connection.close();	
		}
	}
}
}
