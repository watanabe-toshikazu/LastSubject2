<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<sql:query var="SCHEDULE" dataSource="ds/last_subject">
SELECT id,title, starttime, endtime, memo FROM SCHEDULE
</sql:query>

<html>
　　　<jsp:include page="html-header.jsp">
		<jsp:param name="title" value="Top" />
	</jsp:include>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <jsp:include page="page-header.jsp">
		<jsp:param  name="page" value="menu"/>
	</jsp:include>
	
</head>
<body>
    　　　　<br><p class="new_btn"><a href="new">新規作成</a></p>
          <div id="myCalendar"></div>

    <div id="fullCalModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                      <span aria-hidden="true">×</span> <span class="sr-only">close</span>
                   </button>
                 	<p>予定変更</p>
                 	<p>予定名</p>
                    <input type="text" id="modalTitle" name="title" class="modal-title" />
                    <p>開始時間</p>
                    <input type="datetime-local" id="modalStarttime" name="start" class="modal-start" />
                    <p>終了時間</p>
                    <input type="datetime-local" id="modalEndtime" name="end" class="modal-end" />
                    <p>メモ</p>
                    <textarea id="modalMemo" name="memo" class="modal-memo" rows="4" cols="40" ></textarea>
                </div>
                <div id="modalBody" class="modal-body"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" id="edit" class="btn btn-success" data-dismiss="modal">Edit</button>
                    <button type="button" id="delete" class="btn btn-danger" data-dismiss="modal">Delete</button>
                    
                    <!--  
                    <a class="btn btn-primary" id="eventUrl" target="_blank">Event Page</a>
                    -->
                    
                </div>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function() {
        	
        	var calenderEvent;
        	
        	$('#myCalendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'prev title next',
                    right: 'month agendaWeek agendaDay list'
                },
                lang: "ja",
     			firstDay: 7,
     			titleFormat: 'YYYY年 MMMM',
     			defaultView: 'month',
     			timeFormat: 'H:mm',
     			axisFormat: 'H:mm',
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
     			
     			//イベントクリックしたとき
                eventClick:  function(event, jsEvent, view) {
                	console.log(event);
                	calendarEvent = event;
                	$('#delete').attr('eventId',event.id);
                	
                    $('#modalTitle').html(event.title);
                    $('#modalStarttime').html(event.start);
                    $('#modalEndtime').html(event.end);
                    $('#modalMemo').html(event.memo);
                    
                    $('#modalBody').html(event.description);
                    $('#eventUrl').attr('href',event.url);
                    $('#fullCalModal').modal();
                    return false;
                },
                
                //イベント
                events:
                [
                   <c:forEach var="SCHEDULE" items="${SCHEDULE.rows}">
                   {
                	  id: '${SCHEDULE.id}',
                	  title: '${SCHEDULE.title}',
			    	  start: '${SCHEDULE.starttime}',
			    	  end: '${SCHEDULE.endtime}',
			    	  description:"<p>--------------------------------------------------------------------</p><p>メモ：${SCHEDULE.memo}</p><p>--------------------------------------------------------------------</p>",
                   },
                   </c:forEach>
                ]       
            });
        		
        	$('#delete').on('click', function(event) {
            	var eventId = event.target.getAttribute('eventId');
                $('#myCalendar').fullCalendar("removeEvents", eventId);
                $.ajax({
                    url: 'http://localhost:8080/Last-Subject/RemoveServlet',
                    type: 'POST',
                    dataType: 'json',
                    data : {'eventId': eventId},
                    timeout: 10000,
                }).fail(function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log(XMLHttpRequest)
                })
            });
      	  	  
      	  $('#edit').on('click', function(event) {
      		  	console.log()
      		  	calendarEvent.title = $('#modalTitle').val();
      			calendarEvent.start = $('#modalStarttime').val();
      			calendarEvent.end = $('#modalEndtime').val();
      			calendarEvent.memo = $('#modalMemo').val();
                $('#myCalendar').fullCalendar("updateEvent", calendarEvent);
                console.log(calendarEvent.start);
                $.ajax({
                    url: 'http://localhost:8080/Last-Subject/UpdateServlet',
                    type: 'POST',
                    dataType: 'json',
                    data : {'eventId': calendarEvent.id,'title': calendarEvent.title,'starttime': calendarEvent.start._i,'endtime': calendarEvent.end._i,'memo': calendarEvent.memo},
                    timeout: 10000,
                }).fail(function(XMLHttpRequest, textStatus, errorThrown) {
              	  	alert("XMLHttpRequest : " + XMLHttpRequest.status)
                    alert("textStatus     : " + textStatus)
                    alert("errorThrown    : " + errorThrown.message)
                })
            });
        });
    </script>
</body>
	<jsp:include page="page-footer.jsp"></jsp:include>
</html>