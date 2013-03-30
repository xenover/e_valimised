package evalimised;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.appengine.api.rdbms.AppEngineDriver;

public class CreateConnection {

    public static Connection getConnection() {

		Connection c = null;
		try {
			DriverManager.registerDriver(new AppEngineDriver());
		    c = DriverManager.getConnection("jdbc:google:rdbms://riigipoore:riigipoore/evalimised", "root", "");
		 } catch (SQLException e) {
		        e.printStackTrace();
		 }
		return c;

    }
}
