<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:profile profileNavIndex="4" pageTitle="${isOwnProfile? 'Your' : profileUser.username} Profile" profileMenuNavIndex="0">
    <jsp:attribute name="profileInfo">
        <div id="profile-info" class="col-md-2">
           <div id="profile-picture-container">
               <img src="${profileUser.picture}" id="profile-mini-thumb" />
               <h4>${profileUser.username}</h4>
           </div>
       </div>

       <div id="profile-content" class="col-md-10">
           <c:if test="${!isOwnProfile}">
               <div class="btn-group pull-right">
                   <a href="#" class="btn btn-default"><span class="glyphicon glyphicon-comment"></span> Message</a>
                   <a href="#" class="btn btn-default"><span class="glyphicon glyphicon-user"></span> Add friend</a>
               </div>
               <br>
           </c:if>
           
           <div class="col-md-4">
               <h3><strong><span class="glyphicon glyphicon-envelope"></span> Email:</strong></h3>
               <h3><strong><span class="glyphicon glyphicon-time"></span> Member since:</strong></h3>
           </div>
           
           <div class="col-md-8">
               <h3>${profileUser.email}</h3>
               <h3>${profileUser.getFormattedRegisterDate()}</h3>
           </div>
       </div>
    </jsp:attribute>
</tag:profile>