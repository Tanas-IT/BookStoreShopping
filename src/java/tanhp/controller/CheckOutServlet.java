/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanhp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tanhp.cart.CartObj;
import tanhp.order.OrderDAO;
import tanhp.orderdetail.OrderDetailDAO;
import tanhp.orderdetail.OrderDetailDTO;
import tanhp.util.MyApplicationConstants;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

//    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String VIEW_CART_PAGE = "viewCartController";
//    private final String CHECK_OUT_PAGE = "checkout.jsp";
    private final String CHECK_OUT_PAGE = "checkOutPage";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        double total = 0.0;
//        String url = VIEW_CART_PAGE;
        // 1. Get context scope
        ServletContext context = this.getServletContext();
        // 2. Get site Maps
        Properties properties = (Properties)context.getAttribute("SITEMAPS");
        // 3. Use SiteMaps to get url pattern
//        String url = properties.getProperty(VIEW_CART_PAGE);
        String url = properties.getProperty(MyApplicationConstants.CheckOutFeatures.VIEW_CART_PAGE);
        try {
            HttpSession session = request.getSession();
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart != null) {
                OrderDAO orderDao = new OrderDAO();
                String uniqueID = UUID.randomUUID().toString();
                orderDao.insertOrder(uniqueID.substring(0, 7), date);
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                if (cart.getItems() != null) {
                    for (String key : cart.getItems().keySet()) {
                        double totalEach = cart.getProductBySKU(key).getPrice() * cart.getItems().get(key);
                        total += totalEach;
                    }
                    for (String key : cart.getItems().keySet()) {
                        double totalEachProduct = cart.getProductBySKU(key).getPrice() * cart.getItems().get(key);
                        orderDetailDAO.insertToOrderDetails(key, uniqueID.substring(0, 7),
                                cart.getProductBySKU(key).getPrice(),
                                cart.getItems().get(key), totalEachProduct);
                    }
                    orderDao.updateOrder(uniqueID.substring(0, 7), Math.ceil(total * 1000)/1000);
                    double totalOfOrder = orderDao.getTotalByOrderID(uniqueID.substring(0, 7));
                    session.removeAttribute("CART");
                    orderDetailDAO.getAllOrder();
                    List<OrderDetailDTO> listOrder = orderDetailDAO.getListOrder();
                    session.setAttribute("listOrderDetail", listOrder);
                    session.setAttribute("OrderID", uniqueID.substring(0, 7));
                    session.setAttribute("total", totalOfOrder);
                    url = properties.getProperty(MyApplicationConstants.CheckOutFeatures.CHECK_OUT_PAGE);
                }
            } else {
                url = VIEW_CART_PAGE;
            }
            response.sendRedirect(url);
        } catch (SQLException ex) {
            log("CheckOut_ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckOut_ Naming " + ex.getMessage());
        } finally {

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
