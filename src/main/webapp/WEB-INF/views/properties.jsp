<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, org.example.models.Property" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Property Search Results</title>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        display: flex;
        flex-direction: column;
        align-items: center;
        height: 100vh;
    }
    header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 100%;
        padding: 15px;
        background-color: #2c3e50;
        color: white;
    }
    .logo h1 {
        font-size: 22px;
        margin-left: 20px;
    }
    nav {
        margin-right: 20px;
    }
    nav ul {
        list-style: none;
        display: flex;
        gap: 10px;
    }
    nav ul li {
        display: inline;
    }
    nav ul li a {
        text-decoration: none;
        padding: 8px 12px;
        background: #3498db;
        color: white;
        border-radius: 5px;
        font-size: 14px;
        transition: 0.3s;
    }
    nav ul li a:hover {
        background: #2980b9;
    }
    .alert {
        background: #ffcc00;
        padding: 10px;
        border-radius: 5px;
        color: #333;
        font-weight: bold;
        margin: 10px 0;
        width: 60%;
        text-align: center;
    }
    table {
        width: 60%;
        border-collapse: collapse;
        margin-top: 20px;
        background: white;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 5px;
        overflow: hidden;
    }
    th, td {
        padding: 12px;
        border: 1px solid #ddd;
        text-align: left;
    }
    th {
        background: #3498db;
        color: white;
    }
    tr:nth-child(even) {
        background: #f9f9f9;
    }
    tr:hover {
        background: #f1f1f1;
    }
</style>
<body>
<header>
    <div class="logo">
        <h1>Rent House Online</h1>
    </div>
    <nav>
        <ul>
            <li><a href="index.jsp">Main</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Rent</a></li>
            <li><a href="#">Contacts</a></li>
            <li><a href="login">Login</a></li>
        </ul>
    </nav>
</header>
<h1>Search Results</h1>
<c:if test="${not empty message}">
    <div class="alert">
            ${message}
    </div>
</c:if>
<c:choose>
    <c:when test="${not empty properties}">
        <table>
            <tr>
                <th>ID</th>
                <th>Owner ID</th>
                <th>City</th>
                <th>Country</th>
                <th>Price</th>
                <th>Available From</th>
                <th>Available To</th>
            </tr>
            <c:forEach var="property" items="${properties}">
                <tr>
                    <td>${property.id}</td>
                    <td>${property.ownerId}</td>
                    <td>${property.city}</td>
                    <td>${property.country}</td>
                    <td>${property.price}</td>
                    <td>${property.availableFrom}</td>
                    <td>${property.availableTo}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <div class="alert">
            No properties found.
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
