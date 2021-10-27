package run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String driver = "org.sqlite.JDBC";
		String DB_URL = "jdbc:sqlite:D:\\nidhin\\projects\\FetchResults.sqlite";
		Connection connection = null;

		Class.forName(driver);
		connection = DriverManager.getConnection(DB_URL);

		PreparedStatement statement = connection.prepareStatement("SELECT * FROM student_details");
		ResultSet resultSet = statement.executeQuery();
		ResultSetMetaData metadata = resultSet.getMetaData();
		int numberOfColumns = metadata.getColumnCount();
		while (resultSet.next()) {
			System.out.print(resultSet.getString("usn"));
			for (int i = 1; i <= numberOfColumns; i++)
				System.out.print(resultSet.getObject(i) + " ");
			System.out.println();
		}
	}
}
