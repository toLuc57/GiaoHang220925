<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fee page!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/mystyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/table.css">
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="card">
  <h3>List</h3>
  <table>
    <tr>
      <c:forEach items="${title}" var="i">
   	    <th>${i}</th>
      </c:forEach>
    </tr>
    <c:forEach items="${feeList}" var="i">
      <tr>
        <td>${i.feeid}</td>
        <td>${i.feename}</td>
        <td>${i.distance}</td>
        <td>${i.mass}</td>
        <td>${i.feeprice}</td>
      </tr>
    </c:forEach>
  </table>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>