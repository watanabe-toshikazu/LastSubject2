package jp.co.comnic.javalesson.webapp.lastsubject.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.comnic.javalesson.webapp.lastsubject.controller.Action;
import jp.co.comnic.javalesson.webapp.lastsubject.controller.ActionFactory;

/**
 * Servlet implementation class FrontContoroller
 */

@WebServlet("*.do") // 「.do」が付くすべてのリクエストを受け付ける
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * <p></p>
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		System.out.println(request.getParameter("starttime"));
		// リクエストURLから適切なActionオブジェクト(ビジネス・ロジックの実行をカプセル化するオブジェクト)を取得
		Action action = ActionFactory.getAction(request.getServletPath());
		// Actionを実行して、転送先Viewのパスを取得
		String forwardPath = action.execute(request, response);
		
		// Actionの実装先でsendRedirectが実行されている場合にはレスポンスがすでに返送されているためnullが返る
		if (forwardPath != null) { // レスポンスがまだ返送されていなければ
			// 適切なViewに転送
			request.getRequestDispatcher(forwardPath).forward(request, response);
		}
	}
		
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}
}
