package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTest {

	public static void main(String[] args) {
		String url = "jdbc:postgresql:sample";
		String user = "student";
		String pass = "himitu";
		//UPDATE 위치 SET 바꿀정보 WHERE 바꾸고 싶은 정보
		String sql = "UPDATE emp SET age=27 WHERE code=1";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try(Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			int rows = st.executeUpdate();
			System.out.println(rows + "件、レコードを変更しました。");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
