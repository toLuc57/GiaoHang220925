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
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/form.css">
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<div class="row">
    <div class="card">
      <h3>Login</h3>
      <form action="transport" method="post">
      	<p style="color:red" >${errorString}</p>
        <label for="from">From </label>
        <input type="text" id="from" name="from" 
        placeholder="From" value="${from}">
        <label for="to">To</label>
        <input type="text" id="to" name="to" 
        placeholder="To" value="${to}">
        <label for="mass">Mass (gram)</label>
        <input type="number" id="mass" name="mass" 
        placeholder="Mass" value="${mass}" step="250">
        <input type="submit" value="Search">
        <!-- doPost() -->
        <div>
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
      </form>
    </div>
</div>
  
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>