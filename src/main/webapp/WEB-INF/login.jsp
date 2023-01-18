<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="parts/header.jsp" %>
<form class="form-horizontal" action="login" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend>Login form</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="userlogin">Login</label>
            <div class="col-md-4">
                <input id="userlogin" name="login" type="text" placeholder=""
                       class="form-control input-md" required="">

            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="password">Password</label>
            <div class="col-md-4">
                <input id="password" name="password" type="password" placeholder=""
                       class="form-control input-md" required="">

            </div>
        </div>

        <!-- Button (Double) -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="login"></label>
            <div class="col-md-8">
                <button id="login" name="login"
                        class="btn btn-success">Login
                </button>

            </div>
        </div>

    </fieldset>
</form>

<%@include file="parts/footer.jsp" %>
