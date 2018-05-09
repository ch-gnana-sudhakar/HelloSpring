<%@page import="com.mycompany.myapp.domain.TxnApprovalStatus"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
</head>
<body>

<form method="GET" action="updateTxnStatus">
<% TxnApprovalStatus approvalStatus = (TxnApprovalStatus)request.getAttribute("txnInfo"); %>

<H3>Transaction Info</H3>

<table>
  <tr>
  <th>ReferenceNumber</th>
    <th>UserId</th>
    <th>Vendor</th>
    <th>Amount</th>
    <th>DateTime</th>
  </tr>
  <tr>
    <td><%=approvalStatus.getTxnReqId() %></td>
    <td><%=approvalStatus.getUserId() %></td>
    <td><%=approvalStatus.getVendor() %></td>
    <td><%=approvalStatus.getAmount() %></td>
    <td><%=approvalStatus.getTxnInitTime() %></td>
  </tr>
  </table>
  <br>
  <input type="hidden" name="userId" value="<%=approvalStatus.getUserId() %>" />
  <input type="hidden" name="txnNumber" value="<%=approvalStatus.getTxnReqId() %>" />
  
  <button type="submit" name="status" value="Approve">Approve</button>&nbsp;&nbsp;<button type="submit" name="status" value="Decline">Decline</button>
</form>
</body>
</html>