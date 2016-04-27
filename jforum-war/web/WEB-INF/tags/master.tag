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
        <jsp:invoke fragment="styles" />
        
        <script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <jsp:invoke fragment="scripts" />
    </head>
    
    <body>
        
    </body>
</html>