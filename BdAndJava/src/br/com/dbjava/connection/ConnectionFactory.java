package br.com.dbjava.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectionFactory {
	
	private static final String USERNAME = "tt";
	private static final String PASSWORD = "dbaaccesskey";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3307/system?characterEncoding=utf8";
	
	public static Connection createConnectioToMySQL() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection = (Connection) DriverManager.getConnection(DATABASE_URL, USERNAME,PASSWORD);
		
		return connection;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con = createConnectioToMySQL();
		
		if (con!=null) {
			System.out.println("Conexão bem sucedida");
			con.close();
		}
	}
	
}
