package jp.co.comnic.javalesson.webapp.lastsubject.filters;

public class AuthenticatePathMatcher {

	public static boolean matches(String path) {

		if (path.matches("/login.*") || 
		    path.matches(".*(.css)|(.js)|(.gif)|(.png)|(.jpg)")) {

			return true;
		}
		
		return false;
	}
	
}
