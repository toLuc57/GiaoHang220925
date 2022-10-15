<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login page!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/mystyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/form.css">
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="row">
  <div class="leftcolumn">
    <div class="card">
      <div class="fakeimg" style="height: 300px;">Image</div>
    </div>
  </div>
  <div class="rightcolumn">
    <div class="card">
      <h3>Login</h3>
      <form action="login" method="post">
      	<p style="color:red" >${errorString}</p>
        <label for="fname">Username</label>
        <input type="text" id="username" name="username" 
        placeholder="Your username.." value="${user.username}">

        <label for="yourpassword">Password</label>
        <input type="password" id="yourpassword" name="yourpassword" 
        placeholder="Your password.." value="${user.password}">
<!--     	
    	<label class="container">Remember me
          <input type="checkbox" id="rememberMe" name="rememberMe"
    	    value="Y">
          <span class="checkmark"></span>
        </label> 
-->
        <input type="submit" value="Login">
      </form>
    </div>
  </div>
</div>
</body>
</html>