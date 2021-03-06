<%-- 
  KYLE RUSSELL
  AUT UNIVERSITY 2016
  https://github.com/denkers/jforum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tag:master pageTitle="Register" navIndex="2">
    <jsp:attribute name="content">
        <div class="row center-block">
            <div class="container col-md-7 col-md-offset-3">
                <div id="register-panel" class="panel panel-default">
                    <div class="panel-body">
                        <c:if test="${registerResult == null || !registerResult.key}">
                            <div class="page-header">
                                <h2>Account registration
                                    <br>
                                    <small>Fill in the form below to create your account</small>
                                </h2>
                            </div>
                        </c:if>
                        
                        <c:if test="${registerResult != null}">
                                <tag:result-alert resultStatus="${registerResult.key}" resultMessage="${registerResult.value}"></tag:result-alert>
                            
                            <c:if test="${registerResult.key}">
                                <div class="row center-block">
                                    <div class="col-md-4 col-md-offset-4">
                                        <div class="btn-group">
                                            <a class="btn btn-default" href="${rootPath}/home">
                                                <span class="glyphicon glyphicon-home"></span> Return home
                                            </a>
                                                <a href="${rootPath}/user/login" class="btn btn-primary">
                                                    <span class="glyphicon glyphicon-lock"></span> Login
                                                </a>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:if>

                        <c:if test="${registerResult == null || !registerResult.key}">
                            <div id="register-container">
                                <form id="register-form" action="" method="POST">
                                    <%-- USERNAME FIELD --%>
                                    <div class="row">
                                        <div class="col-md-2">
                                            <h4>Username</h4>
                                        </div>

                                        <div class="col-md-7">
                                            <input type="text" placeholder="Enter a 6-18 unique alphanumeric username" 
                                                   class="form-control" name="register_username" />
                                        </div>
                                    </div>

                                    <%-- PASSWORD FIELD --%>
                                    <div class="row">
                                        <div class="col-md-2">
                                            <h4>Password</h4>
                                        </div>

                                        <div class="col-md-7">
                                            <input type="password" placeholder="Enter a 4-16 alphanumeric password" 
                                                   class="form-control" name="register_password"/>
                                        </div>
                                    </div>

                                    <%-- REPEAT-PASSWORD FIELD --%>
                                    <div class="row">
                                        <div class="col-md-2">
                                            <h4>Repeat password</h4>
                                        </div>

                                        <div class="col-md-7">
                                            <input type="password" placeholder="Re-enter your password. Passwords must match" 
                                                   class="form-control" name="register_re_password"/>
                                        </div>
                                    </div>

                                    <%-- EMAIL FIELD --%>
                                    <div class="row">
                                        <div class="col-md-2">
                                            <h4>Email</h4>
                                        </div>

                                        <div class="col-md-7">
                                            <input type="text" placeholder="Enter a valid email address" 
                                                   class="form-control" name="register_email"/>
                                        </div>
                                    </div>

                                    <%-- CONTROLS --%>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-home"></span> Return home</button>
                                        <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> Register</button>
                                    </div>
                                </form>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</tag:master>