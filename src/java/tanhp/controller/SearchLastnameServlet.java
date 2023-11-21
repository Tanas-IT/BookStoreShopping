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
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tanhp.registration.RegistrationDAO;
import tanhp.registration.RegistrationDTO;
import tanhp.util.MyApplicationConstants;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {
//    private final String SEARCH_PAGE = "search.html";
    private final String SEARCH_PAGE = "searchHTMLPage";
//    private final String RESULT_PAGE = "search.jsp";
    private final String RESULT_PAGE = "searchPage";
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
        
        String searchValue = request.getParameter("txtSearchValue");
//        String url = SEARCH_PAGE;
         //1.Get context scope
        ServletContext context = this.getServletContext();
        //2.Get SiteMaps
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        //3.Use SiteMaps to get url Pattern
//        String url = siteMaps.getProperty(SEARCH_PAGE);
        String url = siteMaps.getProperty(MyApplicationConstants.SearchLastNameFeatures.SEARCH_PAGE);
        try  {
           // 1. check valid search value
           if(!searchValue.trim().isEmpty()) {
               //2. call model
               //2.1 new DAO object
               RegistrationDAO dao = new RegistrationDAO();
               //2.2 call methods of DAO
               dao.searchLastName(searchValue);
               //3. process result
               List<RegistrationDTO> result = dao.getAccountList();
               request.setAttribute("SEARCH_RESULT", result);
               url = siteMaps.getProperty(MyApplicationConstants.SearchLastNameFeatures.RESULT_PAGE);
           }// end search value has valid value
        } catch(SQLException ex) {
            log("SearchLastName_SQL " + ex.getMessage());
        } catch(NamingException ex) {
            log("SearchLastName_Naming " + ex.getMessage());
        }
        finally {
            // dùng response.sendRedirect() sẽ xóa request Object có chứa attribute của kết quả
            // vừa search
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
