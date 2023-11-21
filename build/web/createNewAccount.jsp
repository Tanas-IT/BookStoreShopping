<%-- 
    Document   : createNewAccount
    Created on : Jun 27, 2023, 2:30:40 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="DispatchServlet" method="POST">
            Username* <input type="text" name="txtUserName" value="${param.txtUserName}" /> (6 - 20 chars) <br/>
            
            <font color="red">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            <c:if test="${not empty errors}">
                <c:if test="${not empty errors.usernameLengthErr}">
                    <c:out value="${errors.usernameLengthErr}"/><br/>
                </c:if>
            </c:if>
            </font>
            
            Password* <input type="password" name="txtPassword" value="" /> (8 - 30 chars) <br/>
            
            <font color="red">
            <c:if test="${not empty errors}">
                <c:if test="${not empty errors.passwordLengthErr}">
                    <c:out value="${errors.passwordLengthErr}"/><br/>
                </c:if>
            </c:if>
            </font>
            
            Confirm* <input type="password" name="txtConfirm" value="" /> <br/>
            
            <font color="red">
            <c:if test="${not empty errors}">
                <c:if test="${not empty errors.confirmNotMatched}">
                    <c:out value="${errors.confirmNotMatched}"/><br/>
                </c:if>
            </c:if>
            </font>
            
            Full Name <input type="text" name="txtFullname" value="${param.txtFullname}" /> (2 - 50 chars) <br/>
            
            <font color="red">
            <c:if test="${not empty errors}">
                <c:if test="${not empty errors.fullNameLengthErr}">
                    <c:out value="${errors.fullNameLengthErr}"/><br/>
                </c:if>
            </c:if>
            </font>
            
            <input  style="margin-bottom: 20px" type="submit" value="Create New Account" name="btAction" />
            <input style="margin-bottom: 20px" type="reset" value="Reset" /> <br/>
            
            <font color="red">
            <c:if test="${not empty errors}">
                <c:if test="${not empty errors.usernameIsExisted}">
                    <c:out value="${errors.usernameIsExisted}"/><br/>
                </c:if>
            </c:if>
            </font>
            
        </form>
    </body>
</html>
