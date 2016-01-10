import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class Koneksi {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		String DB_URL = "jdbc:mysql://localhost/resto";
		String USER = "root";
		String PASS = "";
		final String DB_staff = "'D:/Ngampus/Workspace/Java/Resto [uas]/dataFiles/staff.txt'";

		try {

			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Nyoba konek DB...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Iso konek DB...");

			System.out.println("nggawe statement...");
			stmt = conn.createStatement();

			String sql = "LOAD DATA INFILE "+DB_staff+" INTO TABLE manager FIELDS TERMINATED BY ','";

			int rowsInserted = stmt.executeUpdate(sql);
			if (rowsInserted > 0) {
				System.out.println("Sukses!");
			}
			
			String sqli = "SELECT id, password, nama_depan, nama_belakang FROM manager";
			ResultSet rs = stmt.executeQuery(sqli);
			// Njupuk data tekan result set
			while (rs.next()) {
				// Ngampil data urut tekan kolom
				int id = rs.getInt("id");
				String pass = rs.getString("password");
				String ngarep = rs.getString("nama_depan");
				String mburi = rs.getString("nama_belakang");

				// Tampilke
				System.out.println("ID: " + id);
				System.out.println("password: " + pass);
				System.out.println("nama_depan: " + ngarep);
				System.out.println("nama_belakang: " + mburi);
			}
			rs.close();
		} catch (SQLException se) {
			// Handle errors u/ JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors u/ Class.forName
			e.printStackTrace();
		} finally {
			
			// finally block gae close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // ndolok ae
			
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} 
		System.out.println("Sukses!");
	}
}
