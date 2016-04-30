<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:master pageTitle="${category.name}" navIndex="0">
    <jsp:attribute name="content">
        <div class="row center-block">
            <div class="container col-md-8 col-md-offset-2">
                <div class="panel panel-default board-panel">
                    <div class="panel-heading">
                        <a ${sessionScope.activeUser == null? 'disabled' : ''} href="${rootPath}/boards/thread/save?catid=${category.id}" class="btn btn-default pull-right">
                            <span class="glyphicon glyphicon-plus"></span> New Thread
                        </a>
                        <strong>${category.name}</strong>
                        <br>
                        ${category.description}
                    </div>
                    
                    <div class="panel-body">
                        <c:choose>
                            <c:when test="${not empty category.threadses}">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Thread</th>
                                            <th>No. Posts</th>
                                            <th>Date created</th>
                                            <th>Most recent post</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach items="${category.threadses}" var="thread">
                                            <tr>
                                                <td>
                                                    <strong><a href="${rootPath}/boards/thread?catid=${category.id}&threadid=${thread.id}">${thread.title}</a><br></strong>
                                                    <small>Created by <a href="#">${thread.users.username}</a></small>
                                                </td>
                                                
                                                <td class="center-table-cell">
                                                    ${thread.getNumPosts()} posts(s)
                                                </td>
                                                
                                                <td class="center-table-cell">
                                                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${thread.dateCreated}" />
                                                </td>
                                                <td class="center-table-cell">
                                                    <c:choose>
                                                        <c:when test="${not empty thread.postses}">
                                                            By <a href="${rootPath}/user/profile/info?userid=${thread.postses[fn:length(thread.postses) - 1].users.username}">
                                                            ${thread.postses[fn:length(thread.postses) - 1].users.username}</a>
                                                            <br>
                                                            <small><fmt:formatDate type="both" dateStyle="short" timeStyle="short" 
                                                                            value="${thread.postses[fn:length(thread.postses) - 1].postedDate}" /></small>
                                                        </c:when>
                                                        
                                                        <c:otherwise>
                                                            None
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            
                            <c:otherwise>
                                <div class="no-thread-container">
                                    <h2><span class="glyphicon glyphicon-info-sign"></span> No threads found in this category</h2>
                                    <a ${sessionScope.activeUser == null? 'disabled' : ''} 
                                        href="${rootPath}/boards/thread/create?catid=${category.id}" class="btn btn-primary btn-lg">
                                        <span class="glyphicon glyphicon-plus"></span> Create new thread
                                    </a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</tag:master>