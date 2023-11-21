/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanhp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tanhp.util.MyApplicationConstants;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
//    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_PAGE = "";
//    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String LOGIN_CONTROLLER = "loginController";
//    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "searchController";
//    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "deleteAccountController";
//    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "updateAccountController";
//    private final String START_UP_CONTROLLER = "StartUpServlet";
    private final String START_UP_CONTROLLER = "startUpController";
//    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
    private final String ADD_ITEM_TO_CART_CONTROLLER = "addItemToCartController";
//    private final String REMOVE_ITEMS_FROM_CART_CONTROLLER = "RemoveItemsFromCartServlet";
    private final String REMOVE_ITEMS_FROM_CART_CONTROLLER = "removeItemFromCartController";
//    private final String SEARCH_ITEMS_CONTROLLER = "ShoppingServlet";
    private final String SEARCH_ITEMS_CONTROLLER = "shoppingController";
//    private final String VIEW_PAGE_CART = "viewCart.jsp";
    private final String VIEW_PAGE_CART = "viewCartController";
//    private final String CHECK_OUT_CONTROLLER = "CheckOutServlet";
    private final String CHECK_OUT_CONTROLLER = "checkOutController";
//    private final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountServlet";
    private final String CREATE_ACCOUNT_CONTROLLER = "createAccountController";
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
       
        // Which button did user click?
        String button = request.getParameter("btAction");
        //1.Get context scope
        ServletContext context = this.getServletContext();
        //2.Get SiteMaps
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        //3.Use SiteMaps to get url Pattern
//        String url = LOGIN_PAGE;
//        String url = siteMaps.getProperty(LOGIN_PAGE);
         String url = siteMaps.getProperty(MyApplicationConstants.DispatchFeatures.LOGIN_PAGE);
        try  {
           if(button == null) {// trigger welcome file
//               url = START_UP_CONTROLLER;
//               url = siteMaps.getProperty(START_UP_CONTROLLER);
                 url = siteMaps.getProperty(MyApplicationConstants.DispatchFeatures.START_UP_CONTROLLER);
           } else if (button.equals("Login")) {//user clicked login
//               url = LOGIN_CONTROLLER;
//               url = siteMaps.getProperty(LOGIN_CONTROLLER);
                 url = siteMaps.getProperty(MyApplicationConstants.DispatchFeatures.LOGIN_CONTROLLER);
           } else if (button.equals("Search")) {
//               url = SEARCH_LASTNAME_CONTROLLER;
//               url = siteMaps.getProperty(SEARCH_LASTNAME_CONTROLLER);
                 url = siteMaps.getProperty(MyApplicationConstants.DispatchFeatures.SEARCH_LASTNAME_CONTROLLER);
           } else if (button.equals("delete")) {
//               url = DELETE_ACCOUNT_CONTROLLER;
//               url = siteMaps.getProperty(DELETE_ACCOUNT_CONTROLLER);
                 url = siteMaps.getProperty(MyApplicationConstants.DispatchFeatures.DELETE_ACCOUNT_CONTROLLER);

           } else if(button.equals("Update")) {
//               url = UPDATE_ACCOUNT_CONTROLLER;
//               url = siteMaps.getProperty(UPDATE_ACCOUNT_CONTROLLER);
                 url = siteMaps.getProperty(MyApplicationConstants.DispatchFeatures.UPDATE_ACCOUNT_CONTROLLER);
               
           } else if(button.equals("Add Book to Your Cart")) {
//               url = ADD_ITEM_TO_CART_CONTROLLER;
//               url = siteMaps.getProperty(ADD_ITEM_TO_CART_CONTROLLER);
                 url = siteMaps.getProperty(MyApplicationConstants.DispatchFeatures.ADD_ITEM_TO_CART_CONTROLLER);
           } else if(button.equals("View Your Cart")) {
//               url = VIEW_PAGE_CART;
//               url = siteMaps.getProperty(VIEW_PAGE_CART);
                 url = siteMaps.getProperty(MyApplicationConstants.DispatchFeatures.VIEW_PAGE_CART);
           } else if (button.equals("Remove Selected Items")) {
//               url = REMOVE_ITEMS_FROM_CART_CONTROLLER;
//               url = siteMaps.getProperty(REMOVE_ITEMS_FROM_CART_CONTROLLER);
                 url = siteMaps.getProperty(MyApplicationConstants.DispatchFeatures.REMOVE_ITEMS_FROM_CART_CONTROLLER);
           } else if(button.equals("Search Items")) {
//               url = SEARCH_ITEMS_CONTROLLER;
//               url = siteMaps.getProperty(SEARCH_ITEMS_CONTROLLER);
                 url = siteMaps.getProperty(MyApplicationConstants.DispatchFeatures.SEARCH_ITEMS_CONTROLLER);
           } else if (button.equals("Check Out")) {
//               url = CHECK_OUT_CONTROLLER;
//               url = siteMaps.getProperty(CHECK_OUT_CONTROLLER);
                 url = siteMaps.getProperty(MyApplicationConstants.DispatchFeatures.CHECK_OUT_CONTROLLER);
           } else if(button.equals("Create New Account")) {
//               url = CREATE_ACCOUNT_CONTROLLER;
//               url = siteMaps.getProperty(CREATE_ACCOUNT_CONTROLLER);
                 url = siteMaps.getProperty(MyApplicationConstants.DispatchFeatures.CREATE_ACCOUNT_CONTROLLER);
           }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
