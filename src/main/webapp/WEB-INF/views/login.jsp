<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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
        .form-container {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
            margin-top: 50px;
        }
        .form-container h2 {
            margin-bottom: 15px;
            font-size: 22px;
        }
        .form-container label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }
        .form-container input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-container button {
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
        .form-container button:hover {
            background: #2980b9;
        }
        .alert {
            background: #ffcc00;
            padding: 10px;
            border-radius: 5px;
            color: #333;
            font-weight: bold;
            margin: 10px 0;
            text-align: center;
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
            <li><a href="#">Rent</a></li>
            <li><a href="#">Contacts</a></li>
            <li><a href="login">Login</a></li>
        </ul>
    </nav>
</header>

<div class="form-container">
    <h2>Login</h2>
    <% if (request.getAttribute("error") != null) { %>
    <div class="alert"><%= request.getAttribute("error") %></div>
    <% } %>
    <form action="login" method="post">
        <label>Login: <input type="text" name="login" required></label>
        <label>Password: <input type="password" name="password" required></label>
        <button type="submit">Login</button>
    </form>
    <p>Don't have an account? <a href="register">Register</a></p>
</div>

</body>
</html>
