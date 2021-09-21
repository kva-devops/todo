<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Приложение "ToDO" - Главная страница </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid">
    <h1>Приложение ToDo. Список дел.</h1>
    <p> Добавляйте задания и следите за выполнением.</p>
</div>
<div class="container-fluid mt-3 mb-3">
    <div class="row border bg-light">
        <ul class="nav">
            <c:if test="${user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Войти</a>
                </li>
            </c:if>
            <c:if test="${user != null}">
                <li class="nav-item">
                    <i class="nav-link"><c:out value="${user.name}"/></i>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/logout">Выйти</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<div class="container-fluid">
    <h2>Добавить новое задание</h2>
    <form class="row g-3">
        <div class="mb-3">
            <label for="addTask" class="form-label">Описание нового задания</label>
            <textarea class="form-control" id="addTask" rows="3" placeholder="Введите новое задание..." title="Авторизуйтесь или зареристрируйтесь"></textarea>
            <button type="submit" class="btn btn-primary" disabled>Добавить задание</button>
        </div>
    </form>
</div>
<div class="container-fluid" id="itemsList">
    <h2>Список заданий</h2>

    <ul class="list-group" id="itemsListUl">
    </ul>
    <button type="submit" class="btn btn-primary" disabled>Закрыть выбранные задачи</button>
</div>
</body>
</html>
