package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PlusServlet2")
public class PlusServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String num1 = request.getParameter("value1"); //String num1 = ""; <- getない
		String num2 = request.getParameter("value2");//String num2 ＝null　↼getでページを読んだとき
		
		if(num1 == null || num1.length() == 0 || //num1.equal("")も可能
				num2 == null || num2.length() == 0) {
			//未入力データあり
			showNotEnterdError(out);
			//returnだけ書いたら処理終了
			return;
		}
		//ブロック外で使う可能性がある変数は外で宣言する！
		int answer = 0;
		try {
			int i1 = Integer.parseInt(num1);
			int i2 = Integer.parseInt(num2);
			answer = i1 + i2;
		} catch (NumberFormatException e) {
			// 整数ではない
			showNotIntegerError(out);
			return;
		}
		
		
		out.println("<html><head><title>Plus</title></head><body>");
		out.println(num1 + "　+　" + num2 + "=" + answer);
		out.println("</body></html>");
		
	}
	
	private void showNotEnterdError(PrintWriter out) {
		out.println("<html><head><title>Plus</title></head><body>");
		out.println("<h1>整数を２つ入力してください</h1>");
		out.println("</body></html>");
	}
	
	private void showNotIntegerError(PrintWriter out) {
		out.println("<html><head><title>Plus</title></head><body>");
		out.println("<h1>整数ではない値が入力されました！</h1>");
		out.println("</body></html>");
	}
	
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response)throws ServletException, IOException {
		
		doPost(request, response);
	}
}
