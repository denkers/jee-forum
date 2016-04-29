<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:profile profileNavIndex="4" pageTitle="Account settings" profileMenuNavIndex="3" profileUserId="${sessionScope.activeUser.username}">
    <jsp:attribute name="profileInfo">
        <div class="page-header">
            <h3>Account settings
                <br>
                <small>Manage your account details</small>
            </h3>
        </div>
        
        <div class="row">
            <div class="col-md-8">
                <div class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span> Email</span>
                    <input type="text" name="settings_email" class="form-control" placeholder="Email address" value="${sessionScope.activeUser.email}"/>
                </div>

                <br>
                <div class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-picture"></span> Picture</span>
                    <input type="text" name="settings_picture" class="form-control" placeholder="Display picture" value="${sessionScope.activeUser.picture}"/>
                </div>

                <br>
                <div class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span> Password</span>
                    <input type="password" name="settings_password" class="form-control" placeholder="Password" value="${sessionScope.activeUser.password}"/>
                </div>
                
                <br>
                <div class="btn-group">
                    <a class="btn btn-default" href="${rootPath}/home"><span class="glyphicon glyphicon-home"></span> Return to home</a>
                    <button type="submit" class="btn btn-primary"><span class=" glyphicon glyphicon-ok"></span> Save settings</button>
                </div>
            </div>
        </div>
    </jsp:attribute>
</tag:profile>