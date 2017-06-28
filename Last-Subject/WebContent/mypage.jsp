<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<jsp:include page="html-header.jsp">
		<jsp:param name="title" value="mypage" />
	</jsp:include>
     <head>
          <meta charset=UTF-8>
          <jsp:include page="page-header.jsp">
			<jsp:param  name="page" value="menu"/>
		</jsp:include>
     </head>
     <body>
		
		<h1>マイページ</h1>
		
		<h1>${ACCOUNT.username }</h1>
	
     </body>
</html>