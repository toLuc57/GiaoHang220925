<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Receipt page!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/mystyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/table.css">
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<div class="row">
    <div class="card">
      <h3>Receipt</h3>
        <table>
          <tr>
            <c:forEach items="${title}" var="i">
              <th>${i}</th>
            </c:forEach>
          </tr>
          <c:forEach items="${receiptList}" var="i">
            <tr>
              <td>${i.receiptid}</td>
              <td>${i.receiptname}</td>
              <td>${i.distance}</td>
              <td>${i.mass}</td>
              <td>${i.receiptprice}</td>
            </tr>
            <c:forEach items="${invoiceDetails}" var="j">
              <tr>
                <td></td>
                <td>${j.goodid}</td> 
                <td>${j.goodname}</td>
                <td>${j.amount}</td> 
                <td>${j.price}</td> 
              </tr>
            </c:forEach>
          </c:forEach>
        </table>
    </div>
</div>
  
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>