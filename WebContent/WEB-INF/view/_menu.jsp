<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page import="hm.GiaoHang.entity.UserAccount, hm.GiaoHang.utils.MyUtils" %>

<ul class="topnav">
  <li><a href="home">Home</a></li>
  <li><a href="fee">View fees</a></li>
  <%
  UserAccount loginedUser = MyUtils.getLoginedUser(request.getSession());
  %>
  <c:if test="${empty loginedUser}">
    <li class="right">
      <a href="login">Login</a>
    </li>
    <li class="right">
      <a href="signup">Sign Up</a>
    </li>
  </c:if>
  <c:if test="${not empty loginedUser.idCustomer }">
    <li class="dropdown">
      <a href="receipt">Receipt</a>
    </li>
    <li class="dropdown">
      <a href="transport">Transport</a>
    </li>
    <li class="dropdown">
      <a href="quene">Quene</a>
    </li>
  </c:if>
  <c:if test="${not empty loginedUser}">
    <li class="dropdown right">
      <a href="javascript:void(0)" class="dropbtn">Account</a>
      <div class="dropdown-content">
        <a href="info">Info</a>
        <a href="logout">Log out</a>
      </div>
    </li>
  </c:if>
</ul>
