package jp.co.comnic.javalesson.webapp.lastsubject.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;

@WebListener
public class ActionFactory implements ServletContextListener {
	
	
	private static ServletContext context;

	
	public static Action getAction(String servletPath) throws ServletException {
		
		Action action = null;
		
		//actionClassNameにjspのaction=""内のサーブレットパスに対応するActionの実装クラス名を取得。
		String actionClassName = context.getInitParameter(servletPath);
		
		try {
			action = (Action)Class.forName(actionClassName).newInstance();
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		return action;
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		//アプリが起動したときにServletContextオブジェクトを受け取る。getはすでに存在するものを受け取る意味がある。
		context = sce.getServletContext();
	}
}
