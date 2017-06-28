<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="demo demo9">
  <div class="heading"><span>NabeSchedule</span></div>
</div>

   <div class="clearfix box_wrap">
	<div class="login-user">
		Hello！
	</div> 
	<div class="username">
		${loginUsername} さん
	</div>
	<div class="logout_btn">
		<a href="${ctxPath}logout.do"> Logout</a>
	</div>
   </div>