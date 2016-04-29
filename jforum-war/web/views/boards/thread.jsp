<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tag:master pageTitle="${thread.title}" navIndex="0">
    <jsp:attribute name="content">
        <div class="row center-block">
            <div class="container col-md-8 col-md-offset-2">
                <c:forEach items="${thread.postses}" var="post">
                    <tag:post post_content="${post.content}" post_date="${post.postedDate}" 
                              post_user="${post.users.username}" post_user_picture="${post.users.picture}"></tag:post>
                </c:forEach>
            </div>
        </div>
    </jsp:attribute>
</tag:master>