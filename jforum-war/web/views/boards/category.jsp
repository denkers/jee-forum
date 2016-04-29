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
                <div class="panel panel-default board-panel">
                    <div class="panel-heading">
                        <strong>${category.name}</strong>
                        <br>
                        ${category.description}
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</tag:master>