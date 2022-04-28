package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectOrderByTest {
	public static void main(String[] args) {
		//URL、ユーザ名、パスワードの準備
		String url = "jdbc:postgresql:sample";
		String user = "student";
		String pass = "himitu";
		//SQL文の作成
		String sql = "SELECT * FROM emp ORDER BY age";
		
		try {
			//JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
				
		try (//データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				//PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				//SQLの実行（SELECT文）
				ResultSet rs = st.executeQuery();) {
			//結果の取得及び表示
			while (rs.next()) {
				System.out.print(rs.getInt("code") + " : ");
				System.out.print(rs.getString("name") + " : ");
				System.out.print(rs.getInt("age") + " : ");
				System.out.println(rs.getString("tel"));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
}
}