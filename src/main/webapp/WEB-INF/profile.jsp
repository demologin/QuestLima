<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="parts/header.jsp" %>
<div class="container">
    <jsp:useBean id="user" scope="session"
                 type="com.javarush.quest.shubchynskyi.questlima.entity.user.User"/>

    <div class="px-4 py-5 my-5 text-center">
        <p class="lead mb-4">
        <div class="form-group">
            <img src="images/${user.image}" width="250" alt="${user.image}">
        </div>
        <h5 class="display-5 fw-bold">User login: ${user.login}</h5>
        <h5 class="display-5 fw-bold">User role: ${user.role}</h5>
        <div class="col-lg-6 mx-auto">
            <form class="form-horizontal" action="profile" method="post" enctype="multipart/form-data">
                <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                    <input type="hidden" name="id" value="${user.id}">
                    <button type="submit" name="user" class="btn btn-primary btn-lg px-4 gap-3">Редактировать</button>
<%--                    <button type="submit" name="logout" class="btn btn-outline-secondary btn-lg px-4">Выход</button>--%>
                </div>
            </form>
        </div>
    </div>
</div>
<c:import url="parts/footer.jsp"/>
