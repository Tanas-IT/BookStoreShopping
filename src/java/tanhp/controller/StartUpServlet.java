/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanhp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tanhp.registration.RegistrationDAO;
import tanhp.registration.RegistrationDTO;
import tanhp.util.MyApplicationConstants;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "StartUpServlet", urlPatterns = {"/StartUpServlet"})
public class StartUpServlet extends HttpServlet {
//    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_PAGE = "";
//    private final String SEARCH_PAGE = "search.jsp";
    private final String SEARCH_PAGE = "searchPage";
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
//        String url = LOGIN_PAGE;
         //1.Get context scope
        ServletContext context = this.getServletContext();
        //2.Get SiteMaps
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        //3.Use SiteMaps to get url Pattern
//        String url = siteMaps.getProperty(LOGIN_PAGE);
        String url = siteMaps.getProperty(MyApplicationConstants.StartUpFeatures.LOGIN_PAGE);
        try  {
//           // 1. Get all cookies
//           Cookie[] cookies = request.getCookies();
//          
//           // 2. Check Cookies
//           if(cookies != null) {
//             // 3. Get newest Cookie
//             Cookie newestCookie = cookies[cookies.length - 1];
//             // 4. Get username and password
//             String username = newestCookie.getName();
//             String password = newestCookie.getValue();
//             // 5. call DAO and use CheckLogin
//             RegistrationDAO dao = new RegistrationDAO();
//             RegistrationDTO result = dao.checkLogin(username, password);
             // 6. Process result
//             if(result != null) {
//                 url = SEARCH_PAGE;
//             }
              HttpSession session = request.getSession();
              RegistrationDTO registration = (RegistrationDTO) session.getAttribute("USER");
              if(registration != null)  {
                  url = siteMaps.getProperty(MyApplicationConstants.StartUpFeatures.SEARCH_PAGE);
              }
           } // end cookies have existed
         finally {
            response.sendRedirect(url);
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
