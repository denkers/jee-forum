<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tag:master pageTitle="${thread.title}" navIndex="0">
    <jsp:attribute name="scripts">
        <script src="${rootPath}/assets/js/thread-utils.js"></script>
    </jsp:attribute>
    
    <jsp:attribute name="content">
        <div class="row center-block">
            <div class="container col-md-7 col-md-offset-2">
                <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${thread.dateCreated}" var="threadDate" />
                <h3><strong><span class="label label-primary thread-label">OPEN</span> [Thread] ${thread.title}</strong>
                    <br>
                    <small>Created by <a href="#">${thread.users.username}</a></small>
                    <br>
                    <small>${threadDate}</small>
                </h3>
                
                <%-- DISPLAY THREAD CONTENT POST --%>
                <tag:post post_content="${thread.content}" post_date="${threadDate}" original_post="true" post_id="-1"
                thread_id="${thread.id}" post_user="${thread.users.username}" post_user_picture="${thread.users.picture}"></tag:post>
                              
                <%-- DISPLAY POSTS --%>
                <c:forEach items="${thread.postses}" var="post">
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${post.postedDate}" var="postDate" />
                    <tag:post post_content="${post.getParsedContent()}" post_date="${postDate}" original_post="false" post_id="${post.id}"
                    thread_id="${thread.id}" post_user="${post.users.username}" post_user_picture="${post.users.picture}"></tag:post>
                </c:forEach>
                
                <br>
                <tag:postreply pcontent="" pid="" threadid="${thread.id}"></tag:postreply>
            </div>
        </div>
    </jsp:attribute>
</tag:master>