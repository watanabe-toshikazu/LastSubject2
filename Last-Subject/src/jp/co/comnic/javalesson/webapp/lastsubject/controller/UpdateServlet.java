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
		
		
		System.out.println("date:" + request.getParameter("starttime"));
//		String strstrat= request.getParameter("starttime");
//		request.getParameterMap().put("starttime", new String[]{strstrat.replace(" ", "'T'")});
//		
//		String strend= request.getParameter("endtime");
//		request.getParameterMap().put("endtime", new String[]{strstrat.replace(" ", "'T'")});
		
		Integer id = Integer.parseInt(request.getParameter("eventId")); // 更新するレコードのID
		
		System.out.println(request.getParameter("eventId"));
		
		try {
			ScheduleDao Schedao= new ScheduleDao();
			Object entity=Schedao.findById(id);
			
			// リクエスト・パラメータの値を使用してエンティティ・オブジェクトのフィールド値を設定
			ControllerUtils.populateEntity(request, entity);

			new BaseDao().update(entity);
			
//			Schedao.update(Schedule.class);
			
//		} catch (DaoException e) {
//			request.setAttribute("error", "[ERROR]: " + 
//                                  ControllerUtils.getShortMessage(e));
		} catch (Exception e) {
			e.printStackTrace();
//			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
