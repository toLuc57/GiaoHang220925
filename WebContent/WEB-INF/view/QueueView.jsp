<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quere page!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/mystyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/form.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/table.css">
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="row">
    <div class="card">
      <h3>Quere</h3>
      <div>
        <table>
          <tr>
            <c:forEach items="${title}" var="i">
              <th>${i}</th>
            </c:forEach>
          </tr>
          <c:forEach items="${list}" var="i">
          <input type="hidden" id="newRecords_${i.id}" name="newRecords" value="${i.id}">
            <tr>
              <td>${i.id}</td>
              <td>${i.idCustomer}</td>
              <td>${i.idShip}</td>
              <td>${i.idFee}</td>
              <td>${i.date}</td>
              <td>${i.origin}</td>
              <td>${i.destination}</td>
              <td>${i.getDurationNotice()}</td>
              <td>${i.getStatusNotice()}</td>
              <td>${i.price}</td>
            </tr>
          </c:forEach>
        </table>
</div>
</body>
</html>