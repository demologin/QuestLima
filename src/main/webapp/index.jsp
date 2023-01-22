<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
          crossorigin="anonymous"
    >
</head>
<body>
<header class="bg-light d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
    <a href="${pageContext.request.contextPath}/"
       class="d-flex align-items-left col-md-1 mb-2 mb-md-0 text-dark text-decoration-none">
        <img src="${pageContext.request.contextPath}/ico/home.jpg" width="48" alt="Home page">
    </a>

    <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <%-- guest --%>
        <li><a href="${pageContext.request.contextPath}/" class="nav-link px-2 link-secondary">Home</a></li>
        <%-- user --%>
        <li><a href="#" class="nav-link px-2 link-dark disabled">Играть</a></li>
        <li><a href="#" class="nav-link px-2 link-dark disabled">Статистика</a></li>
        <%-- admin--%>
        <li><a href="create-quest" class="nav-link px-2">Создать квест</a></li>
        <li><a href="#" class="nav-link px-2 disabled">Редактировать квест</a></li>
    </ul>

</header>
