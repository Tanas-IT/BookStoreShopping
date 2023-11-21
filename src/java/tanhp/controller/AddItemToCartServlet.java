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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tanhp.cart.CartObj;
import tanhp.product.ProductDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {

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
        String searchValue = request.getParameter("lastSearchValue");
        String url = "DispatchServlet"
                + "?btAction=Search Items"
                + "&txtItemsValue=" + searchValue;
        String[] quantityNumber = request.getParameterValues("numberquantity");
        String[] idProductCheck = request.getParameterValues("IdProductCheck");
        try {
            // 1. Customer goes to the cart's place
            HttpSession session = request.getSession();
            // true vì bắt buộc phải có giỏ hàng
            // 2. Customer takes his/her cart
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObj();
            }
            // 3. Customer drops item to his/her cart
            String[] skuArray = request.getParameterValues("chkItem");
            if (skuArray != null) {
                for (String sku: skuArray) {
                    if (cart.getProductBySKU(sku).isStatus()) {
                        for(int i = 0; i < idProductCheck.length;i++) {
                            if(sku.equals(idProductCheck[i])) {
                                int quantityItem = Integer.parseInt(quantityNumber[i]);
                                cart.addItemToCart(sku, quantityItem);
                                break;
                            }
                        }
                    }
                }
            }
            session.setAttribute("CART", cart);
            // 4. Customer cotinuely goes to shopping
            response.sendRedirect(url);
        } catch (SQLException ex) {
            log("AddItemToCart_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("AddItemtoCart_Naming " + ex.getMessage());
        } finally {

        }
        // Sử dụng URLRewriting để quay lại trang shopping

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
