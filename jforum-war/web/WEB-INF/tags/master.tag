<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@tag description="Master content tag" pageEncoding="UTF-8"%>
<%@attribute name="pageTitle" required="true" %>
<%@attribute name="content" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>
<%@attribute name="styles" fragment="true" %>
<%@attribute name="navIndex" required="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

     <%
        String rootPath =   request.getContextPath();
        request.setAttribute("rootPath", rootPath);
    %>

<!DOCTYPE html>
<html>
    <head>
        <title>JForum - ${pageTitle}</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main.css">
        <jsp:invoke fragment="styles" />
        
        <script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <jsp:invoke fragment="scripts" />
    </head>
    
    <body>
        <%-- HEAD NAVIGATION --%>
        <nav id="head-nav" class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand">
                        <strong><span class="glyphicon glyphicon-comment"></span> jForum
                        </strong>
                            <br>
                            <p><small>A neat bulletin board</small></p>
                    </a>
                </div>
                
                <ul class="nav navbar-nav navbar-right">
                    <li class="${navIndex == 0? "active" : ""}"><a href="<%=request.getContextPath()%>/home"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                    
                    <c:choose>
                        <c:when test="${sessionScope.activeUser == null}">
                            <li class="${navIndex == 1? "active" : ""}">
                                <a href="<%=request.getContextPath()%>/user/login"><span class="glyphicon glyphicon-lock"></span> Login</a>
                            </li>
                            <li class="${navIndex == 2? "active" : ""}">
                                <a href="<%=request.getContextPath()%>/user/register"><span class="glyphicon glyphicon-plus"></span> Register</a>
                            </li>
                        </c:when>
                            
                        <c:otherwise>
                            <li class="${navIndex == 4? "active" : ""}">
                                <a href="${rootPath}/user/profile/info?userid=${sessionScope.activeUser.username}">
                                    <span class="glyphicon glyphicon-user"></span> Profile
                                </a>
                            </li>
                            
                              <li class="${navIndex == 5? "active" : ""}">
                                <a href="${rootPath}/user/profile/friends?userid=${sessionScope.activeUser.username}">
                                    <span class="glyphicon glyphicon-heart"></span> Friends
                                </a>
                            </li>
                            
                              <li class="${navIndex == 6? "active" : ""}">
                                <a href="${rootPath}/user/profile/settings?userid=${sessionScope.activeUser.username}">
                                    <span class="glyphicon glyphicon-cog"></span> Settings
                                </a>
                            </li>
                            
                            <li class="${navIndex == 7? "active" : ""}">
                                <a href="${rootPath}/user/logout"><span class="glyphicon glyphicon-share-alt"></span> Logout</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </nav>
        
        <%-- CONTENT --%>
        <div class="row center-block">
            <div class="container col-md-10 col-md-offset-1">
                <jsp:invoke fragment="content" />            
            </div>
        </div>
        
        <%-- FOOTER --%>
        <nav id="footer-nav" class="nav navbar-default navbar-fixed-bottom">
            <div class="row center-block">
                <div class="container col-md-10 col-md-offset-1">
                    <h4><span class="glyphicon glyphicon-copyright-mark"></span> Kyle Russell 2016</h4>
                </div>
            </div> 
        </nav>
    </body>
</html>