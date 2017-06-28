<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<sql:query var="table" dataSource="ds/Last_Subject">
	${param.sql}
	<sql:param value="%${param.name}%" />
</sql:query>

			<c:forEach var="record" items="${table.rowsByIndex}">
					<c:forEach var="data" items="${record}" varStatus="loop">
						<c:if test="${loop.count == 1}">
							<c:set var="id" value="${data}" />
						</c:if>
						<td>${data}</td>
					</c:forEach>
			</c:forEach>
			<c:set var="id" value="${id + 1}"/>
			