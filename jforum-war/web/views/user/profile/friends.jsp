<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:profile profileNavIndex="4" pageTitle="${isOwnProfile? 'Your' : profileUser.username} Friends" profileMenuNavIndex="1" 
             profileUserId="${profileUser.username}" isOwnProfile="${isOwnProfile}">
    <jsp:attribute name="profileFriends">
        
        <div class="page-header">
            <h3>${isOwnProfile? 'Your' : profileUser.username.concat('\'s')} friends
                <br>
                <small>User has ${fn:length(friendList)} friend(s)</small>
            </h3>
        </div>
         
        <c:forEach items="${friendList}" var="friendship">
            <div class="row">
                <div class="col-md-2">
                    <div class="panel panel-default friend_holder">
                        
                        <div class="panel-body" style='background-image: url("${friendship.getFriend(profileUser).picture}")'>
                        </div>
                        
                        <div class="panel-footer">
                            <a class="plain-link" href="${rootPath}/user/profile/info?userid=${friendship.getFriend(profileUser).username}">
                                ${friendship.getFriend(profileUser).username}
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
                
    </jsp:attribute>
</tag:profile>
