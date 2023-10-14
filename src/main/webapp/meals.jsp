<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>

<h2>Meals</h2>
<h3><a href="meals?action=add">Add Meal</h3>

<table cellpadding="7" border="2" width="100%">
  <tr>
    <th>Date</th>
    <th>Description</th>
    <th >Calories</th>
  	<th ></th>
  	<th ></th>
  </tr>
    <c:forEach var="meal" items="${mealTO}">
        <tr style="${meal.excess ? "color: red" : "color: green"}">
            <th><p><javatime:format value="${meal.dateTime}" style="MS"/></p></th>
            <th><p>${meal.description}</p></th>
            <th><p>${meal.calories}</p></th>
            <th><a href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">Edit</a></th>
            <th><a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">Delete</a></th>
        </tr>
    </c:forEach>
</table>
</body>
</html>