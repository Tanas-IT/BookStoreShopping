<%-- 
    Document   : viewCart
    Created on : Jun 19, 2023, 1:37:00 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map"%>
<%@page import="tanhp.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:set var="cart" value="${sessionScope.CART}"/> 
        <c:set var="searchValue" value="${param.lastSearchValue}"/>
        <c:url var="urlRewriting" value="DispatchServlet">
                    <c:param name="btAction" value="Search Items"/>
                    <c:param name="txtItemsValue" value="${searchValue}"/>
       </c:url>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <h2>Your cart includes</h2>
                <form action="DispatchServlet">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Number of Remove</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${items}" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>
                                        <jsp:useBean id="productManager" class="tanhp.cart.CartObj" scope="session" />
                                        <c:set var="nameOfProduct" value="${productManager.getProductBySKU(item.key).name}" />
                                        ${nameOfProduct}
                                    </td>
                                    <td>${item.value}</td>
                                    <td>
                                        <input style="width: 177px; height: 17px" type="number" name="quantityOfRemove" max="${item.value}" min="1"/>
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkItem" value="${item.key}" />
                                        <input type="hidden" name="lastSearchItems" value="${searchValue}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3"> 
                                    <a href="${urlRewriting}">Add More Items to Your Cart</a> 
                                </td>
                                <td style="text-align: center" colspan="2">
                                    <input type="submit" value="Remove Selected Items" name="btAction" />
                                </td>
                            </tr>
                        </tbody>
                    </table> <br/>
                    <input type="submit" value="Check Out" name="btAction" />
                </form>
            </c:if>
                <c:if test="${empty items}">
                    <h2>
                        No item is existed!!!!!
                    </h2>
                    <a href="${urlRewriting}">Click here to return shopping page</a>
                </c:if>
        </c:if>
        <c:if test="${empty cart}">
                <h2>
                    No cart is existed!!!!!
                </h2>
        </c:if>
        <%--  <%
            //1. Cust goes his/her cart's place
            if (session != null) {
               //2. Cust takes his/her cart
               CartObj cart = (CartObj)session.getAttribute("CART");
               String searchValue = request.getParameter("lastSearchValue");
               if(cart != null) {
                   //3. Cus gets items
                   Map<String,Integer> items = cart.getItems();
                   if(items != null) {
                       //4. Cust picks all items up
                       String urlRewriting = "DispatchServlet"
                                              + "?btAction=Search Items"
                                              + "&txtItemsValue=" + searchValue;
                       %>
                       <h2>Your cart includes</h2>
                       <form action="DispatchServlet">
                       <table border="1">
                           <thead>
                               <tr>
                                   <th>No.</th>
                                   <th>Name</th>
                                   <th>Quantity</th>
                                   <th>Action</th>
                               </tr>
                           </thead>
                           <tbody>
                               <%
                                  int count = 0;
                                  for(String key: items.keySet()) {
                                      
                                      %>
                             <tr>
                                 <td>
                                     <%= ++count %>
                                 </td>
                                 <td>
                                     <%= cart.getProductBySKU(key).getName() %>
                                 </td>
                                  <td>
                                      <%= items.get(key) %>
                                  </td>
                                  <td>
                                      <input type="checkbox" name="chkItem" value="<%= key %>" />
                                      <input type="hidden" name="lastSearchItems" value="<%= searchValue %>" />
                                  </td>
                              </tr> 
                               <%
                                  } // traverse each item
                               %>
                                <tr>
                                    <td colspan="3"> 
                                        <a href="<%= urlRewriting %>">Add More Items to Your Cart</a> 
                                    </td>
                                    
                                    <td>
                                        <input type="submit" value="Remove Selected Items" name="btAction" />
                                    </td>
                               </tr>
                           </tbody>
                       </table> <br/>
                        <input type="submit" value="Check Out" name="btAction" />
                       </form> 
                      
                      
          <%
                    return;
                   } // end items have existed
               }// cart has existed
            } // end session has existed
          
            
          
          %> --%>
        
    </body>
</html>
