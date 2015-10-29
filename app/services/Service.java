package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import play.db.DB;

public class Service {
	
	public int dblookup(int id) {
		int result = -1;
		try (Connection c = DB.getConnection()) {
		    String sql = "select randomNumber from world where id = " + id;
		    try (PreparedStatement stmt = c.prepareStatement(sql)) {
		        ResultSet rs = stmt.executeQuery();
		        while (rs.next()) { // should only be one result
		        	result = rs.getInt("RANDOMNUMBER");
		        }	        
		    }	    
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}
}
