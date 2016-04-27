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
                    <a class="navbar-brand">jeeForum</a>
                </div>
                
                <ul class="nav navbar-nav navbar-right">
                    <li class="${navIndex == 0? "active" : ""}"><a href="#"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                    <li class="${navIndex == 1? "active" : ""}"><a href="#"><span class="glyphicon glyphicon-comment"></span> Forum</a></li>
                    <li class="${navIndex == 2? "active" : ""}"><a href="#"><span class="glyphicon glyphicon-lock"></span> Login</a></li>
                    <li class="${navIndex == 3? "active" : ""}"><a href="#"><span class="glyphicon glyphicon-plus"></span> Register</a></li>
                    <li class="${navIndex == 4? "active" : ""}"><a href="#"><span class="glyphicon glyphicon-info-sign"></span> About</a></li>
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