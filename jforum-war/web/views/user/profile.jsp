<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:master pageTitle="${isOwnProfile? 'Your' : profileUser.username} Profile" navIndex="4">
    <jsp:attribute name="content">
        <div class="row center-block">
            <div class="container col-md-9 col-md-offset-2">
                <div id="panel-container" class="row content">
                    <%-- PROFILE NAVIGATION --%>
                    <div id="profile-navigation" class="col-md-2 panel panel-default no-float">
                        <div class="list-group">
                            <a href="#" class="list-group-item active">Profile</a>
                            <a href="#" class="list-group-item">Friends</a>
                            <a href="#" class="list-group-item">Messages</a>
                            <a href="#" class="list-group-item">Settings</a>
                        </div>
                    </div>
                    
                    <%-- PROFILE CONTENT --%>
                    <div id="profile-panel" class="panel panel-default col-md-10 no-float">
                        <div class="panel-body">
                            <div id="profile-info" class="col-md-2">
                                <div id="profile-picture-container">
                                    <img src="${profileUser.picture}" id="profile-mini-thumb" />
                                    <h4>${profileUser.username}</h4>
                                </div>
                            </div>

                            <div id="profile-content" class="col-md-10">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</tag:master>