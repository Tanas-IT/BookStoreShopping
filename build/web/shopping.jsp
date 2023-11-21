<%-- 
    Document   : shopping
    Created on : Jun 19, 2023, 4:02:40 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map"%>
<%@page import="tanhp.cart.CartObj"%>
<%@page import="java.util.List"%>
<%@page import="tanhp.product.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Java Book Store</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtItemsValue" 
                                value="${param.txtItemsValue}"/>
            <input type="submit" value="Search Items" name="btAction" />
        </form>
        <br>
        <form action="DispatchServlet">
            <c:set var="searchValue" value="${param.txtItemsValue}"/>
            <c:if test="${not empty searchValue}">
                <c:set var="result" value="${requestScope.listProduct}"/>
                <c:if test="${not empty result}">
                    <table border="1"  style="margin-bottom: 20px" >
                        <thead>
                            <tr>
                                <th>Add</th>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="product" items="${result}" varStatus="counter">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="chkItem" 
                                               value="${product.sku}"
                                               <c:if test="${product.status eq false}">
                                                   disabled
                                               </c:if>
                                               <c:if test="${sessionScope.CART != null}">
                                                   <c:set var="items" value="${sessionScope.CART.items}"/>
                                                   <c:if test="${not empty items}">
                                                       <c:forEach var="item" items="${items}">
                                                           <c:if test="${item.key eq product.sku}">
                                                               checked ="checked"
                                                           </c:if>
                                                       </c:forEach>
                                                   </c:if>   
                                               </c:if>
                                               />
                                        <input type="hidden" name="lastSearchValue" value="${param.txtItemsValue}" />
                                    </td>
                                    <td>${counter.count}</td>
                                    <td>${product.name}</td>
                                    <td>
                                        <input type="number" name="numberquantity" value="1" />
                                        <input type="hidden" name="IdProductCheck" value="${product.sku}"/>
                                    </td>
                                    <td>${product.price}</td>
                                    <td>
                                        <c:if test="${product.status eq true}">
                                            Stocking
                                        </c:if>
                                        <c:if test="${product.status eq false}">
                                            Sold out
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        <tr>
                            <td colspan="4">
                                <input style="position: relative;left: 30%" type="submit" value="Add Book to Your Cart" name="btAction" />
                            </td>
                            <td colspan="2">
                                <input type="submit" value="View Your Cart" name="btAction" />
                            </td>
                        </tr>
                     </tbody>
                   </table>
                </c:if>
                <c:if test="${empty result}">
                        <h2>No record is matched!!!</h2>
                </c:if>
            </c:if>
            <a href="login.html">Back to the Login page</a>
           
            <%---
            <%
                String searchValue = request.getParameter("txtItemsValue");
                if (searchValue != null) {
                    List<ProductDTO> result = (List<ProductDTO>) // DTO là đang Serializable nên phải ép kiểu tường minh về dạng thường
                            request.getAttribute("listProduct");
                    if (result != null) { // search found at least 1 record

            %>

            <table border="1">
                <thead>
                    <tr>
                        <th>Add</th>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <%  int count = 0;
                        for (ProductDTO dto : result) {
                            String status = "Sold out";
                            if (dto.isStatus()) {
                                status = "Stocking";
                            }
                    %> 
                    <tr>
                        <td>
                            <input type="checkbox" name="chkItem" value="<%= dto.getSku()%>"
                                   <%
                                       if (session != null) {
                                           CartObj cart = (CartObj) session.getAttribute("CART");
                                           if (cart != null) {
                                               Map<String, Integer> items = cart.getItems();
                                               if (items != null) {
                                                   for (String key : items.keySet()) {
                                                       if (key.equals(dto.getSku())) {
                                   %>
                                   checked="checked"         
                                   <%
                                                       }
                                                   }
                                               }
                                           }
                                       }
                                   %>
                                   />

                            <input type="hidden" name="lastSearchValue" value="<%= searchValue%>" />
                        </td>
                        <td>
                            <%= ++count%>
                        </td>
                        <td>
                            <%= dto.getName()%>
                        </td>
                        <td>
                            <input type="number" name="numberquantity" value="" />
                        </td>
                        <td>
                            <%= dto.getPrice()%> 
                        </td>
                        <td>
                            <%= status%> 
                        </td>
                    </tr>
                    <%
                        } // end process each dto

                    %>
                    <tr>
                        <td colspan="4">
                            <input style="position: relative;left: 30%" type="submit" value="Add Book to Your Cart" name="btAction" />
                        </td>
                        <td colspan="2">
                            <input type="submit" value="View Your Cart" name="btAction" />
                        </td>
                    </tr>
                </tbody>
            </table>
            <% } else { %>
            <h2>No record is matched!!!</h2>
            <%
                    }
                } // end user access directly or first access this page

            %>
        </form>
            --%>
    </body>
</html>
