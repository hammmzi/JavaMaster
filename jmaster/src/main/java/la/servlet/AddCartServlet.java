package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//送信する文字コードの指定
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//リクエストパラメータの読み込み
		String productNo = request.getParameter("product_no");
		int no=  Integer.parseInt(productNo);
		String productName = null;
		
		//選択された商品の判別
		switch (no) {
		case 100:
			productName = "パソコン";
			break;
		case 101:
			productName = "プリンタ";
			break;
		case 102:
			productName = "デジタルカメラ";
			break;

		default:
			productName = "???";
			break;
		}
		
		//セッション領域の取得
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<String> cart = (ArrayList<String>)session.getAttribute("products");
		//初めての時はカートを作成
		if(cart == null ) {
			cart = new ArrayList<String>();
			session.setAttribute("products", cart);
		}
		//商品追加
		cart.add(productName);
		
		//クライアントごとのカート情報の表示
		out.println("<html><head><title>ShowCart</title></head><body>");
		out.println("現在のカートの中身は下記の通りです。<br><br>");
		for (int i = 0; i < cart.size(); i++) {
			out.println(i+1);
			out.println(":" + cart.get(i)+"<br>");
		}
		
		//商品リストへのリンク
		out.println("<hr><a href= '/jmaster/selectProduct3.html'>商品リスト</a> ");
		out.println("</body></html>");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
