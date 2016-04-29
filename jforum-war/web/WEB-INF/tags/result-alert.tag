<%-- 
    Document   : result-alert
    Created on : 29/04/2016, 5:09:21 PM
    Author     : denker
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="resultStatus" required="true"%>
<%@attribute name="resultMessage" required="true"%>

<div class="alert ${resultStatus? "alert-success" : "alert-danger"} alert-dismissable fade in">
    <button class="close" data-dismiss="alert">&times;</button>
    <strong>
        <span class="${resultStatus?  "glyphicon glyphicon-ok-circle" : "glyphicon glyphicon-remove-circle"}"></span> 
        ${resultStatus? "Success!" : "Error!"}
    </strong>
    <p>${resultMessage}</p>
</div>