package jp.co.comnic.javalesson.webapp.lastsubject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.comnic.javalesson.webapp.lastsubject.dao.BaseDao;
import jp.co.comnic.javalesson.webapp.lastsubject.dao.DaoException;



public class RemoveAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String servletPath = request.getServletPath();
		String forwardPath = "./";
		String redirectPath = "./";
		Integer id = Integer.parseInt(request.getParameter("id")); // 削除するレコードのID
		
		try {
			
			// リクエストされたサーブレット・パスから完全修飾クラス名を取得
			String entityClass = ControllerUtils.getFullyQualifiedClassName(servletPath);
			// DAOを使用してエンティティ・オブジェクトを削除
			new BaseDao().remove(Class.forName(entityClass), id);
			
			forwardPath = null;
			response.sendRedirect(redirectPath);
			
		} catch (DaoException e) {
			request.setAttribute("error", "[ERROR]: " + 
                                  ControllerUtils.getShortMessage(e));
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		return forwardPath;
	}

}
