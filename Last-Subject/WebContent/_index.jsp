<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<sql:query var="SCHEDULE" dataSource="ds/last_subject">
SELECT id,title, starttime, endtime FROM SCHEDULE
</sql:query>
<html>

	<jsp:include page="html-header.jsp">
		<jsp:param name="title" value="Top" />
	</jsp:include>
<head>
		<jsp:include page="page-header.jsp">
			<jsp:param  name="page" value="menu"/>
		</jsp:include>
<!-- 
		<script type="text/javascript" src="js/full.js"></script>
 -->
<script>
$(function() {
	$('#my-draggable').draggable({
	    revert: true,      // immediately snap back to original position
	    revertDuration: 0  //
	});
	
	$('#calendar').fullCalendar({
 		 header: {
 			left: "prev,next today",
 			center: "title",
 			right: "month agendaWeek agendaDay list"
 		 },
 			lang: "ja",
 			firstDay: 7,
 			titleFormat: 'YYYY年 MMMM',
 			defaultView: 'month',
 			
 	 // スロットの時間の書式
 			axisFormat: 'H:mm',
 	 // 時間の書式
 			timeFormat: 'H:mm',
 			
 			allDayText: "終日",
 			
 			buttonText: {
 			 prev: "＜", 
 			 next: "＞", 
 			 prevYear: "＜＜", 
 			 nextYear: "＞＞", 
 			 today: "今日",
 			 month: "月",
 			 week: "週",
 			 day: "日",
 			 list: "予定"
 			},
 			 monthNames: [
 			   "1月",
 			   "2月",
 			   "3月",
 			   "4月",
 			   "5月",
 			   "6月",
 			   "7月",
 			   "8月",
 			   "9月",
 			  "10月",
 			  "11月",
 			  "12月"
 			  ],
 			  monthNamesShort: [
 			    "1月",
 			    "2月",
 			    "3月",
 			    "4月",
 			    "5月",
 			    "6月",
 			    "7月",
 			    "8月",
 			    "9月",
 			   "10月",
 			   "11月",
 			   "12月"
 			   ],
 			   dayNames: ["日曜日", "月曜日", "火曜日", "水曜日", "木曜日", "金曜日", "土曜日"],
 			   dayNamesShort: ["日", "月", "火", "水", "木", "金", "土"],
 			   
 			    events: [
 			      <c:forEach var="SCHEDULE" items="${SCHEDULE.rows}">
 			  		 {  
			    	     id: '${SCHEDULE.id}',
 			  			 title: '${SCHEDULE.title}',
			    	     start: '${SCHEDULE.starttime}',
			    	     end: '${SCHEDULE.endtime}'
			    	 },
			      </c:forEach>
 			    ],
 			  	 editable: true,
 			  　      droppable: true,	 

 	});
 	
 	
});

</script>

</head>
     <body>
     	<br><p class="square_btn"><a href="new">New</a></p>
		<div id='calendar'></div>
     </body>
</html>