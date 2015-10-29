import java.util.Random;
import play.*;
import play.db.*;

public class Global extends GlobalSettings {

    public void onStart(Application app) {
        Logger.info("Application has started");
        
        String createTableSql = "CREATE TABLE IF NOT EXISTS WORLD ("
				+ "ID NUMBER(5) NOT NULL, "
				+ "RANDOMNUMBER NUMBER(10) NOT NULL, "
				+ "PRIMARY KEY (ID) "
				+ ")";
        
        java.sql.Connection connection = null;
    	try {
    	   	connection = DB.getConnection(); 
    	   	connection.createStatement().execute(createTableSql);
    	   	Random rand = new Random();
    	   	for (int i = 1; i <= 10000; i++) {
        	   	connection.createStatement().executeUpdate(
        	   			"INSERT INTO WORLD (ID, RANDOMNUMBER) VALUES (" + 
        	   			i + "," + 
        	   			Math.abs(rand.nextInt(100000)) + ")");
    	   	}
    	} catch (java.sql.SQLException e) {
    		Logger.error("exception loading data", e);
    	} 
    	finally {
    		if (connection != null) {
        		try {
        			connection.close();
        		} catch (java.sql.SQLException e) {}
    		}
    	}
    }

    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }
}