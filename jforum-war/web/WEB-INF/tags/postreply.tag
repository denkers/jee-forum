<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="pcontent" required="true" %>
<%@attribute name="pid" required="true" %>
<%@attribute name="threadid" required="true" %>

<div class="panel panel-default">
    <div class="panel-heading">
        Quick reply
    </div>

    <div class="panel-body">
        <form method="POST" action="${rootPath}/boards/post/save">
            <h4><span class="glyphicon glyphicon-pencil"></span> Reply message</h4>
            <input type="hidden" name="post_id" value="${pid}"/>
            <input type="hidden" name="thread_id"value="${threadid}"/>
            <textarea class="form-control" name="post_content" placeholder="Enter your message">${pcontent}</textarea>
            <small><span class="glyphicon glyphicon-info-sign"></span> 1500 characters allowed</small>
            
            
            <br><br>
            <button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-ok"></span> Save reply</button>
        </form>

    </div>
</div>