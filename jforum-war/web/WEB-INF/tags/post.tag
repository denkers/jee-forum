<%-- 
   KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="post_content" required="true" %>
<%@attribute name="post_user" required="true" %>
<%@attribute name="post_date" required="true" %>
<%@attribute name="post_user_picture" required="true" %>
<%@attribute name="original_post" required="true" %>

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
            <a href="#" class="btn btn-default"><span class="glyphicon glyphicon-user"></span> View profile</a>
            <a href="#" class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span> Edit post</a>
            <a href="#" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span> Remove post</a>
        </div>
            <div class="post-details">
                <span class="label label-${original_post? 'success' : 'primary'}">${original_post? 'Original post' : 'Reply'}</span> Posted ${post_date}
            </div>
        </div>
    </div>
</div>