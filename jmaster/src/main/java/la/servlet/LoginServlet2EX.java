package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet2EX")
public class LoginServlet2EX extends HttpServlet {
	// データベースの代わりにこのユーザ名とパスワードを正しいとする
	//定数で指定
	private static final String USER = "jack";
	private static final String PASS = "abc";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// リクエストパラメータの読み込み
		String action = request.getParameter("action");
		if (action.equals("login")) {
			// ログイン時はユーザ名とパスワードを取得する
			// パラメータのエラーチェックは省略
			String name = request.getParameter("name");
			String passWord = request.getParameter("pw");

			// ★未入力だった場合のエラー
			if (name.length() == 0 || passWord.length() == 0) {
				out.println("<html><head><title>ShowCart</title></head><body>");
				out.println("<h1>未入力項目があります</h1>");
				out.println("</body></html>");
				return;
			}

			if (name.equals(USER) && passWord.equals(PASS)) { // ユーザ名とパスワードが一致したら
				// ユーザ名とパスワードが一致したらログイン処理を行う
				// セッション管理を行う
				HttpSession session = request.getSession();
				// ログイン済みの属性を設定する
				session.setAttribute("isLogin", "true");
				out.println("<html><head><title>ShowCart</title></head><body>");
				out.println("<h1>ログイン成功！</h1>");
				out.println("</body></html>");
			} else {
				out.println("<html><head><title>ShowCart</title></head><body>");
				out.println("<h1>ユーザ名またはパスワードが違います</h1>");
				out.println("</body></html>");
			}
		} else if (action.equals("logout")) { // ログアウト時
			// すでに作成されているセッション領域を取得する。新しくは作成しない
			HttpSession session = request.getSession(false);
			if (session != null) {
				
				//★厳密にチェックしたいのでセッションがあればログイン情報を取得
				//★セッションがある＝ログインしている、とは限らないため →　Loginしてない状態で利用できるショッピングモールとか
				String isLogin = (String) session.getAttribute("isLogin");
				
				//getSession(flase)
				if(isLogin != null && isLogin.equals("true")) {//★ログイン情報のチェック
					// セッション領域を無効にする
					session.invalidate();
					out.println("<html><head><title>ShowCart</title></head><body>");
					out.println("<h1>ログアウトしました</h1>");
					out.println("</body></html>");
				
				}else{	//★ログインしてないとき
					out.println("<html><head><title>ShowCart</title></head><body>");
					out.println("<h1>ログインしてません</h1>");
					out.println("</body></html>");
				}
	
			}else{	//★ログインしてないとき
				out.println("<html><head><title>ShowCart</title></head><body>");
				out.println("<h1>ログインしてません</h1>");
				out.println("</body></html>");
			}
		} else {
			out.println("<html><head><title>ShowCart</title></head><body>");
			out.println("<h1>トップページからやりなおしてください</h1>");
			out.println("</body></html>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}