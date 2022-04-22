package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PlusServlet3")
public class PlusServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	
		//リクエストパラメータの読み込み
		String num1 = request.getParameter("value1");
		String num2 = request.getParameter("value2");
		
		//足し算の実行
		int i1 = Integer.parseInt(num1);
		int i2 = Integer.parseInt(num2);
		int answer = i1 + i2;
		
		//リクエストコープに必要なデータ
//		request.setAttribute("data1", Integer.valueOf(i1));
//		request.setAttribute("data2", Integer.valueOf(i2));
//		request.setAttribute("answer", Integer.valueOf(answer));
		
		request.setAttribute("data1", i1);
		request.setAttribute("data2", i2);
		request.setAttribute("answer", answer);
		
		//リクエストをAnswerServletに転送する
		RequestDispatcher rd = request.getRequestDispatcher("/AnswerServlet");
		rd.forward(request, response);
		}
}
