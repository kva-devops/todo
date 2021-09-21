<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Приложение "ToDO" - Регистрация пользователя </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
    <script>
        function validate() {
            let elements = document.forms[0].elements;
            for (let i = 0; i < elements.length - 1; i++) {
                if ($(elements[i]).val() === '') {
                    alert($(elements[i]).attr('title'));
                    return false;
                }
            }
            return true;
        }
    </script>
<body>
<div class="container-fluid">
    <h1>Регистрация нового пользователя</h1>
    <p> Введите свои данные для регистрации нового пользователя</p>
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
        <div class="card" style="width: 100%">
            <div class="card-header">
                <a class="card-header-link" href="<%=request.getContextPath()%>/login.jsp">Авторизация</a> | Регистрация
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/reg" method="post">
                    <div class="form-group col-3 p-1">
                        <label>Имя</label>
                        <input type="text" class="form-control" name="name" title="Введите своё имя">
                    </div>
                    <div class="form-group col-3 p-1" >
                        <label>Почта</label>
                        <input type="text" class="form-control" name="email" title="Введите свой email">
                    </div>
                    <div class="form-group col-3 p-1">
                        <label>Пароль</label>
                        <input type="text" class="form-control" name="password" title="Введите пароль">
                    </div>
                    <div class="form-group col-3 p-1 mt-2">
                        <button type="submit" class="btn btn-primary" onclick="return validate()">Зарегистрироваться</button>
                    </div>
                </form>
            </div>
        </div>
</div>
</body>
</html>

