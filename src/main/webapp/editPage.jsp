<%--
  Created by IntelliJ IDEA.
  User: usach
  Date: 07.10.2019
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Edit and Insert Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>Edit Page</h1>
    <form method="post" action='meals' name="frmAddMealTo">

        <div class="form-group row">
            <label for="inputId3" class="col-sm-2 col-form-label">Meal ID: </label>
            <div class="col-sm-10">
                <input class="form-control" name="mealId" id="inputId3" value="<c:out value="${meal.id}"/>">
            </div>
        </div>

        <div class="form-group row">
            <label for="inputDateTime3" class="col-sm-2 col-form-label">Date/Time: </label>
            <div class="col-sm-10">
                <input class="form-control" type="datetime-local" name="dateTime" value="${meal.dateTime}"
                       id="inputDateTime3">
            </div>
        </div>

        <div class="form-group row">
            <label for="inputDescription3" class="col-sm-2 col-form-label">Description: </label>
            <div class="col-sm-10">
                <input class="form-control" id="inputDescription3" name="description"
                       value="<c:out value="${meal.description}"/>">
            </div>
        </div>

        <div class="form-group row">
            <label for="inputCalories3" class="col-sm-2 col-form-label">Calories: </label>
            <div class="col-sm-10">
                <input class="form-control" id="inputCalories3" name="calories"
                       value="<c:out value="${meal.calories}"/>">
            </div>
        </div>

        <div class="form-group row">
            <div class="col-sm-10">
                <button type="submit" value="Submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </form>
</div>


<%--<form method="POST" action='meals' name="frmAddMealTo">--%>
<%--<javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd hh:mm" var="pd"/>--%>
<%--Meal ID : <input type="text" name="mealId" readonly="readonly" value="<c:out value="${meal.id}"/>"/><br/>--%>
<%--Date/Time : <input type="datetime-local" name="dateTime" value="${meal.dateTime}"><br/>--%>
<%--Description : <input type="text" name="description" value="<c:out value="${meal.description}"/>"/><br/>--%>
<%--Calories : <input type="text" name="calories" value="<c:out value="${meal.calories}"/>"/><br/>--%>
<%--<input type="submit" value="Submit"/>--%>
<%--</form>--%>
</body>
</html>
