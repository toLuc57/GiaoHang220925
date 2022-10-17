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
<title>Transport page!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/mystyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/table.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSSFiles/form.css">
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="row">
    <div class="card">
      <h3>Transport</h3>
      <form action="transport" method="post">
      	<p style="color:red" >${errorString}</p>
		<div class="form-group">
			<label>Origin: </label> 
			<input class="form-control" id="origin" name="origin" onkeyup="filterList('origin','listOrigin')" 
			value="${origin}" placeholder="Enter a location" type="text" required/> 
			<div id="from_places" class="card" style="height:110px; overflow: scroll;">
			  <c:forEach items="${listOrigin}" var="i">
			    <p style="cursor: pointer;" class="listOrigin"
			    onclick="getPlace('origin', '${i}')">${i}</p>
			  </c:forEach>
			</div>
			<br/>
		</div>
		<div class="form-group" >
			<label>Destination: </label> 
			<input class="form-control" id="destination" name="destination" onkeyup="filterList('destination','listDestination')"
			value="${destination}" placeholder="Enter a location" type="text" required/> 
			<div id="to_places" class="card" style="height:110px; overflow: scroll;">
			  <c:forEach items="${listDestination}" var="i">
			    <p style="cursor: pointer;" class="listDestination" 
			    onclick="getPlace('destination', '${i}')">${i}</p>
			  </c:forEach>
			</div>
		</div>
        <div class="form-group">
			<label>Mass: </label> 
			<input class="form-control" id="mass" name="mass" placeholder="Enter weight" 
			value="${mass}" type="number" required/> 
		</div>
        <input type="submit" value="Submit" />
        <!-- doPost() -->
        <div>
          <table>
            <tr>
              <th>Choose</th>
              <c:forEach items="${title}" var="i">
   	            <th>${i}</th>
              </c:forEach>
            </tr>
            <c:forEach items="${feeList}" var="i">
              <tr>
              	<td><a href="insertReceipt?placeid=${placeid}&feeid=${i.feeid}">Choose</a></td>
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
const origins = document.getElementsByClassName("listOrigin");
const destinations = document.getElementsByClassName("listDestination");

function filterList(myInput, myOutput) {
	  // Declare variables
	  var input, filter, list, a, i, txtValue;
	  
	  input = document.getElementById(myInput);
	  filter = input.value.toUpperCase();
	  
	  if(myInput === 'origin'){
		  list = origins;
	  } 
	  else if (myInput === 'destination'){
		  list = destinations;
	  }
	  // Loop through all list items, and hide those who don't match the search query
	  for (i = 0; i < list.length; i++) {
	    a = list[i];
	    txtValue = a.textContent || a.innerText;
	    // alert(txtValue.toUpperCase().indexOf(filter));
	    if (txtValue.toUpperCase().indexOf(filter) > -1) {
	      a.style.display = "";
	    } else {
	    	a.style.display = "none";
	    }
	  }
	}
	function getPlace(nameId, value){
		document.getElementById(nameId).value = value;
	}
</script>
</body>
</html>