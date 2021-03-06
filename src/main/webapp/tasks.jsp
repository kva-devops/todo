<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Приложение "ToDO" - Текущие задачи </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/scripts.js"></script>
</head>
<body>
<div class="container-fluid">
    <h1>Текущие задачи</h1>
    <p> Добавляйте задания и следите за выполнением.</p>
</div>
<div class="container-fluid mt-3 mb-3">
    <div class="row border bg-light">
        <ul class="nav">
            <c:if test="${user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login">Войти</a>
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
    <form class="row g-3" action="<%=request.getContextPath()%>/task" method="post">
        <div class="mb-3">
            <label for="addTask" class="form-label">Описание нового задания</label>
            <textarea class="form-control" name="descriptionOfTask" id="addTask" rows="3" placeholder="Введите новое задание..." title="Введите текст задания"></textarea>
            <select class="form-select" name="cIds" id="cIds" multiple size="5">
                <c:forEach items="${allCategories}" var="categories">
                    <option value='<c:out value="${categories.id}"/>'>
                            <c:out value="${categories.name}" />
                    </option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-primary mt-3" onclick="return validate()">Добавить задание</button>
        </div>
    </form>
</div>
<div class="container-fluid" id="itemsList">
    <h2>Список заданий</h2>
    <form action="<%=request.getContextPath()%>/todo-close-task" method="post">
    <ul class="list-group" id="itemsListUl">

        <c:forEach items="${tasks}" var="task">
            <li class="list-group-item">
                <input class="form-check-input me-1" type="checkbox"  name="check" value='<c:out value="${task.id}"/>' aria-label="..." <c:if test="${task.done == true}">checked</c:if> >
                <i>Task: </i><c:out value="${task.description}"/> <i>Author: </i><c:out value="${user.name}"/>
                <i>Category: </i>
                <c:forEach items="${task.categories}" var="category">
                    <c:out value="${category.name}" />
                </c:forEach>
            </li>
        </c:forEach>
    </ul>
    <button type="submit" class="btn btn-primary mt-3">Закрыть выбранные задачи</button>
    </form>
</div>
<div class="container-fluid">
    <button type="submit" class="btn btn-primary mt-3" id="showButton" onclick="return showAllTask()">

    </button>
</div>
</body>
</html>
