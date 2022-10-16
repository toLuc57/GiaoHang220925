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
            <c:forEach items="${map.get(i.id)}" var="j">
              <tr>
              	<td></td>
                <td>${j.goodsId}</td> 
                <td>${j.goodsName}</td>
                <td>${j.amount}</td> 
                <td>${j.price}</td> 
                <td></td>
                <td></td>
              </tr>
            </c:forEach>
          </c:forEach>
        </table>
    </div>
</div>
  
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>