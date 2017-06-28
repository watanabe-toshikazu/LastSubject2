package jp.co.comnic.javalesson.webapp.lastsubject.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.comnic.javalesson.webapp.lastsubject.dao.BaseDao;
import jp.co.comnic.javalesson.webapp.lastsubject.dao.DaoException;
import jp.co.comnic.javalesson.webapp.lastsubject.dao.ScheduleDao;
import jp.co.comnic.javalesson.webapp.lastsubject.model.Schedule;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
Integer id = Integer.parseInt(request.getParameter("id")); // 削除するレコードのID
		
		try {
			ScheduleDao Schedao= new ScheduleDao();

System.out.println("OK");
			
			// リクエスト・パラメータの値を使用してエンティティ・オブジェクトのフィールド値を設定
			ControllerUtils.populateEntity(request, Schedao.findById(id));
			new BaseDao().update(Schedule.class);
			
		} catch (DaoException e) {
			request.setAttribute("error", "[ERROR]: " + 
                                  ControllerUtils.getShortMessage(e));
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


}
