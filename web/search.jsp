<%-- 
    Document   : search
    Created on : Jun 10, 2023, 1:24:29 PM
    Author     : ASUS
--%>

<%-- <%@page import="tanhp.registration.RegistrationDTO"%> 
<%@page import="java.util.List"%> --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USER.fullName}
        </font>
         <a href="LogoutServlet" style="margin: 20px">Logout</a>
        <form action="DispatchServlet" style="margin-top: 20px">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}"/>
            <br>
            <input type="submit" value="Search" name="btAction" />
        </form> <br/>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${result}" var="dto" varStatus="counter">
                            <form action="DispatchServlet">
                                <tr>
                                    <td>
                                        ${counter.count}
                                        <input type="hidden" name="lastSearchValue" value="${param.txtItemsValue}" />
                                    </td>
                                    <td>
                                        ${dto.username}
                                        <input type="hidden" name="txtUsername" value="${dto.username}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtPassword" value="${dto.password}" />
                                    </td>
                                    <td>
                                        ${dto.fullName}
                                    </td>
                                    <td>
                                        <!--${dto.role}-->
                                        <input type="checkbox" name="chkRole" value="ON" 
                                               <c:if test="${dto.role}">
                                                   checked="checked"
                                               </c:if>
                                               />
                                    </td>
                                    <td>
                                        <c:url var="deleteLink" value="DispatchServlet">
                                            <c:param name="btAction" value="delete"/>
                                            <c:param name="pk" value="${dto.username}"/>
                                            <c:param name="lastSearchValue" value="${searchValue}"/>
                                        </c:url>
                                        <c:if test="${sessionScope.USER.username ne dto.username}">
                                                <a href="${deleteLink}">Delete</a>
                                        </c:if>
                                    </td>
                                    <td>
                                        <input type="hidden" name="valueSearchUpdate" 
                                               value="${searchValue}" />
                                        <c:if test="${sessionScope.USER.username ne dto.username}">
                                                <input type="submit" value="Update" name="btAction"/>
                                        </c:if>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${empty result}">
                <h2>
                    No record is matched!!!
                </h2>
            </c:if>
        </c:if>
        <%--  <%
              Cookie[] cookies = request.getCookies();
              if(cookies != null) {
                  String username = "";
                  for(Cookie cookie: cookies) {
                         String temp = cookie.getName();
                         if(!temp.equals("JSESSIONID")) {
                             username = temp;
                         }
                  }
                  %>
                  <font color="red">
                      Welcome, <%= username %>
                  </font>
          <%
              }
          %>
          <a href="LogoutServlet" style="margin-left: 20px">Logout</a>
          <h1 style="color: blue">Welcome to DB Servlet JSP</h1>
          <form action="DispatchServlet">
              Search Value <input type="text" name="txtSearchValue" 
                                  value="<%if(request.getParameter("txtSearchValue") != null) {%><%= request.getParameter("txtSearchValue") %><%}%>"/>
              <br>
              <input type="submit" value="Search" name="btAction" />
          </form>
          <br>
          <%
              String searchValue = request.getParameter("txtSearchValue");
                  if(searchValue != null) {
                      List<RegistrationDTO> result = (List<RegistrationDTO>)  
                      // DTO là đang Serializable nên phải ép kiểu tường minh về dạng thường
                                                  request.getAttribute("SEARCH_RESULT");
                  if(result != null) { // search found at least 1 record
          %>
          <table border="1">
              <thead>
                  <tr>
                      <th>No.</th>
                      <th>Username</th>
                      <th>Password</th>
                      <th>FullName</th>
                      <th>Role</th>
                      <th>Delete</th>
                      <th>Update</th>
                  </tr>
              </thead>
              <tbody>
                  <%
                      int count = 0;
                      for(RegistrationDTO dto : result) {
                          String urlRewriting = "DispatchServlet"
                                  + "?btAction=delete"
                                  + "&pk=" + dto.getUsername()
                                  + "&lastSearchValue=" + searchValue;
                          // kiem tra dau &, ?, = 
                  %> 
              <form action="DispatchServlet">
              
                  <tr>
                      <td>
                          <%= ++count %>
                      </td>
                      <td>
                          <%= dto.getUsername() %>
                          <input type="hidden" name="txtUsername" 
                                              value="<%= dto.getUsername() %>" />
                      </td>
                      <td>
                           <%= dto.getPassword() %> 
                          <input type="text" name="txtPassword" 
                                              value="<%= dto.getPassword() %>" />
                      </td>
                      <td>
                          <%= dto.getFullName() %> 
                      </td>
                      <td>
                          <%= dto.isRole()%>
                          <input type="checkbox" name="chkRole" value="ADMIN" 
                                  <%
                                      if (dto.isRole()) {
                                          %>
                                       checked="checked"   
                                 <%
                                      }
                                  %>
                                 /> 
                      </td>
                      <td>
                          <a href="<%= urlRewriting %>">Delete</a>
                      </td>
                      <td>
                          <input type="hidden" name="lastSearchValue" 
                                               value="<%= searchValue %>" />
                          <input type="submit" name="btAction" value="Update">
                      </td>
                  </tr>
                  
              </form>
                  <% 
                      } // end process each dto
                  %>
              </tbody>
          </table>
          <% } else { %>
                  <h2>No record is matched!!!</h2>
          <% 
                  }
              } // end user access directly or first access this page 
          %> 
        --%>

    </body>
</html>
