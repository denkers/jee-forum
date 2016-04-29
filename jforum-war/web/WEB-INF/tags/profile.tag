<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@attribute name="pageTitle" required="true" %>
<%@attribute name="profileNavIndex" required="true" %>
<%@attribute name="profileInfo" fragment="true" %>
<%@attribute name="profileMessages" fragment="true" %>
<%@attribute name="profileSettings" fragment="true" %>
<%@attribute name="profileFriends" fragment="true" %>
<%@attribute name="profileMenuNavIndex" required="true" %>
<%@attribute name="profileUserId" required="true" %>

<tag:master pageTitle="${pageTitle}" navIndex="${profileNavIndex}">
    <jsp:attribute name="content">
        <div class="row center-block">
            <div class="container col-md-9 col-md-offset-2">
                <div id="panel-container" class="row content">
                    <%-- PROFILE NAVIGATION --%>
                    <div id="profile-navigation" class="col-md-2 no-float">
                        <div class="list-group">
                            <a href="${rootPath}/user/profile/info?userid=${profileUserId}" class="list-group-item ${profileMenuNavIndex == 0? 'active' : ''}">
                                <span class="glyphicon glyphicon-info-sign"></span> Info
                            </a>
                            <a href="${rootPath}/user/profile/friends?userid=${profileUserId}" class="list-group-item ${profileMenuNavIndex == 1? 'active' : ''}">
                                <span class="glyphicon glyphicon-user"></span> Friends
                            </a>
                            <a href="${rootPath}/user/profile/messages?userid=${profileUserId}" class="list-group-item ${profileMenuNavIndex == 2? 'active' : ''}">
                                <span class="glyphicon glyphicon-envelope"></span> Messages
                            </a>
                            <a href="${rootPath}/user/profile/settings?userid=${profileUserId}" class="list-group-item ${profileMenuNavIndex == 3? 'active' : ''}">
                                <span class="glyphicon glyphicon-cog"></span> Settings
                            </a>
                        </div>
                    </div>
                    
                    <%-- PROFILE CONTENT --%>
                    <div id="profile-panel" class="col-md-10 no-float">
                        <jsp:invoke fragment="profileInfo" />
                        <jsp:invoke fragment="profileFriends" />
                        <jsp:invoke fragment="profileMessages" />
                        <jsp:invoke fragment="profileSettings" />
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</tag:master>