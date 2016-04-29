<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:master pageTitle="Home" navIndex="0">
    <jsp:attribute name="content">
        <div class="row center-block">
            <div class="container col-md-8 col-md-offset-2">
                <c:forEach items="${sections}" var="section">
                    <div class="panel panel-default board-panel">
                        <div class="panel-heading">
                            ${section.name}
                        </div>

                        <div class="panel-body">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Category</th>
                                        <th>No. Threads</th>
                                        <th>No. Posts</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                    <c:forEach items="${section.categorieses}" var="category">
                                        <tr>
                                            <td>
                                                <a href="${rootPath}/boards/category?catid=${category.id}">${category.name}</a>
                                                <br>${category.description}
                                            </td>
                                            
                                            <td>
                                                ${category.getNumThreads()} thread(s)
                                            </td>
                                            
                                            <td>
                                                ${category.getNumPosts()} post(s)
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </jsp:attribute>
</tag:master>