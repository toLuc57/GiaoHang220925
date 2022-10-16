<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Info Page!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/mystyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/accountform.css">
</head>
<style>
.leftcolumn {   
  float: left;
  width: 50%;
}
.rightcolumn {
  float: left;
  width: 50%;
  background-color: #f1f1f1;
  padding-left: 20px;
}
@media screen and (max-width: 800px) {
  .leftcolumn, .rightcolumn {   
    width: 100%;
    padding: 0;
  }
}
</style>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<div class="modal">
  <form class="modal-content" action="signup" method="post">
    <div class="row container">
      <div class="leftcolumn">
        <h1>Will update avatar later</h1>
      </div>      
      <div class="rightcolumn">
        <h1>Will update later</h1>
      </div>
    </div>
    
  </form>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
