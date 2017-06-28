<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<jsp:include page="html-header.jsp">
		<jsp:param name="title" value="New" />
	</jsp:include>
     <head>
          <meta charset=UTF-8>
          <jsp:include page="page-header.jsp"></jsp:include>
     </head>
     <body>
    
     	<p class="square_btn"><a href="./">Back</a></p>
     	 <div id="new" >
     	 <p class='form-title'>新規作成</p>
     	 
         <form action="schedule/insert.do" method="get">

           <p><label for="name">タイトル名 </label></p>
           <p class="title"><input type="text" name="title" value="${title}" required></p>
           
           <p><label for="hire_date">開始日時 </label></p>
           <p class="start"><input type="datetime-local" name="starttime" value="${strattime}" required></p>
          
           <p><label for="hire_date">終了日時 </label></p>
           <p class="start"><input type="datetime-local" name="endtime" value="${endtime}" required></p>
           
           <p><label for="text">メモ </label></p>
           <p class="title"><textarea name="memo"　value="${memo}" rows="4" cols="40"></textarea></p>
           
           <p class="new_reset"><input type="reset" value="リセット"></p>
           <p class="new_submit"><input type="submit" value="登録" ></p>
        </form>
        </div>
    
     </body>
   <jsp:include page="page-footer.jsp"></jsp:include>
</html>