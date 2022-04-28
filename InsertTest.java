package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest {

	public static void main(String[] args) {
		String url = "jdbc:postgresql:sample";
		String user = "student";
		String pass = "himitu";
		
		String sql = "INSERT INTO emp(code,name,age,tel) VALUES(8,'近藤',29,'09-999-9999')";
		String sql2 = "INSERT INTO emp(code,name,age,tel) VALUES(9,'松本',33,'01-2222-8899')";
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

//		try(Connection con = DriverManager.getConnection(url, user, pass);
//				PreparedStatement st = con.prepareStatement(sql);) {
//			int rows = st.executeUpdate();
//			System.out.println(rows + "件、データベースに追加しました。");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		try(Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql2);) {
			int rows = st.executeUpdate();
			System.out.println(rows + "件、データベースに追加しました。");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
