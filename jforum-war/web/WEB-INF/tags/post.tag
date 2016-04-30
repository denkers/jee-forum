<%-- 
   KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="post_content" required="true" %>
<%@attribute name="post_user" required="true" %>
<%@attribute name="post_date" required="true" %>
<%@attribute name="post_user_picture" required="true" %>
<%@attribute name="original_post" required="true" %>
<%@attribute name="post_id" required="true" %>
<%@attribute name="thread_id" required="true" %>

<section class="post-section" id="${original_post? 'OP' : post_id}">
<div class="panel panel-default post-panel">
    <div class="panel-body">
        <div id="profile-info" class="col-md-2 poster-info">
           <div id="profile-picture-container">
               <img src="${post_user_picture}" id="profile-mini-thumb" />
               <a href="${rootPath}/user/profile/info?userid=${post_user}"><h4>${post_user}</h4></a>
           </div>
       </div>
           
        <div id="profile-content" class="col-md-10 post-content">
            ${post_content}
        </div>
    </div>
        
    <div class="panel-footer">
        <div class="row post-controls">
        <div class="btn-group pull-right">
            <a href="${rootPath}/user/profile/info?userid=${post_user}" class="btn btn-default">
                <span class="glyphicon glyphicon-user"></span> View profile
            </a>

            <c:if test="${sessionScope.activeUser != null}">
                <c:if test="${sessionScope.activeUser.username == post_user}">
                    <c:choose>
                        <c:when test="${!original_post}">
                            <a href="${rootPath}/boards/post/edit?threadid=${thread_id}&postid=${post_id}" class="btn btn-default">
                                <span class="glyphicon glyphicon-pencil"></span> Edit post
                            </a>
                        </c:when>

                        <c:otherwise>
                            <a href="${rootPath}/boards/thread/save?threadid=${thread_id}" class="btn btn-default">
                                <span class="glyphicon glyphicon-pencil"></span> Edit thread
                            </a>
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${!original_post}">
                        <a href="${rootPath}/boards/post/remove?postid=${post_id}" class="btn btn-default">
                            <span class="glyphicon glyphicon-remove"></span> Remove post
                        </a>
                    </c:if>
                </c:if>
                
                <button class="btn btn-default post-reply-button"><span class="glyphicon glyphicon-share-alt"></span> Reply</button>
            </c:if>
        </div>
            <div class="post-details">
                <span class="label label-${original_post? 'success' : 'primary'}">
                    ${original_post? 'Original post' : 'Reply'}</span> Posted ${post_date}
            </div>
        </div>
    </div>
</div>
</section>