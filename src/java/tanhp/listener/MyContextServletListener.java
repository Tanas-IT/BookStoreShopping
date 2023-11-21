/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanhp.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author ASUS
 */
public class MyContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("When the application is deploying ..............");
        // 1. Get the context scope
        ServletContext context = sce.getServletContext();
        // 2. Get the siteMaps Path
        String siteMapsPath = context.getInitParameter("SITEMAPS_FILE_PATH");
        // 3. Load siteMaps into attribute of Context Scope
        Properties siteMaps = null;
        InputStream is = null;
        try {
            siteMaps = new Properties();
            is = context.getResourceAsStream(siteMapsPath);
            siteMaps.load(is);
            
            context.setAttribute("SITEMAPS", siteMaps);
        } catch (IOException ex) {
           context.log("MyContextServletListener    IO " + ex.getMessage());
        } finally {
            if(is != null) {
                try {
                    is.close();
                }catch(IOException ex) {
                    context.log("MyContextServletListener    IO " + ex.getMessage());
                } 
            }
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("When the appication is undeploying and destroying .......");
    }
}
