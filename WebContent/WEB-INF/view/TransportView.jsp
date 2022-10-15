<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!--
<link href="${pageContext.request.contextPath}/bootstrap-5.2.2-dist/css/bootstrap.min.css" id="bootstrap-css" rel="stylesheet" />
-->
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
		<div class="form-group">
			<label>Origin: </label> 
			<input class="form-control" id="origin" oninput="displayPlace('origin')"
			placeholder="Enter a location" type="text" required/> 
			<div id="from_places" class="card">
			  <c:forEach items="${originList}" var="i">
			  	<p onclick="getPlace('origin','${i.origin}')">${i.destination}</p>
			  </c:forEach>
			</div>
			<br/>
		</div>
		<div class="form-group" >
			<label>Destination: </label> 
			<input class="form-control" id="destination" oninput="displayPlace('destination')"
			placeholder="Enter a location" type="text" required/> 
			<div id="to_places" class="card">
			  <c:forEach items="${destinationList}" var="i">
			  	<p onclick="getPlace('destination','${i.destination}')">${i.destination}</p>
			  </c:forEach>
			</div>
		</div>
        <div class="form-group">
			<label>Mass: </label> 
			<input class="form-control" id="mass" placeholder="Enter weight" type="number" required/> 
		</div>
        <input class="btn btn-primary" type="submit" value="Calculate" />
        <input id="mass" name="mass" value="${mass}" type="hidden" />
        <input id="duration" name="duration" value="${duration}" type="hidden" />
        <input id="distance" name="distance" value="${distance}" type="hidden" />
       
        <!-- doPost() -->
        <div>
          <table>
            <tr>
              <c:forEach items="${title}" var="i">
   	            <th>${i}</th>
              </c:forEach>
            </tr>
            <c:forEach items="${feeList}" var="i">
              <tr onclick="myFunction('${feeid}', '${feeprice }')">
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
<script>

	function displayPlace(value){
		var x = document.getElementById(value).value;
		const xhttp = new XMLHttpRequest();
        xhttp.onload = function() {
          // document.getElementById(place).innerHTML = this.responseText;
        }
        xhttp.open("POST","place");
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("value= "+ value + "&place=" + x);

	}
	function getPlace(nameId, value){
		document.getElementById(nameId).value = value;
	}
    function myFunction(x, y){
    	const xhttp = new XMLHttpRequest();
    	xhttp.onload = function() {
          // document.getElementById("demo").innerHTML = this.responseText;
        }
        xhttp.open("POST","transport");
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("feeid=" + x
        		+ "feeprice=" + y);
    }
</script>
</body>
</html>