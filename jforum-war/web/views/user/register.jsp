<%-- 
    Document   : register
    Created on : 28/04/2016, 1:07:44 AM
    Author     : denker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:master pageTitle="Register" navIndex="3">
    <jsp:attribute name="content">
        <div class="row center-block">
            <div class="container col-md-7 col-md-offset-3">
                <div id="register-panel" class="panel panel-default">
                    <div class="panel-body">
                        <div class="page-header">
                            <h2>Account registration
                                <br>
                                <small>Fill in the form below to create your account</small>
                            </h2>
                        </div>
                        
                        <div id="register-container">
                            <form id="register-form" action="" method="POST">
                                <%-- USERNAME FIELD --%>
                                <div class="row">
                                    <div class="col-md-2">
                                        <h4>Username</h4>
                                    </div>
                                    
                                    <div class="col-md-7">
                                        <input type="text" placeholder="Enter a 6-18 unique alphanumeric username" class="form-control" name="register_username" />
                                    </div>
                                </div>
                                
                                <%-- PASSWORD FIELD --%>
                                <div class="row">
                                    <div class="col-md-2">
                                        <h4>Password</h4>
                                    </div>
                                    
                                    <div class="col-md-7">
                                        <input type="password" placeholder="Enter a 4-16 alphanumeric password" class="form-control" name="register_password"/>
                                    </div>
                                </div>
                                
                                <%-- REPEAT-PASSWORD FIELD --%>
                                <div class="row">
                                    <div class="col-md-2">
                                        <h4>Repeat password</h4>
                                    </div>
                                    
                                    <div class="col-md-7">
                                        <input type="password" placeholder="Re-enter your password. Passwords must match" class="form-control" name="register_re_password"/>
                                    </div>
                                </div>
                                
                                <%-- EMAIL FIELD --%>
                                <div class="row">
                                    <div class="col-md-2">
                                        <h4>Email</h4>
                                    </div>
                                    
                                    <div class="col-md-7">
                                        <input type="text" placeholder="Enter a valid email address" class="form-control" name="register_email"/>
                                    </div>
                                </div>
                                
                                <%-- CONTROLS --%>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-home"></span> Return home</button>
                                    <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-ok"></span> Register</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</tag:master>