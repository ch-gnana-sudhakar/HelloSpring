<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="refresh" content="10;URL=logout">

<title>Insert title here</title>
</head>
<body>

<H2><font color="green"> Dear <%=request.getAttribute("user")%>,</font></H2>

<% if(request.getAttribute("message") != null && "Approve".equals(request.getAttribute("message"))) { %>

&nbsp;&nbsp;&nbsp;<H2><font color="green">Transaction Successful with Transaction Id</font> <font color="grey"> <%=request.getAttribute("txNo")%></font></H2>

<% } else { %>
&nbsp;&nbsp;&nbsp;<H2><font color="red">Transaction Declined with Transaction Id</font> <font color="grey"> <%=request.getAttribute("txNo")%></font></H2>
<% }%>
</body>
</html>