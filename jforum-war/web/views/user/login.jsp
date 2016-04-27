<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:master pageTitle="Login" navIndex="3">
    <jsp:attribute name="content">
        <div class="row center-block">
            <div class="container col-md-4 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Login
                        </h3>
                    </div>
                    
                    <div class="panel-body">
                        <form id="login-form" action="" method="POST">
                            <%-- USERNAME FIELD --%>
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                <input type="text" class="form-control" placeholder="Username" name="login_username"/>
                            </div>
                            
                            <%-- PASSWORD FIELD --%>
                            <br>
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                <input type="password" class="form-control" placeholder="Password" name="login_password"/>
                            </div>
                            
                            <%-- CONTROLS --%>
                            <br>
                            <div class="btn-group">
                                <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-home"></span> Return home</button>
                                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-lock"></span> Login</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</tag:master>