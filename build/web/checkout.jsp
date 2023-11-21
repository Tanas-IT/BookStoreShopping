<%-- 
    Document   : checkout
    Created on : Jun 25, 2023, 5:15:32 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="tanhp.product.ProductDTO"%>
<%@page import="tanhp.cart.CartObj"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="tanhp.orderdetail.OrderDetailDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CheckOut Page</title>
    </head>
    <body>
        <h1 style="color:red">YOUR BILL</h1>
        <c:set var="listOrderDetail" value="${sessionScope.listOrderDetail}"/>
        <c:set var="orderID" value="${sessionScope.OrderID}"/>
        <c:if test="${not empty listOrderDetail}">
            <jsp:useBean id="date" class="java.util.Date"/>
            <fmt:formatDate value="${date}" pattern="HH:MM:ss, dd-MM-yyyy" var="currentDateTime" type="date" />
            <jsp:useBean id="cart" class="tanhp.cart.CartObj"/>
        </c:if>
         <h1 style="display: inline-block; margin-right: 20px">ID of Bill: ${orderID}</h1>
        <h1 style="display: inline-block">Date: ${currentDateTime}</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Code of Product</th>
                    <th>Name of Product</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>SubTotal</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="counter" value="0"/>
                <c:forEach var="orderDTO" items="${listOrderDetail}">
                    <c:if test="${orderDTO.orderID eq orderID}">
                            <c:if test="${not empty cart.getProductBySKU(orderDTO.getSku())}">
                                <c:set var="product" value="${cart.getProductBySKU(orderDTO.getSku())}"/>
                            </c:if>
                            <c:if test="${not empty product}">
                                <tr>
                                    <td>${counter = counter + 1}</td>
                                    <td>${product.sku}</td>
                                    <td>${product.name}</td>
                                    <td>${orderDTO.quantity}</td>
                                    <td>${orderDTO.price}</td>
                                    <td>${String.format("%.3f",orderDTO.getQuantity()*orderDTO.getPrice())}</td>
                                </tr>
                            </c:if>
                    </c:if>
                </c:forEach>
               <tr>
                    <td colspan="5">Total</td>
                    <td>${sessionScope.total}</td>
                </tr>
            </tbody>
        </table>
                <h1>Thank you for buying !!!</h1>
                <a href="shopping.jsp">Click here to return shopping page</a>

       <%-- <%
            List<OrderDetailDTO> listOrderDetail = (List<OrderDetailDTO>) session.getAttribute("listOrderDetail");
            String orderID = (String) session.getAttribute("OrderID");
            if(listOrderDetail != null) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                CartObj cart = new CartObj();
        %>
        <h1 style="display: inline-block; margin-right: 20px">OrderID: <%= orderID  %></h1>
        <h1 style="display: inline-block">Date: <%= sdf.format(date) %></h1>  
        <table border="1">
            <thead>
                <tr>
                    <th>Code of Product</th>
                    <th>Name of Product</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>SubTotal</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for(OrderDetailDTO orderDTO : listOrderDetail) {
                        if(orderDTO.getOrderID().equals(orderID)) {
                        ProductDTO product = (cart.getProductBySKU(orderDTO.getSku()));
                        if(product != null) {
                            %>
                            <tr>
                                <td><%= product.getSku() %></td>
                                <td><%= product.getName() %></td>
                                <td><%= orderDTO.getQuantity() %></td>
                                <td><%= orderDTO.getPrice() %></td>
                                <td><%= String.format("%.3f", orderDTO.getQuantity()*orderDTO.getPrice()) %></td>
                            </tr>         
                <%
                        }
                    }
                 }
                %>
                <tr>
                    <td colspan="4">Total</td>
                    <td><%= session.getAttribute("total") %></td>
                </tr>
            </tbody>
        </table>

        <%
            } 
%>  --%>
        
    </body>
</html>
