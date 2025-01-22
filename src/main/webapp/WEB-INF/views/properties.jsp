<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, org.example.models.Property" %>
<jsp:useBean id="properties" scope="request" type="java.util.List"/>
<html>
<head>
    <title>Property Search Results</title>
</head>
<body>
<h1>Search Results</h1>
<table border="1">
    <tr>
        <th>City</th>
        <th>Country</th>
        <th>Price</th>
    </tr>
    <%
        for (Property property : (List<Property>) properties) {
    %>
    <tr>
        <td><%= property.getCity() %></td>
        <td><%= property.getCountry() %></td>
        <td><%= property.getPrice() %></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
