<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.models.User" %>
<%
  User user = (User) session.getAttribute("user");
  if (user == null)
  {
    response.sendRedirect("index.jsp");
    return;
  }
%>
<html>
<head>
  <title>Home</title>
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
    .search-container {
      background: white;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      width: 400px;
      text-align: center;
      margin-top: 50px;
    }
    .search-container h1 {
      margin-bottom: 15px;
      font-size: 22px;
    }
    .search-container label {
      display: block;
      margin: 10px 0 5px;
      font-weight: bold;
    }
    .search-container input {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    .search-container button {
      width: 100%;
      padding: 10px;
      margin-top: 15px;
      border: none;
      background: #3498db;
      color: white;
      font-size: 16px;
      border-radius: 5px;
      cursor: pointer;
    }
    .search-container button:hover {
      background: #2980b9;
    }
    .user-info {
      margin-top: 20px;
      font-size: 18px;
      color: #333;
    }
  </style>
</head>
<body>
<header>
  <div class="logo">
    <h1>Rent House Online</h1>
  </div>
  <nav>
    <ul>
      <li><a href="index.jsp">Main</a></li>
      <li><a href="#">About</a></li>
      <li><a href="#">Contacts</a></li>
      <li><a href="logout">Logout</a></li>
      <li>Logged as: <%= user.getLogin() %></li>
    </ul>
  </nav>
</header>
<div class="search-container">
  <h1>Search Properties</h1>
  <form action="properties" method="get">
    <label for="city">City:</label>
    <input type="text" id="city" name="city" required>
    <label for="country">Country:</label>
    <input type="text" id="country" name="country" required>
    <button type="submit">Search</button>
  </form>
  <form action="properties" method="get">
    <button type="submit" style="margin-top: 10px;">Show All Properties</button>
  </form>
  <br>
  <a href="create-rental.jsp"><button>Create Rental</button></a>
</div>
</body>
</html>
