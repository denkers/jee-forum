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

<!DOCTYPE html>
<html>
    <head>
        <title>JForum - ${pageTitle}</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="assets/css/main.css">
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
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#">Login</a></li>
                    <li><a href="#">Register</a></li>
                    <li><a href="#">About</a></li>
                </ul>
            </div>
        </nav>
        
        <div class="row center-block">
            <div class="container col-md-10 col-md-offset-1">
                <jsp:invoke fragment="content" />            
            </div>
        </div>
        
        <%-- FOOTER --%>
        <nav id="footer-nav" class="nav navbar-default navbar-fixed-bottom">
            <div class="row center-block">
                <div class="container col-md-10 col-md-offset-1">
                    <h4>Kyle Russell 2016</h4>
                </div>
            </div> 
        </nav>
    </body>
</html>