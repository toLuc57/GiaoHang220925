<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Signup Page!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/mystyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/accountform.css">
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<div class="modal">
  <form class="modal-content" action="signup" method="post">
    <div class="container">
      <h1>Sign Up</h1>
      <c:forEach items="${list}" var="i">
        <input type="hidden" class="listUserName" value="${i}">
      </c:forEach>
      <p>Please fill in this form to create an account.</p>
      <p style="color:red;">${errorMessage}</p>
      
      <label for="name"><b>Full name</b></label>
      <input type="text" placeholder="Enter full name" name="name">

      <label for="telephone"><b>Telephone</b></label>
      <input type="text" placeholder="Enter telephone" name="telephone" required>
      
      <label for="address"><b>Address</b></label>
      <input type="text" placeholder="Enter address" name="address">
      
      <label for="username"><b>Username</b></label>
      <span style="color:red" id="noticeExists"></span>
      <input type="text" placeholder="Enter Username" id="username"
      name="username" oninput="checkExists()" required>
      
      <label for="psw"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" id="psw" 
      name="psw" oninput="checkPassword()" required>

      <label for="psw-repeat"><b>Repeat Password</b></label>
      <span style="color:red" id="wrongPassword"></span>  
      <input type="password" placeholder="Repeat Password" id="pswRepeat"
      name="pswRepeat" oninput="checkPassword()" required>
       
      <div class="clearfix">
        <a href="home"><button type="button" class="cancelbtn">Cancel</button></a>
        <button type="submit" class="signupbtn">Sign Up</button>
      </div>
    </div>
  </form>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
<script>
const letters = new Set();
var x = document.getElementsByClassName("listUserName");
for(var i = 0; i < x.length; ++i)
{
  letters.add(x[i].value);
}
function checkExists(){
	  var username = document.getElementById("username").value;
	  if(letters.has(username))
	  {
	    document.getElementById("noticeExists").innerHTML = "(Account already exists)";
	  } 
	  else {
	    document.getElementById("noticeExists").innerHTML = "";
	  }
}
function checkPassword(){
	  var psw = document.getElementById("psw").value;
	  var pswRepeat = document.getElementById("pswRepeat").value;
	  if(pswRepeat === psw)
	  {
	    document.getElementById("wrongPassword").innerHTML = "";
	  } 
	  else {
	    document.getElementById("wrongPassword").innerHTML = "(Wrong password)";
	  }
}
</script>
</body>
</html>
