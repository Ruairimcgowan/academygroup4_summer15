<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

 pageEncoding="ISO-8859-1"
 import = "cititradeweb.actions.*, cititradeweb.dal.*, cititradeweb.dataobjects.StockObject"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>

<body>

<h2> Stocks</h2>



<form action= "getTopQuotesServlet" method="get">

<table>

<tr><td></td><td><input type="submit" value="Get Stocks" /></td></tr>


</table>

</form>

</body>
</html>