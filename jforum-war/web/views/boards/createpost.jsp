<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:master pageTitle="Save post" navIndex="-1">
    <jsp:attribute name="content">
        <div class="row center-block">
            <div class="container col-md-7 col-md-offset-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <span class="glyphicon glyphicon-plus"></span> Save post
                    </div>
                    
                    <div class="panel-body">
                        <form method="POST" action="${rootPath}/boards/post/save">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-tag"></span> Title</span>
                            <input type="text" placeholder="Post title/subject" name="post_title" />
                        </div>
                        
                        
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</tag:master>