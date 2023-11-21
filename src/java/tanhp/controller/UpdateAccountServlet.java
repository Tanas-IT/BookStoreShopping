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
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tanhp.registration.RegistrationDAO;
import tanhp.util.MyApplicationConstants;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {
//    private final String UPDATE_ERROR = "updateError.html";
    private final String UPDATE_ERROR = "updateErrorPage";
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
//        String url = UPDATE_ERROR;
         //1.Get context scope
        ServletContext context = this.getServletContext();
        //2.Get SiteMaps
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        //3.Use SiteMaps to get url Pattern
//        String url = siteMaps.getProperty(INVALID_PAGE);
        String url = siteMaps.getProperty(MyApplicationConstants.UpdateAccountFeatures.UPDATE_ERROR);
        try  {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String admin = request.getParameter("chkRole");
            boolean role = false;
            if(admin != null) {
                role = true;
            }
            String searchValue = request.getParameter("valueSearchUpdate");
            RegistrationDAO rd  = new RegistrationDAO();
            boolean result = rd.updateAccount(username, password, role);
            if(result) {
                url = "DispatchServlet?btAction=Search&txtSearchValue=" + searchValue;
            }
        } catch(SQLException ex) {
            log("UpdateAccount_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateAccount_Naming " + ex.getMessage());
        }
        finally {
            response.sendRedirect(url);
            out.close();
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
