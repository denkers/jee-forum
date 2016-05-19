<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:master pageTitle="Save thread" navIndex="-1">
    <jsp:attribute name="content">
        <div class="row center-block">
            <div class="container col-md-7 col-md-offset-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <span class="glyphicon glyphicon-plus"></span> Save thread
                    </div>
                    
                    <div class="panel-body">
                        <div class="page-header">
                            <h3>Save thread</h3>
                        </div>
                        
                        <c:if test="${saveThreadResult != null}">
                            <tag:result-alert resultStatus="${saveThreadResult.key}" resultMessage="${saveThreadResult.value}"></tag:result-alert>
                        </c:if>
                        
                        <form method="POST" action="${rootPath}/boards/thread/save">
                            <input type="hidden" name="thread_edit_id" value="${thread.id}" />
                            <input type="hidden" name="thread_cat_id" value="${thread != null? thread.categories.id : catid}"/>
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-tag"></span> Thread title</span>
                                <input class="form-control" type="text" placeholder="Thread title/subject" name="thread_title" value="${thread.title}" />
                            </div>

                            <br>
                            <h4><strong><span class="glyphicon glyphicon-pencil"></span> Content</strong></h4>
                            <textarea class="form-control lg-textarea" name="thread_content">${thread.content}</textarea>

                            <br>
                            <div class="row center-table-cell">
                                <div class="btn-group">
                                    <a href="${rootPath}/boards/category?catid=${catid}" class="btn btn-default">
                                        <span class="glyphicon glyphicon-share-alt"></span> Back to category
                                    </a>

                                    <button type="submit" class="btn btn-primary">
                                        <span class="glyphicon glyphicon-ok"></span> Save
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</tag:master>