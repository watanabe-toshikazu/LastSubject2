<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<jsp:include page="html-header.jsp">
		<jsp:param name="title" value="Login" />
	</jsp:include>

    <head>
          <meta charset=UTF-8>
          <title>Login</title>
 	<div class="demo demo9">
 		 <div class="heading"><span>NabeSchedule</span></div>
	</div>
     </head>
    <body>
     <div id="form">
     	<p class='form-title'>Login</p>
     	
       <form action="login.do" method="post">
     	<p>ID</p>
		 <p class="email"><input type="email" name="email"></p>
		<p>Password</p>
		 <p class="password"><input type="password" name="password"></p>
		 
		 <p class="submit"><input type="submit" name="確認" value="ログイン" required></p>
		</form>
		
		<p class="error">${error}</p>
     </div>
    </body>
</html>