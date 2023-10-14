<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<form method="POST" action='meals' name="addMeal">
    <table align="left" cellspacing ="5" border="0" width="50%" >
      <tr align="left" bgcolor="#d7d9f2">
        <input type="hidden" name="id" value="<c:out value="${mealTO.id}" />"/>
        <th>Date</th>
        <th><input name="dateTime" type="datetime-local"
        value="<c:out value="${mealTO.dateTime}" />"/></th>
      </tr>
      <tr align="left" bgcolor="#d7d9f2">
        <th>Description</th>
        <th><input name="description" type="text"
         value="<c:out value="${mealTO.description}" />"/></th>
      </tr>
      <tr align="left" bgcolor="#d7d9f2">
        <th>Calories</th>
        <th><input name="calories" type="number"
        value="<c:out value="${mealTO.calories}" />"/></th>
      </tr>
      <th align="left">
        <input type="submit" value="Save" />
        <input type="button" onclick="window.history.back()" value="Cancel"/>
      </th>
    </table>
</from>

</body>
</html>