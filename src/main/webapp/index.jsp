<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Property Search</title>
</head>
<body>
<h1>Search Properties</h1>
<form action="properties" method="get">
    <label>City:</label>
    <input type="text" name="city" required>
    <br>
    <label>Country:</label>
    <input type="text" name="country" required>
    <br>
    <button type="submit">Search</button>
</form>
</body>
</html>
