package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectTest3 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("名前の一部をを入力してください：");
		String nameLike = scan.nextLine();

		// URL、ユーザ名、パスワードの準備
		String url = "jdbc:postgresql:sample";
		String user = "student";
		String pass = "himitu";
		// SQL文の作成
		String sql = "SELECT * FROM emp WHERE name LIKE ?";

		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
				st.setString(1, "%"+ nameLike + "%");
				
				System.out.println("入力した後SQL文：\n" + st);
				
			try (ResultSet rs = st.executeQuery();) {
				while (rs.next()) {
					System.out.print(rs.getInt("code") + " : ");
					System.out.print(rs.getString("name") + " : ");
					System.out.print(rs.getInt("age") + " : ");
					System.out.println(rs.getString("tel"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		scan.close();

	}
}