<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:master pageTitle="Home" navIndex="-1">
    <jsp:attribute name="content">
        <div class="row center-block">
            <div class="container col-md-9 col-md-offset-2">
                <div class="jumbotron">
                    <h1><span class="glyphicon glyphicon-remove-circle"></span> An error has occurred</h1>
                    <h3>The page you requested could not be displayed.</h3>
                    <h3>You may be seeing this because: </h3>
                    <ul>
                        <li><h4>You do not have permission to view this page</h4></li>
                        <li><h4>The page was not found</h4></li>
                        <li><h4>The server is on fire</h4></li>
                    </ul>
                    
                    <br>
                    <a href="${rootPath}/home" class="btn btn-lg btn-primary"><span class="glyphicon glyphicon-home"></span> Return to Home</a>
                </div>
            </div>
        </div>
    </jsp:attribute>
</tag:master>