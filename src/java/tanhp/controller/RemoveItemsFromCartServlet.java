/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanhp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tanhp.cart.CartObj;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "RemoveItemsFromCartServlet", urlPatterns = {"/RemoveItemsFromCartServlet"})
public class RemoveItemsFromCartServlet extends HttpServlet {
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
        try {
            //1. Customer goes to his/her cart's place
            String searchValue = request.getParameter("lastSearchItems");
            HttpSession session = request.getSession();
            if(session != null) {
            //2. Customer take his/her cart
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart != null) {
                String[] skuArray = request.getParameterValues("chkItem");
                String[] temp = request.getParameterValues("quantityOfRemove");
                int[] quantityOfRemove = new int[temp.length];
                for(int i = 0; i < temp.length; i++) {
                    if(!temp[i].isEmpty()) {
                        quantityOfRemove[i] = Integer.parseInt(temp[i]);
                    }
                }
                if (skuArray != null) {
                    for (int i = 0; i < skuArray.length; i++) {
                        cart.removeItemFromCart(skuArray[i],quantityOfRemove[i]);
                    }//end for
                    session.setAttribute("CART", cart);
                }//end if item  
            }//end if cart
        }// end if session
        String urlRewriting="DispatchServlet?btAction=View Your Cart"
                + "&lastSearchValue="+searchValue;
        response.sendRedirect(urlRewriting);
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
