<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:profile profileNavIndex="4" pageTitle="${isOwnProfile? 'Your' : profileUser.username} Friends" profileMenuNavIndex="1" profileUserId="${profileUser.username}">
    <jsp:attribute name="profileFriends">
        <div class="page-header">
            <h3>${isOwnProfile? 'Your' : profileUser.username.concat('\'s')} Friends
                <br>
                <small>User has friend(s)</small>
            </h3>
        </div>
    </jsp:attribute>
</tag:profile>
