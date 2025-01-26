<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.models.Property" %>
<%@ page import="org.example.services.PropertyService" %>
<%@ page import="java.util.ArrayList" %>

<%
    PropertyService propertyService = new PropertyService();
    List<String> countries = propertyService.getAvailableCountries();
    String selectedCountry = request.getParameter("country");
    List<String> cities = selectedCountry != null ? propertyService.getAvailableCities(selectedCountry) : new ArrayList<>();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Property Search</title>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f5f5f5;
        margin: 0;
        padding: 0;
    }

    header {
        background-color: #007bff;
        color: white;
        text-align: center;
        padding: 15px;
        font-size: 24px;
    }

    .filter-section {
        text-align: center;
        padding: 20px;
    }

    .properties-grid {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        gap: 20px;
        padding: 20px;
    }

    .property-card {
        background: white;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        width: 300px;
        overflow: hidden;
        transition: transform 0.2s;
    }

    .property-card:hover {
        transform: scale(1.05);
    }

    .property-card img {
        width: 100%;
        height: 200px;
        object-fit: cover;
    }

    .property-info {
        padding: 15px;
        text-align: center;
    }

    .property-info h3 {
        margin: 10px 0;
    }

    .property-info p {
        color: #555;
    }

    button {
        background-color: #007bff;
        color: white;
        border: none;
        padding: 10px;
        width: 100%;
        cursor: pointer;
        font-size: 16px;
        border-radius: 5px;
    }

    button:hover {
        background-color: #0056b3;
    }
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
        width: 300px;
        text-align: center;
        margin-top: 20px;
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
</style>
<body>
<header>
    <div class="logo">
        <h1>Rent House Online</h1>
    </div>
    <nav>
        <ul>
            <li><a href="#">Main</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Rent</a></li>
            <li><a href="#">Contacts</a></li>
            <li><a href="login">Login</a></li>
        </ul>
    </nav>
</header>
<section class="filter-section">
    <form method="GET">
        <select id="countrySelect" name="country" onchange="this.form.submit()">
            <option value="">Оберіть країну</option>
            <% for (String country : countries) { %>
            <option value="<%= country %>" <%= country.equals(selectedCountry) ? "selected" : "" %>><%= country %></option>
            <% } %>
        </select>
        <select id="citySelect" name="city">
            <option value="">Оберіть місто</option>
            <% for (String city : cities) { %>
            <option value="<%= city %>"><%= city %></option>
            <% } %>
        </select>
        <button type="submit">Фільтрувати</button>
    </form>
</section>
<script>
    function applyFilters()
    {
        let country = document.getElementById("countrySelect").value;
        let city = document.getElementById("citySelect").value;
        window.location.href = "index.jsp?country=" + country + "&city=" + city;
    }
    function rentProperty(id)
    {
        window.location.href = "login.jsp";
    }
</script>
</body>
</html>
