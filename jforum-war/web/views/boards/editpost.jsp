<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:master pageTitle="Edit post" navIndex="-1">
    <jsp:attribute name="content">
        <div class="row center-block">
            <div class="container col-md-7 col-md-offset-3">
                <tag:postreply threadid="${post.threads.id}" pid="${post.id}" pcontent="${post.content}"></tag:postreply>
            </div>
        </div>
    </jsp:attribute>
</tag:master>