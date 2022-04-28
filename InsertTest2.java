package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("社員番号をを入力してください：");
		int code = scan.nextInt();
		System.out.print("名前をを入力してください：");
		String name = scan.next();
		System.out.print("年齢をを入力してください：");
		int age = scan.nextInt();
		System.out.print("電話番号をを入力してください：");
		String tel = scan.next();

		// URL、ユーザ名、パスワードの準備
		String url = "jdbc:postgresql:sample";
		String user = "student";
		String pass = "himitu";
		// SQL文の作成
		String sql = "INSERT INTO emp VALUES(?,?,?,?)";
		String sql2 = "SELECT * FROM emp";

		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (// データースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
				
				st.setInt(1, code);
				st.setString(2, name);
				st.setInt(3, age);
				st.setString(4, tel);
				
				System.out.println("入力した後SQL文：\n" + st);
				
				int rows = st.executeUpdate();
				System.out.println(rows + "件、データベースに追加しました。");
				
				try(
						PreparedStatement st2 = con.prepareStatement(sql2);
						ResultSet rs = st2.executeQuery();
						) {
//					System.out.print(rs.getInt("code") + " : ");
//					System.out.print(rs.getString("name") + " : ");
//					System.out.print(rs.getInt("age") + " : ");
//					System.out.println(rs.getString("tel"));
					
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
