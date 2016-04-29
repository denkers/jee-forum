<%-- 
   KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="post_content" required="true" %>
<%@attribute name="post_user" required="true" %>
<%@attribute name="post_date" required="true" %>
<%@attribute name="post_user_picture" required="true" %>

<div class="panel panel-default post-panel">
    <div class="panel-heading">
    </div>
    
    <div class="panel-body">
        <div id="profile-info" class="col-md-2">
           <div id="profile-picture-container">
               <img src="${post_user_picture}" id="profile-mini-thumb" />
               <a href="#"><h4>${post_user}</h4></a>
           </div>
       </div>
           
        <div id="profile-content" class="col-md-10">
            ${post_content}
        </div>
    </div>
</div>