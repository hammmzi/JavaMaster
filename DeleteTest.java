package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteTest {

	public static void main(String[] args) {
		String url = "jdbc:postgresql:sample";
		String user = "student";
		String pass = "himitu";
		
		String sql = "DELETE FROM emp WHERE code=4";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try(Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			int rows = st.executeUpdate();
			System.out.println(rows + "件、レコードを削除ました。");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
